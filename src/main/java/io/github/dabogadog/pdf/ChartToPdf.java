package io.github.dabogadog.pdf;

import io.github.dabogadog.models.BateryTemp.ConsolidadoTestBatteryTemp;
import io.github.dabogadog.models.CpuMem.ConsolidadoTestCpuMem;
import io.github.dabogadog.models.Rendering.ConsolidadoTestFps;
import org.jfree.chart.JFreeChart;
import static io.github.dabogadog.pdf.Dataset.*;
import static io.github.dabogadog.pdf.GeneratePDF.createSavePDF;


public class ChartToPdf {
    public static void generatePdf(ConsolidadoTestBatteryTemp consolidadoTestBattery,
                                   ConsolidadoTestCpuMem consolidadoTestCpuM, ConsolidadoTestCpuMem consolidadoTestMem,
                                   ConsolidadoTestFps consolidadoTestFps)
    {

        JFreeChart ConsolidadoTestTempchart = ConsolidadoTestTempChart(consolidadoTestBattery);
        JFreeChart ConsolidadoTestBatterychart = ConsolidadoTestBatteryChart(consolidadoTestBattery);
        JFreeChart ConsolidadoTestCpu = ConsolidadoTestMemChart("Consolidado CPU",consolidadoTestCpuM);
        JFreeChart ConsolidadoTestMem = ConsolidadoTestMemChart("Comosolidado Memoría",consolidadoTestMem);
        JFreeChart ConsolidadoTestFps = ConsolidadoTestFpsChart(consolidadoTestFps);
        createSavePDF(ConsolidadoTestTempchart,ConsolidadoTestBatterychart,ConsolidadoTestCpu,ConsolidadoTestMem,ConsolidadoTestFps);

    }
}

