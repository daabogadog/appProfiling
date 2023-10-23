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

        JFreeChart ConsolidadoTestBatterychart = ConsolidadoTestBatteryTempChart(consolidadoTestBattery);
        JFreeChart ConsolidadoTestCpu = ConsolidadoTestCPUChart(consolidadoTestCpuM);
        JFreeChart ConsolidadoTestMem = ConsolidadoTestMemChart(consolidadoTestMem);
        JFreeChart ConsolidadoTestFps = ConsolidadoTestFpsChart(consolidadoTestFps);
        createSavePDF(ConsolidadoTestBatterychart,ConsolidadoTestCpu,ConsolidadoTestMem,ConsolidadoTestFps);

    }
}

