package io.github.dabogadog.sessionIds;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.dabogadog.httpUtils.ApiConfig;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiSessionIds {
    private static final Logger logger = Logger.getLogger(ApiSessionIds.class.getName());
    public static String getSessionIds(String idVariable, String autorizacion) {
        try {
            // URL de la API
            String url = ApiConfig.BASE_URL_SESSIONID + idVariable + ApiConfig.PARAMS_SESSIONID;

            // Crear la conexión HTTP
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configurar la solicitud
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Language", ApiConfig.ACCEPT_LANGUAGE );
            con.setRequestProperty("Authorization", autorizacion);

            // Obtener la respuesta
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Analizar la respuesta JSON
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonResponse = jsonParser.parse(response.toString()).getAsJsonObject();
                JsonArray data = jsonResponse.getAsJsonObject("data").getAsJsonArray("data");


                // Crear una lista para almacenar los sessionIds
                List<String> sessionIds = new ArrayList<>();

                // Iterar a través de los objetos y extraer los sessionIds
                for (int i = 0; i < data.size(); i++) {
                    JsonObject item = data.get(i).getAsJsonObject();
                    String sessionId = item.get("sessionId").getAsString();
                    sessionIds.add(sessionId);
                }

                // Convertir la lista en una cadena separada por comas
                return String.join(",", sessionIds);
            } else {
                logger.log(Level.SEVERE, "Error en la solicitud HTTP:", responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Si no se pueden obtener los sessionIds, devuelve una cadena vacía
        return "";
    }

}
