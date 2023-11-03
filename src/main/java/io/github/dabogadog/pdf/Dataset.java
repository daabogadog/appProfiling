package io.github.dabogadog.pdf;

import io.github.dabogadog.models.BateryTemp.ConsolidadoTestBatteryTemp;
import io.github.dabogadog.models.CpuMem.ConsolidadoTestCpuMem;
import io.github.dabogadog.models.Rendering.ConsolidadoTestFps;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

public class Dataset {

    public static JFreeChart ConsolidadoTestTempChart(ConsolidadoTestBatteryTemp consolidadoTestBattery){
        // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestBattery.temperatureMax, "Temperature", "Max");
        dataset.addValue(consolidadoTestBattery.temperatureMin, "Temperature", "Min");
        dataset.addValue(consolidadoTestBattery.temperatureAvg, "Temperature", "Avg");

        return getChart("Consolidado Temperatura",dataset);
    }
    public static JFreeChart ConsolidadoTestFpsChart(ConsolidadoTestFps consolidadoTestFps){
        // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestFps.fpsMax, "FPS", "Max");
        dataset.addValue(consolidadoTestFps.fpsMin, "FPS", "Min");
        dataset.addValue(consolidadoTestFps.fpsAvg, "FPS", "Avg");

        return getChart("Consolidado FPS",dataset);
    }
    public static JFreeChart ConsolidadoTestBatteryChart(ConsolidadoTestBatteryTemp consolidadoTestBattery){
        // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestBattery.batteryMax, "Battery", "Max");
        dataset.addValue(consolidadoTestBattery.batteryMin, "Battery", "Min");
        dataset.addValue(consolidadoTestBattery.batteryAvg, "Battery", "Avg");

        return getChart("Consolidado Batería",dataset);
    }
    public static JFreeChart ConsolidadoTestMemChart(String titulo,ConsolidadoTestCpuMem consolidadoTestCpuMem){
        // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestCpuMem.appMax, "APP", "Max");
        dataset.addValue(consolidadoTestCpuMem.appMin, "APP", "Min");
        dataset.addValue(consolidadoTestCpuMem.appAvg, "APP", "Avg");
        dataset.addValue(consolidadoTestCpuMem.sysMax, "SYS", "Max");
        dataset.addValue(consolidadoTestCpuMem.sysMin, "SYS", "Min");
        dataset.addValue(consolidadoTestCpuMem.sysAvg, "SYS", "Avg");

        return getChart(titulo,dataset);
    }


    private static JFreeChart getChart(String titulo,DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                titulo,    // Título del gráfico
                "Categoria",        // eje X
                "Valor",           //  eje Y
                dataset,           // Conjunto de dato
                PlotOrientation.VERTICAL, // Orientación del gráficgit
                true,              // leyenda
                true,
                false
        );
    }

}
