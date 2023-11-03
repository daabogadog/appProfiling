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

    public static void createSavePDF(JFreeChart ConsolidadoTestTempchart,JFreeChart ConsolidadoTestBatterychart,JFreeChart ConsolidadoTestCpu
            ,JFreeChart ConsolidadoTestMem, JFreeChart ConsolidadoTestFps)
    {
        // pdf
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage(PDRectangle.A4);
        PDPage page2 = new PDPage(PDRectangle.A4);
        PDPage page3 = new PDPage(PDRectangle.A4);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        document.addPage(page1);
        document.addPage(page2);
        document.addPage(page3);


        try {
            // imagen
            BufferedImage chartImageBatterychart = ConsolidadoTestBatterychart.createBufferedImage(800, 600);
            BufferedImage chartImageTempchart = ConsolidadoTestTempchart.createBufferedImage(800, 600);
            BufferedImage chartImageTestCpu = ConsolidadoTestCpu.createBufferedImage(800, 600);
            BufferedImage chartImageTestMem = ConsolidadoTestMem.createBufferedImage(800, 600);
            BufferedImage chartImageTestFps = ConsolidadoTestFps.createBufferedImage(800, 600);

            // Guardar la imagen temporalmente
            File tempImageBatterychart = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageBatterychart, "png", tempImageBatterychart);
            File tempImageTempchart = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageTempchart, "png", tempImageTempchart);
            File tempImageTestCpu = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageTestCpu, "png", tempImageTestCpu);
            File tempImageTestMem = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageTestMem, "png", tempImageTestMem);
            File tempImageTestFps = File.createTempFile("chartImage", ".png");
            ImageIO.write(chartImageTestFps, "png", tempImageTestFps);

            //formato imagen
            PDImageXObject ximageBatterychart = PDImageXObject.createFromFileByContent(tempImageBatterychart, document);
            PDImageXObject ximageTempchart = PDImageXObject.createFromFileByContent(tempImageTempchart, document);
            PDImageXObject ximageTestCpu = PDImageXObject.createFromFileByContent(tempImageTestCpu, document);
            PDImageXObject ximageTestMem = PDImageXObject.createFromFileByContent(tempImageTestMem, document);
            PDImageXObject ximageTestFps = PDImageXObject.createFromFileByContent(tempImageTestFps, document);

            // Agregar la imagen al PDF
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page1, PDPageContentStream.AppendMode.APPEND, true)) {
                contentStream.drawImage(ximageTestCpu, 30,450, ximageTestCpu.getWidth() * 0.6f, ximageTestCpu.getHeight() * 0.5f);
                contentStream.drawImage(ximageTestMem, 30,100, ximageTestMem.getWidth() * 0.6f, ximageTestMem.getHeight() * 0.5f);
                contentStream.setFont(font, 18); // Tamaño y estilo de fuente
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 800); // Posición del título
                contentStream.showText("Gráfico de Consolidado");
                contentStream.endText();

            }
            // Agregar la imagen al PDF
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page2, PDPageContentStream.AppendMode.APPEND, true)) {
                contentStream.drawImage(ximageBatterychart, 30,450, ximageBatterychart.getWidth() * 0.6f, ximageBatterychart.getHeight() * 0.5f);
                contentStream.drawImage(ximageTestFps, 30,10, ximageTestFps.getWidth() * 0.6f, ximageTestFps.getHeight() * 0.5f);
                contentStream.setFont(font, 18); // Tamaño y estilo de fuente
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 800); // Posición del título
                contentStream.showText("Gráfico de Consolidado");
                contentStream.endText();

            }     // Agregar la imagen al PDF
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page3, PDPageContentStream.AppendMode.APPEND, true)) {
                contentStream.drawImage(ximageTempchart, 30,450, ximageTempchart.getWidth() * 0.6f, ximageTempchart.getHeight() * 0.5f);
                contentStream.setFont(font, 18); // Tamaño y estilo de fuente
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 800); // Posición del título
                contentStream.showText("Gráfico de Consolidado");
                contentStream.endText();

            }

            tempImageTempchart.delete();
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
