package io.github.dabogadog.utils;

import com.google.gson.reflect.TypeToken;
import io.github.dabogadog.models.Rendering.ConsolidadoTestFps;
import io.github.dabogadog.models.Rendering.Fps;
import io.restassured.RestAssured;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.github.dabogadog.httpUtils.ApiConfig.BASE_URL;
import static io.github.dabogadog.httpUtils.ApiConfig.PARAMS_APIRENDERING;

public class ManageFpsData {
    private static Logger logger = Logger.getLogger(ManageFpsData.class.getName());

    public static List<Fps> getAllFpsDataFromBuild(String sessionID) {
        Type listType = new TypeToken<List<Fps>>() {
        }.getType();

        return RestAssured.given().when().get(BASE_URL + sessionID + PARAMS_APIRENDERING)
                .then().extract().as(listType);
    }
    public static ConsolidadoTestFps getMetricForEachTest(List<Fps> fpsList) {

        return ConsolidadoTestFps
                .builder()
                .fpsMax(getFpsMax(fpsList))
                .fpsMin(getFpsMin(fpsList))
                .fpsAvg(getFpsAvg(fpsList))
                .build();
    }
    public static ConsolidadoTestFps getFpsMetrics(String[] sessionIdsArray) {
        List<ConsolidadoTestFps> listaConsolidadoTestFps = getFpsMetricsFromEachTest(sessionIdsArray);
        return getConsolidatedFpsMetrics(listaConsolidadoTestFps);
    }

    public static List<ConsolidadoTestFps> getFpsMetricsFromEachTest(String[] sessionIdList) {
        List<ConsolidadoTestFps> listaConsolidadoTestFps = new ArrayList<>();

        for (String sessionID : sessionIdList) {
            logger.log(Level.INFO, "Getting Fps data");
            List<Fps> fpsList = getAllFpsDataFromBuild(sessionID);
            ConsolidadoTestFps metricsFromEachTest = getMetricForEachTest(fpsList);
            listaConsolidadoTestFps.add(metricsFromEachTest);
        }
        logger.log(Level.INFO, "Fps done");

        return listaConsolidadoTestFps;
    }
    public static ConsolidadoTestFps getConsolidatedFpsMetrics(List<ConsolidadoTestFps> listaConsolidadoTest) {
        double fpsMax = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.fpsMax)
                .max().orElse(0.0);
        double fpsMin = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.fpsMin)
                .max().orElse(0.0);
        double fpsAvg = listaConsolidadoTest.stream()
                .mapToDouble(e -> e.fpsAvg)
                .max().orElse(0.0);


        return ConsolidadoTestFps.builder()
                .fpsMax(fpsMax)
                .fpsMin(fpsMin)
                .fpsAvg(fpsAvg)
                .build();
    }
    public static double getFpsMax(List<Fps> fpsList) {
        return fpsList.stream()
                .mapToDouble(e -> e.data.count)
                .max()
                .orElse(0.0);
    }
    public static double getFpsMin(List<Fps> fpsList) {
        return fpsList.stream()
                .mapToDouble(e -> e.data.count)
                .min()
                .orElse(0.0);
    }
    public static double getFpsAvg(List<Fps> fpsList) {
        return fpsList.stream()
                .mapToDouble(e -> e.data.count)
                .average()
                .orElse(0.0);
    }


}
