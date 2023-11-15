package io.github.dabogadog.pdf;

import io.github.dabogadog.models.BateryTemp.ConsolidadoTestBatteryTemp;
import io.github.dabogadog.models.CpuMem.ConsolidadoTestCpuMem;
import io.github.dabogadog.models.Rendering.ConsolidadoTestFps;
import static io.github.dabogadog.listEjecution.ApiConsumerListEjecution.searchIdByName;
import static io.github.dabogadog.pdf.ChartToPdf.generatePdf;
import static io.github.dabogadog.sessionIds.ApiSessionIds.getSessionIds;
import static io.github.dabogadog.utils.ManageBatteryTempData.getBatteryTempMetrics;
import static io.github.dabogadog.utils.ManageCpuData.getCpuMetrics;
import static io.github.dabogadog.utils.ManageCpuData.getMemMetrics;
import static io.github.dabogadog.utils.ManageFpsData.getFpsMetrics;

public class GetPdfReport {
    public static void summary(String buildName, String lambdaToken) {
        String[] sessionIdsArray = getSessionIdsArray(buildName, lambdaToken);

        ConsolidadoTestBatteryTemp consolidadoTestBattery = getBatteryTempMetrics(sessionIdsArray);
        ConsolidadoTestCpuMem consolidadoTestCpu = getCpuMetrics(sessionIdsArray, "cpu");
        ConsolidadoTestCpuMem consolidadoTestMem = getMemMetrics(sessionIdsArray, "mem");
        ConsolidadoTestFps consolidadoTestFps = getFpsMetrics(sessionIdsArray);

        generatePdf(consolidadoTestBattery, consolidadoTestCpu, consolidadoTestMem, consolidadoTestFps);
    }

    public static String[] getSessionIdsArray(String buildName, String lambdaToken) {
        String idVariable = searchIdByName(buildName, lambdaToken);
        //Obtiene el sesssioniD de cada test
        String sessionIds = getSessionIds(idVariable, lambdaToken);
        //Dividir la cadena en función de las comas
        return sessionIds.split(",");
    }
}
