package io.github.dabogadog.utils;

import com.google.gson.reflect.TypeToken;
import io.github.dabogadog.models.CpuMem.ConsolidadoTestCpuMem;
import io.github.dabogadog.models.CpuMem.Cpu;
import io.restassured.RestAssured;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.github.dabogadog.httpUtils.ApiConfig.BASE_URL;

public class ManageCpuData {

    public static ConsolidadoTestCpuMem getCpuMetrics(String[] sessionIdsArray, String type) {
        List<ConsolidadoTestCpuMem> listaConsolidadoTestCpuMem = getCpuMemMetricsFromEachTest(sessionIdsArray, type);
        return getConsolidatedCpuMemMetrics(listaConsolidadoTestCpuMem);
    }

    public static ConsolidadoTestCpuMem getMemMetrics(String[] sessionIdsArray, String type) {
        List<ConsolidadoTestCpuMem> listaConsolidadoTestCpuMem = getCpuMemMetricsFromEachTest(sessionIdsArray, type);
        return getConsolidatedCpuMemMetrics(listaConsolidadoTestCpuMem);
    }

    public static List<ConsolidadoTestCpuMem> getCpuMemMetricsFromEachTest(String[] sessionIdList, String type) {
        List<ConsolidadoTestCpuMem> listaConsolidadoTestCpuMem = new ArrayList<>();

        for (String sessionID : sessionIdList) {
            List<Cpu> listCpuMem = getAllCpuMemDataFromBuild(sessionID, type);
            ConsolidadoTestCpuMem metricsFromEachTest = getMetricForEachTest(listCpuMem);
            listaConsolidadoTestCpuMem.add(metricsFromEachTest);
        }
        return listaConsolidadoTestCpuMem;
    }

    public static ConsolidadoTestCpuMem getMetricForEachTest(List<Cpu> listCpuMem) {

        return ConsolidadoTestCpuMem
                .builder()
                .sysMax(getSysMax(listCpuMem))
                .sysMin(getSysMin(listCpuMem))
                .sysAvg(getSysAvg(listCpuMem))
                .appMax(getAppMax(listCpuMem))
                .appMin(getAppMin(listCpuMem))
                .appAvg(getAppAvg(listCpuMem))
                .build();
    }

    public static ConsolidadoTestCpuMem getConsolidatedCpuMemMetrics(List<ConsolidadoTestCpuMem> listaConsolidadoTest) {
        double appMax = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.appMax)
                .max().orElse(0.0);
        double appMin = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.appMin)
                .max().orElse(0.0);
        double appAvg = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.appAvg)
                .max().orElse(0.0);
        double sysMax = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.sysMax)
                .max().orElse(0.0);
        double sysMin = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.sysMin)
                .max().orElse(0.0);
        double sysAvg = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.sysAvg)
                .max().orElse(0.0);

        return ConsolidadoTestCpuMem.builder()
                .sysMax(sysMax)
                .sysMin(sysMin)
                .sysAvg(sysAvg)
                .appMax(appMax)
                .appMin(appMin)
                .appAvg(appAvg)
                .build();
    }


    public static List<Cpu> getAllCpuMemDataFromBuild(String sessionID, String type) {
        Type listType = new TypeToken<List<Cpu>>() {
        }.getType();
        System.out.println("Obteniendo metricas de cpu y memoria");
        return RestAssured.given().when().get(BASE_URL + sessionID + "/log/appprofile?metricstype=" + type)
                .then().extract().as(listType);
    }
    public static double getAppMax(List<Cpu> listCpu) {
        return listCpu.stream()
                .mapToDouble(e -> e.data.app)
                .max()
                .orElse(0.0);
    }

    public static double getAppMin(List<Cpu> listCpu) {
        return listCpu.stream()
                .mapToDouble(e -> e.data.app)
                .filter(e -> e != 0.0)
                .min()
                .orElse(0.0);
    }

    public static double getAppAvg(List<Cpu> listCpu) {
        return listCpu.stream()
                .mapToDouble(e -> e.data.app)
                .average()
                .orElse(0.0);
    }

    public static double getSysMax(List<Cpu> listCpu) {
        return listCpu.stream()
                .mapToDouble(e -> e.data.sys)
                .max()
                .orElse(0.0);
    }

    public static double getSysMin(List<Cpu> listCpu) {
        return listCpu.stream()
                .mapToDouble(e -> e.data.sys)
                .filter(e -> e != 0.0)
                .min()
                .orElse(0.0);
    }

    public static double getSysAvg(List<Cpu> listCpu) {
        return listCpu.stream()
                .mapToDouble(e -> e.data.sys)
                .average()
                .orElse(0.0);
    }

}
