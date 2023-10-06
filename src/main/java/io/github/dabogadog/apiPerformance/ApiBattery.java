package io.github.dabogadog.apiPerformance;

import io.github.dabogadog.httpUtils.ApiConfig;
import io.github.dabogadog.httpUtils.HttpUtils;


public class ApiBattery {

    public static String obtainApiApiBattery(String sessionId, String autorizacion) {

    // Construir el URL completo utilizando la URL base de la API de ApiConfig
    String url = ApiConfig.BASE_URL
            + sessionId + ApiConfig.PARAMS_APIBATTERY;

    // Realizar la solicitud HTTP utilizando HttpUtils
        return HttpUtils.sendHttpGetRequest(url, autorizacion);
    }
}
