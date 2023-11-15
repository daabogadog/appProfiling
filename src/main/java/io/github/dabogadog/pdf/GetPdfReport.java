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
    public static void summary(String buildName, String lambdaToken){
        String idVariable = searchIdByName(buildName, lambdaToken);
        //Obtiene el sesssioniD de cada test
        String sessionIds = getSessionIds(idVariable, lambdaToken);
        //Dividir la cadena en función de las comas
        String[] sessionIdsArray = sessionIds.split(",");

        ConsolidadoTestBatteryTemp consolidadoTestBattery = getBatteryTempMetrics(sessionIdsArray);
        System.out.println("battery temperature done");
        ConsolidadoTestCpuMem consolidadoTestCpu = getCpuMetrics(sessionIdsArray, "cpu");
        System.out.println("cpu done");
        ConsolidadoTestCpuMem consolidadoTestMem = getMemMetrics(sessionIdsArray, "mem");
        System.out.println("mem done");
        ConsolidadoTestFps consolidadoTestFps = getFpsMetrics(sessionIdsArray);
        System.out.println("fps done");

        generatePdf(consolidadoTestBattery,consolidadoTestCpu,consolidadoTestMem,consolidadoTestFps);
    }
}
