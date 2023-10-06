package io.github.dabogadog.apiProfiling;

import io.github.dabogadog.httpUtils.ApiConfig;
import io.github.dabogadog.httpUtils.HttpUtils;


public class ApiProfiling {

    public static String obtainApiProfiling(String sessionId, String autorizacion) {

            // Construir el URL completo utilizando la URL base de la API de ApiConfig
            String url = ApiConfig.BASE_URL
                    + sessionId + ApiConfig.PARAMS_APIPROFILING;

            // Realizar la solicitud HTTP utilizando HttpUtils
            return HttpUtils.sendHttpGetRequest(url, autorizacion);
        }

}