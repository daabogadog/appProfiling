package io.github.dabogadog;

import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.github.dabogadog.listEjecution.ApiConsumerListEjecution.searchIdByName;
import static io.github.dabogadog.sessionIds.ApiSessionIds.getSessionIds;

public class Main {
    public static void main(String[] args) {

        //Datos enviados
        String name = "Regresion Android Banca Movil Av Villas Jenkins Build No.364";
        String AuthorizationBearer = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImE2ZDUyY2M0ODMxY2U5NDRkMWFmMmVhZWY2NDZkZmQ4ZTc2ODBlMTAyYWE4YmJkOTc3ODZjMDVmZjM2ZWY4M2E4YTg1ZDU1YzQ3MWViM2Y4In0.eyJhdWQiOiIzIiwianRpIjoiYTZkNTJjYzQ4MzFjZTk0NGQxYWYyZWFlZjY0NmRmZDhlNzY4MGUxMDJhYThiYmQ5Nzc4NmMwNWZmMzZlZjgzYThhODVkNTVjNDcxZWIzZjgiLCJpYXQiOjE2OTY1MzU5MDMsIm5iZiI6MTY5NjUzNTkwMywiZXhwIjoxNzI4MTU4MzAzLCJzdWIiOiI4Nzk3NzciLCJzY29wZXMiOltdLCJpZCI6ODc5Nzc3LCJlbWFpbCI6ImRhbmllbC5hYm9nYWRvQGF2YWxkaWdpdGFsbGFicy5jb20iLCJ1c2VybmFtZSI6ImRhbmllbC5hYm9nYWRvIiwibmFtZSI6ImRhbmllbCBhYm9nYWRvIiwib3JnYW5pemF0aW9uX2lkIjo2Nzg3ODksIm9yZ2FuaXphdGlvbl9yb2xlIjoiQWRtaW4iLCJpbnRlcmNvbV91c2VyX2hhc2giOiJlYTYxN2QyNDNlY2M0NWE1N2UyMzc3MDlhODdhMjBlYzgwMmYxYTZmMzY1YTZhNWIzMzcyMGM1M2JkNWZhZGRhIiwiY3JlYXRlZF9hdCI6IjIwMjItMDYtMzBUMTM6MTg6MjkuMDAwMDAwWiIsImlzcyI6bnVsbH0.OvB6Hkp0rFTg-AGmP9S7Jilb9Lsl4zFo7pe9R0rpq9Xu1ZQUF1x_zwyiDsyM1vHOi1-xx7J7ZtAR0aawPxtzbTPmAFnk1ip7WfSZhwAsJZ0GFDAxp3xo1O6mY4lqKm34BDhuQMHHRWYRmZ9pLC088lvVbt5i7D1OJOy9aDsTdHshmuf-JMM3ecTQxwiX3za9OXHiiidqhFxwwbJhhKhyjO4GZTMc97MDmahwQTPyG5KrUgEfZzbE746xJFf7uBjEYZ4OPMlwm52rRhWzq7WDqrCn5RtWEwJN0pr_RMMK_kP0gebBrw_fxISc2Oe9k8u-tm-sUIRuN6SQXQLKq-BVaaiydFPwCIU4KcHlBZjRaoHVWUJprl9v2jJAGKchqVDJAWek8jC9rIPxmqNYjbXIch-5LCrrnCZibThQlYkEnxGcT1UxUHXO7PP-FNRzNa8Vp2_jhh7JmP7y7PeHWCbbqaE0Wfn9hZxNUEGXgJSKgNAFIw7nGZzXtAkJRPBONJdAR15q4SE_6Ej3ICfHUJkh57RMc9qJZ4B96XirI0iIufvpBhnBFAMJa26wUz56ZmTLfN2OPb3mZNbyNZmaSzNenVuXCyy0_yEEBxvSTePyS0jI_0XiFl-kwQa7YCyhlnY1BswPYjv5StGUYqvcyrRk80SR5GEfo53pvGoxIGdyqaI";
        String AuthorizationBasic = "Basic ZGFuaWVsLmFib2dhZG86dVFtV0tLODlFYTJPazV6d1c2TlQ0aWQ2RGJQTlZqNnpNNFc5bm0xcXBDaENMZXg0TVI=";
        //Obtiene el id de grupo de test
        String idVariable = searchIdByName(name, AuthorizationBearer);
        //Obtiene el sesssioniD de cada test
        String sessionIds = getSessionIds(idVariable, AuthorizationBearer);
        //Dividir la cadena en función de las comas
        String[] sessionIdsArray = sessionIds.split(",");

        ConsolidadoTestCpuMem consolidadoTestCpu = getCpuMetrics(sessionIdsArray, "cpu");
        ConsolidadoTestCpuMem consolidadoTestMem = getMemMetrics(sessionIdsArray, "mem");

        System.out.println("Indicadores de cpu de la app- consumo maximo -> " + consolidadoTestCpu.appMax);
        System.out.println("Indicadores de cpu de la app- consumo minimo -> " + consolidadoTestCpu.appMin);
        System.out.println("Indicadores de cpu de la app- consumo promedio -> " + consolidadoTestCpu.appAvg);
        System.out.println("Indicadores de memoria de la app- consumo maximo -> " + consolidadoTestMem.appMax);
        System.out.println("Indicadores de memoria de la app- consumo minimo -> " + consolidadoTestMem.appMin);
        System.out.println("Indicadores de memoria de la app- consumo promedio -> " + consolidadoTestMem.appAvg);
        System.out.println("Indicadores de cpu del sistema- consumo maximo -> " + consolidadoTestCpu.sysMax);
        System.out.println("Indicadores de cpu del sistema- consumo minimo -> " + consolidadoTestCpu.sysMin);
        System.out.println("Indicadores de cpu del sistema- consumo promedio -> " + consolidadoTestCpu.sysAvg);
        System.out.println("Indicadores de memoria del sistema- consumo maximo -> " + consolidadoTestMem.sysMax);
        System.out.println("Indicadores de memoria del sistema- consumo minimo -> " + consolidadoTestMem.sysMin);
        System.out.println("Indicadores de memoria del sistema- consumo promedio -> " + consolidadoTestMem.sysAvg);

        //Llamado a cada api - pendiente el llamado a todos y no solo al primero
//        String responseJsonApiProfiling = ApiProfiling.obtainApiProfiling(sessionIdsArray[1].trim(), AuthorizationBasic);
//        String responseJsonApiCpuMemory = ApiCpuMemory.obtainApiCpuMemory(sessionIdsArray[1].trim(), AuthorizationBasic);
//        String responseJsonApiNetwork = ApiNetwork.obtainApiNetwork(sessionIdsArray[1].trim(), AuthorizationBasic);
//        String responseJsonApiRendering = ApiRendering.obtainApiRendering(sessionIdsArray[1].trim(), AuthorizationBasic);
//        String responseJsonApiBattery = ApiBattery.obtainApiApiBattery(sessionIdsArray[1].trim(), AuthorizationBasic);
//        //Resultado de la consulta
//        System.out.println("ID encontrado para " + name + ": " + idVariable);
//        System.out.println("Session IDs: " + sessionIds);
//        System.out.println("Respuesta JSON ApiProfiling: " + responseJsonApiProfiling);
//        System.out.println("Respuesta JSON ApiCpuMemory: " + responseJsonApiCpuMemory);
//        System.out.println("Respuesta JSON ApiNetwork: " + responseJsonApiNetwork);
//        System.out.println("Respuesta JSON ApiRendering: " + responseJsonApiRendering);
//        System.out.println("Respuesta JSON ApiBattery: " + responseJsonApiBattery);
//
//        List<String> sessionIdList = convertToSessionIdList(sessionIds);
//        List<String> listaApiProfilingLleno = new ArrayList<>();
//        for (String sessionId : sessionIdList) {
//           // System.out.println(sessionId);
//            listaApiProfilingLleno.add(ApiProfiling.obtainApiProfiling(sessionId, AuthorizationBasic));
//        }
//        System.out.println("lista lista ApiProfiling Lleno \n" + listaApiProfilingLleno);
//        String sumJson = sumMetrics(listaApiProfilingLleno);
//        // Imprimir el JSON de suma resultante
//        System.out.println("JSON de suma:\n" + sumJson);
//
//

    }

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

    public static List<Cpu> getAllCpuMemDataFromBuild(String sessionID, String type) {
        Type listType = new TypeToken<List<Cpu>>() {
        }.getType();

        return RestAssured.given().when().get("https://jhoan.garcia:8WDbXsiNt2x9snBBcCgC4JGIok8cSs5Isow0kNuK5UxsCIi2Xu@mobile-api.lambdatest.com/mobile-automation/api/v1/sessions/" + sessionID + "/log/appprofile?metricstype=" + type)
                .then().extract().as(listType);
    }
}
