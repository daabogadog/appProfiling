package io.github.dabogadog.pdf;

import io.github.dabogadog.models.BateryTemp.ConsolidadoTestBatteryTemp;
import io.github.dabogadog.models.CpuMem.ConsolidadoTestCpuMem;
import io.github.dabogadog.models.Rendering.ConsolidadoTestFps;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Dataset {

    public static JFreeChart ConsolidadoTestBatteryTempChart(ConsolidadoTestBatteryTemp consolidadoTestBattery){
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
        return chart;
    }

    public static JFreeChart ConsolidadoTestCPUChart(ConsolidadoTestCpuMem consolidadoTestCpuMem){
        // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestCpuMem.appMax, "CPU", "Max");
        dataset.addValue(consolidadoTestCpuMem.appMin, "CPU", "Min");
        dataset.addValue(consolidadoTestCpuMem.appAvg, "CPU", "Avg");

        // crear graico
        JFreeChart chart = ChartFactory.createBarChart(
                "Consolidado Cpu",    // Título del gráfico
                "Categoria",        // eje X
                "Valor",           //  eje Y
                dataset,           // Conjunto de dato
                PlotOrientation.VERTICAL, // Orientación del gráficgit
                true,              // leyenda
                true,
                false
        );
        return chart;
    }

    public static JFreeChart ConsolidadoTestMemChart(ConsolidadoTestCpuMem consolidadoTestCpuMem){
        // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestCpuMem.sysMax, "Memoria", "Max");
        dataset.addValue(consolidadoTestCpuMem.sysMin, "Memoria", "Min");
        dataset.addValue(consolidadoTestCpuMem.sysAvg, "Memoria", "Avg");

        // crear graico
        JFreeChart chart = ChartFactory.createBarChart(
                "Consolidado Memoria",    // Título del gráfico
                "Categoria",        // eje X
                "Valor",           //  eje Y
                dataset,           // Conjunto de dato
                PlotOrientation.VERTICAL, // Orientación del gráficgit
                true,              // leyenda
                true,
                false
        );
        return chart;
    }

    public static JFreeChart ConsolidadoTestFpsChart(ConsolidadoTestFps consolidadoTestFps){
        // datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(consolidadoTestFps.fpsMax, "FPS", "Max");
        dataset.addValue(consolidadoTestFps.fpsMin, "FPS", "Min");
        dataset.addValue(consolidadoTestFps.fpsAvg, "FPS", "Avg");

        // crear graico
        JFreeChart chart = ChartFactory.createBarChart(
                "Consolidado FPS",    // Título del gráfico
                "Categoria",        // eje X
                "Valor",           //  eje Y
                dataset,           // Conjunto de dato
                PlotOrientation.VERTICAL, // Orientación del gráficgit
                true,              // leyenda
                true,
                false
        );
        return chart;
    }

}
