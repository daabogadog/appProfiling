package io.github.dabogadog.Pdf;

import io.github.dabogadog.models.BateryTemp.ConsolidadoTestBatteryTemp;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class ChartToPdf {
    public static void generatePdf(ConsolidadoTestBatteryTemp consolidadoTestBattery) {
       // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestBattery.temperatureMax, "Temperature", "Max");
        dataset.addValue(consolidadoTestBattery.temperatureMin, "Temperature", "Min");
        dataset.addValue(consolidadoTestBattery.temperatureAvg, "Temperature", "Avg");
        dataset.addValue(consolidadoTestBattery.batteryMax, "Battery", "Max");
        dataset.addValue(consolidadoTestBattery.batteryMin, "Battery", "Min");
        dataset.addValue(consolidadoTestBattery.batteryAvg, "Battery", "Avg");

        // crear graico
        JFreeChart chart = ChartFactory.createBarChart(
                "Consolidado Bateria",    // Título del gráfico
                "Categoria",        // eje X
                "Valor",           //  eje Y
                dataset,           // Conjunto de dato
                PlotOrientation.VERTICAL, // Orientación del gráficgit
                true,              // leyenda
                true,
                false
        );
        // pdf
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        document.addPage(page);

        try {
            // imagen
            BufferedImage chartImage = chart.createBufferedImage(800, 600);

            // Guardar la imagen temporalmente
            File tempImage = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImage, "png", tempImage);


            PDImageXObject ximage = PDImageXObject.createFromFileByContent(tempImage, document);
           // Ajusta las coordenadas
            float x = 100; // Coordenada X
            float y = 300; // Coordenada Y
            float width = ximage.getWidth() * 0.5f; // Ancho de la imagen
            float height = ximage.getHeight() * 0.5f; // Alto de la imagen

            // Agregar la imagen al PDF
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                contentStream.drawImage(ximage, x, y, width, height);
                contentStream.setFont(font, 16); // Tamaño y estilo de fuente
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 800); // Posición del título
                contentStream.showText("Gráfico de Consolidado");
                contentStream.endText();

            }


            tempImage.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Guardar
        try {
            document.save("chart.pdf");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

