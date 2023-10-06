package io.github.dabogadog.httpUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpUtils {
    private static final Logger logger = Logger.getLogger(HttpUtils.class.getName());
    public static String sendHttpGetRequest(String url, String authorization) {
        try {
            // Crear la conexión HTTP
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configurar la solicitud
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", ApiConfig.ACCEPT);
            con.setRequestProperty("Accept-Language", ApiConfig.ACCEPT_LANGUAGE);
            con.setRequestProperty("Authorization", authorization);

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

                // Devolver el JSON de la respuesta
                return response.toString();
            } else {
                logger.log(Level.SEVERE, "Error en la solicitud HTTP:", responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<String> convertToSessionIdList(String sessionIdString) {
        // Divide la cadena en elementos individuales utilizando la coma como delimitador
        String[] sessionIdArray = sessionIdString.split(",");

        // Convierte el array en una lista
        List<String> sessionIdList = Arrays.asList(sessionIdArray);

        return sessionIdList;
    }
}
