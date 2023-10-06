package io.github.dabogadog.listEjecution;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import io.github.dabogadog.httpUtils.ApiConfig;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiConsumerListEjecution {
    private static final Logger logger = Logger.getLogger(ApiConsumerListEjecution.class.getName());
    public static String searchIdByName(String fraseABuscar, String autorizacion) {
        try {
            // URL de la API
            String url = ApiConfig.BASE_URL_LISTEJECUTION;

            // Crear la conexión HTTP
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configurar la solicitud
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Language",ApiConfig.ACCEPT_LANGUAGE );
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

                // Analizar la respuesta JSON con Gson
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(response.toString());
                JsonObject jsonResponse = jsonElement.getAsJsonObject();
                JsonArray data = jsonResponse.getAsJsonObject("data").getAsJsonArray("data");

                // Buscar el ID por la frase
                for (JsonElement item : data) {
                    JsonObject jsonObject = item.getAsJsonObject();
                    String name = jsonObject.get("name").getAsString();
                    if (name.contains(fraseABuscar)) {
                        return jsonObject.get("id").getAsString();
                    }
                }
            } else {
                logger.log(Level.SEVERE, "Error en la solicitud HTTP:", responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Si no se encuentra la frase, devolver null
        return null;
    }


}
