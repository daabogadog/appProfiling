package io.github.dabogadog;

import io.github.dabogadog.models.BateryTemp.ConsolidadoTestBatteryTemp;
import io.github.dabogadog.models.CpuMem.ConsolidadoTestCpuMem;
import io.github.dabogadog.models.Rendering.ConsolidadoTestFps;

import static io.github.dabogadog.Pdf.ChartToPdf.generatePdf;
import static io.github.dabogadog.listEjecution.ApiConsumerListEjecution.searchIdByName;
import static io.github.dabogadog.sessionIds.ApiSessionIds.getSessionIds;
import static io.github.dabogadog.utils.ManageBatteryTempData.getBatteryTempMetrics;
import static io.github.dabogadog.utils.ManageCpuData.getCpuMetrics;
import static io.github.dabogadog.utils.ManageCpuData.getMemMetrics;
import static io.github.dabogadog.utils.ManageFpsData.getFpsMetrics;

public class Main {
    public static void main(String[] args) {

        //Datos enviados
        String name = "Regresion Android Banca Movil Av Villas Jenkins Build No.380";
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
        ConsolidadoTestBatteryTemp consolidadoTestBattery = getBatteryTempMetrics(sessionIdsArray);
        ConsolidadoTestFps consolidadoTestFps = getFpsMetrics(sessionIdsArray);


        printData(consolidadoTestCpu, consolidadoTestMem,consolidadoTestBattery,consolidadoTestFps);
        generatePdf(consolidadoTestBattery);

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

    public static void printData(ConsolidadoTestCpuMem consolidadoTestCpu, ConsolidadoTestCpuMem consolidadoTestMem, ConsolidadoTestBatteryTemp consolidadoTestBattery, ConsolidadoTestFps consolidadoTestFps){
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
        System.out.println("Indicadores de bateria- nivel maximo -> " + consolidadoTestBattery.batteryMax);
        System.out.println("Indicadores de bateria- nivel minimo -> " + consolidadoTestBattery.batteryMin);
        System.out.println("Indicadores de bateria- nivel promedio -> " + consolidadoTestBattery.batteryAvg);
        System.out.println("Indicadores de temperatura-  maximo -> " + consolidadoTestBattery.temperatureMax);
        System.out.println("Indicadores de temperatura-  minimo -> " + consolidadoTestBattery.temperatureMin);
        System.out.println("Indicadores de temperatura-  promedio -> " + consolidadoTestBattery.temperatureAvg);
        System.out.println("Indicadores de rendering-  maximo -> " + consolidadoTestFps.fpsMax);
        System.out.println("Indicadores de rendering-  minimo -> " + consolidadoTestFps.fpsMin);
        System.out.println("Indicadores de rendering-  promedio -> " + consolidadoTestFps.fpsAvg);
    }

}
