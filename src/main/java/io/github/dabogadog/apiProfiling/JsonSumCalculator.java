package io.github.dabogadog.apiProfiling;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class JsonSumCalculator {
    public static String sumMetrics(List<String> jsonInputs) {
        JsonObject sumJsonObject = new JsonObject();

        for (String jsonInput : jsonInputs) {
            try {
                // Parsear el JSON de entrada
                JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();

                // Iterar a través de las claves en el JSON de entrada
                for (String key : jsonObject.keySet()) {
                    // Verificar si el campo existe en sumJsonObject
                    if (sumJsonObject.has(key)) {
                        // Verificar el tipo del campo y sumar en consecuencia
                        if (jsonObject.get(key).isJsonPrimitive()) {
                            if (jsonObject.get(key).isJsonNull()) {
                                continue; // Salta los campos nulos
                            }
                            if (jsonObject.get(key).getAsJsonPrimitive().isNumber()) {
                                // Campo numérico
                                sumJsonObject.addProperty(key, sumJsonObject.get(key).getAsDouble() + jsonObject.get(key).getAsDouble());
                            } else if (jsonObject.get(key).getAsJsonPrimitive().isString()) {
                                // Campo de cadena, asume que es en "ms" y realiza la conversión
                                int msValue = extractAndConvertToMs(jsonObject.get(key).getAsString());
                                int currentValue = extractAndConvertToMs(sumJsonObject.get(key).getAsString());
                                sumJsonObject.addProperty(key, msValue + currentValue + " ms");
                            }
                        } else {
                            // Otros tipos de campos no se sumarán
                        }
                    } else {
                        // Si el campo no existe en sumJsonObject, simplemente copia el valor
                        sumJsonObject.add(key, jsonObject.get(key));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sumJsonObject.toString();
    }

    // Método para extraer y convertir los valores "ms" a números
    private static int extractAndConvertToMs(String value) {
        try {
            return Integer.parseInt(value.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0; // Devolver 0 si no se puede convertir
        }
    }

}
