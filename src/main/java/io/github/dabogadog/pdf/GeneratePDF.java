package io.github.dabogadog.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.JFreeChart;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneratePDF {

    public static void createSavePDF(JFreeChart ConsolidadoTestBatterychart,JFreeChart ConsolidadoTestCpu
            ,JFreeChart ConsolidadoTestMem, JFreeChart ConsolidadoTestFps)
    {
        // pdf
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        document.addPage(page);

        try {
            // imagen
            BufferedImage chartImageBatterychart = ConsolidadoTestBatterychart.createBufferedImage(800, 600);
            BufferedImage chartImageTestCpu = ConsolidadoTestCpu.createBufferedImage(800, 600);
            BufferedImage chartImageTestMem = ConsolidadoTestMem.createBufferedImage(800, 600);
            BufferedImage chartImageTestFps = ConsolidadoTestFps.createBufferedImage(800, 600);

            // Guardar la imagen temporalmente
            File tempImageBatterychart = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageBatterychart, "png", tempImageBatterychart);
            File tempImageTestCpu = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageTestCpu, "png", tempImageTestCpu);
            File tempImageTestMem = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageTestMem, "png", tempImageTestMem);
            File tempImageTestFps = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageTestFps, "png", tempImageTestFps);

            //formato imagen
            PDImageXObject ximageBatterychart = PDImageXObject.createFromFileByContent(tempImageBatterychart, document);
            PDImageXObject ximageTestCpu = PDImageXObject.createFromFileByContent(tempImageTestCpu, document);
            PDImageXObject ximageTestMem = PDImageXObject.createFromFileByContent(tempImageTestMem, document);
            PDImageXObject ximageTestFps = PDImageXObject.createFromFileByContent(tempImageTestFps, document);

            // Agregar la imagen al PDF
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                contentStream.drawImage(ximageBatterychart, 10,600, ximageBatterychart.getWidth() * 0.3f, ximageBatterychart.getHeight() * 0.3f);
                contentStream.drawImage(ximageTestCpu, 10,400, ximageTestCpu.getWidth() * 0.3f, ximageTestCpu.getHeight() * 0.3f);
                contentStream.drawImage(ximageTestMem, 10,200, ximageTestMem.getWidth() * 0.3f, ximageTestMem.getHeight() * 0.3f);
                contentStream.drawImage(ximageTestFps, 10,100, ximageTestFps.getWidth() * 0.3f, ximageTestFps.getHeight() * 0.3f);
                contentStream.setFont(font, 16); // Tamaño y estilo de fuente
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 800); // Posición del título
                contentStream.showText("Gráfico de Consolidado");
                contentStream.endText();

            }
            tempImageBatterychart.delete();
            tempImageTestCpu.delete();
            tempImageTestMem.delete();
            tempImageTestFps.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Guardar
        try {
            document.save("ConsolidadoMetricasTest.pdf");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
