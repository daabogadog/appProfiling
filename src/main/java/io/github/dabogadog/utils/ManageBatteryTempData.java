package io.github.dabogadog.utils;

import com.google.gson.reflect.TypeToken;
import io.github.dabogadog.models.BateryTemp.BatteryTemp;
import io.github.dabogadog.models.BateryTemp.ConsolidadoTestBatteryTemp;
import io.github.dabogadog.models.CpuMem.ConsolidadoTestCpuMem;
import io.github.dabogadog.models.CpuMem.Cpu;
import io.restassured.RestAssured;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.github.dabogadog.httpUtils.ApiConfig.BASE_URL;
import static io.github.dabogadog.httpUtils.ApiConfig.PARAMS_APIBATTERY;

public class ManageBatteryTempData {
    public static List<BatteryTemp> getAllBatteryTempDataFromBuild(String sessionID) {
        Type listType = new TypeToken<List<BatteryTemp>>() {
        }.getType();

        return RestAssured.given().when().get(BASE_URL + sessionID + PARAMS_APIBATTERY)
                .then().extract().as(listType);
    }
    public static ConsolidadoTestBatteryTemp getMetricForEachTest(List<BatteryTemp> listBatteryTemp) {

        return ConsolidadoTestBatteryTemp
                .builder()
                .temperatureMax(getTempMax(listBatteryTemp))
                .temperatureMin(getTempMin(listBatteryTemp))
                .temperatureAvg(getTempAvg(listBatteryTemp))
                .batteryMax(getBatteryMax(listBatteryTemp))
                .batteryMin(getBatteryMin(listBatteryTemp))
                .batteryAvg(getBatteryAvg(listBatteryTemp))
                .build();
    }

    public static ConsolidadoTestBatteryTemp getBatteryTempMetrics(String[] sessionIdsArray) {
        List<ConsolidadoTestBatteryTemp> listaConsolidadoTestBatteryTemp = getBatteryTempMetricsFromEachTest(sessionIdsArray);
        return getConsolidatedBatteryTempMetrics(listaConsolidadoTestBatteryTemp);
    }

    public static List<ConsolidadoTestBatteryTemp> getBatteryTempMetricsFromEachTest(String[] sessionIdList) {
        List<ConsolidadoTestBatteryTemp> listaConsolidadoTestBaterryTemp = new ArrayList<>();

        for (String sessionID : sessionIdList) {
            List<BatteryTemp> listBatteryTemp = getAllBatteryTempDataFromBuild(sessionID);
            ConsolidadoTestBatteryTemp metricsFromEachTest = getMetricForEachTest(listBatteryTemp);
            listaConsolidadoTestBaterryTemp.add(metricsFromEachTest);
        }
        return listaConsolidadoTestBaterryTemp;
    }
    public static ConsolidadoTestBatteryTemp getConsolidatedBatteryTempMetrics(List<ConsolidadoTestBatteryTemp> listaConsolidadoTest) {
        double tempMax = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.temperatureMax)
                .max().orElse(0.0);
        double tempMin = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.temperatureMin)
                .max().orElse(0.0);
        double tempAvg = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.temperatureAvg)
                .max().orElse(0.0);
        double batteryMax = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.temperatureMax)
                .max().orElse(0.0);
        double batteryMin = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.batteryMin)
                .max().orElse(0.0);
        double batteryAvg = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.batteryAvg)
                .max().orElse(0.0);

        return ConsolidadoTestBatteryTemp.builder()
                .temperatureMax(tempMax)
                .temperatureMin(tempMin)
                .temperatureAvg(tempAvg)
                .batteryMax(batteryMax)
                .batteryMin(batteryMin)
                .batteryAvg(batteryAvg)
                .build();
    }

    public static double getTempMax(List<BatteryTemp> listBatteryTemp) {
        return listBatteryTemp.stream()
                .mapToDouble(e -> e.data.temp)
                .max()
                .orElse(0.0);
    }
    public static double getTempMin(List<BatteryTemp> listBatteryTemp) {
        return listBatteryTemp.stream()
                .mapToDouble(e -> e.data.temp)
                .min()
                .orElse(0.0);
    }
    public static double getTempAvg(List<BatteryTemp> listBatteryTemp) {
        return listBatteryTemp.stream()
                .mapToDouble(e -> e.data.temp)
                .average()
                .orElse(0.0);
    }
    public static double getBatteryMax(List<BatteryTemp> listBatteryTemp) {
        return listBatteryTemp.stream()
                .mapToDouble(e -> e.data.percent)
                .max()
                .orElse(0.0);
    }
    public static double getBatteryMin(List<BatteryTemp> listBatteryTemp) {
        return listBatteryTemp.stream()
                .mapToDouble(e -> e.data.percent)
                .min()
                .orElse(0.0);
    }
    public static double getBatteryAvg(List<BatteryTemp> listBatteryTemp) {
        return listBatteryTemp.stream()
                .mapToDouble(e -> e.data.percent)
                .average()
                .orElse(0.0);
    }


}
