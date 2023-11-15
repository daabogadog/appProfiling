package io.github.dabogadog;

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

public class Main {
    public static void main(String[] args) {

        //Datos enviados
        String name = "23/11/07-16:45:27 [pipeline] - appCarroya_v.3.8.3.1.apk";
        String AuthorizationBearer = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjcyOTRmZDI5YzM3NmRlZGZkYmVmNDFlZWQ4ODEyNTViOTI4Yjg1OWYxMzI3NjgzMWRlNmMyNjdjYjgwM2ExZTE5ZDRmMWZlMDUxNTMzYjViIn0.eyJhdWQiOiI0IiwianRpIjoiNzI5NGZkMjljMzc2ZGVkZmRiZWY0MWVlZDg4MTI1NWI5MjhiODU5ZjEzMjc2ODMxZGU2YzI2N2NiODAzYTFlMTlkNGYxZmUwNTE1MzNiNWIiLCJpYXQiOjE2OTQxMDI3OTIsIm5iZiI6MTY5NDEwMjc5MiwiZXhwIjoxNzI1NzI1MTkyLCJzdWIiOiI4Nzk3NzciLCJzY29wZXMiOltdLCJpZCI6ODc5Nzc3LCJlbWFpbCI6ImRhbmllbC5hYm9nYWRvQGF2YWxkaWdpdGFsbGFicy5jb20iLCJ1c2VybmFtZSI6ImRhbmllbC5hYm9nYWRvIiwibmFtZSI6ImRhbmllbCBhYm9nYWRvIiwib3JnYW5pemF0aW9uX2lkIjo2Nzg3ODksIm9yZ2FuaXphdGlvbl9yb2xlIjoiQWRtaW4iLCJpbnRlcmNvbV91c2VyX2hhc2giOiJlYTYxN2QyNDNlY2M0NWE1N2UyMzc3MDlhODdhMjBlYzgwMmYxYTZmMzY1YTZhNWIzMzcyMGM1M2JkNWZhZGRhIiwiY3JlYXRlZF9hdCI6IjIwMjItMDYtMzBUMTM6MTg6MjkuMDAwMDAwWiIsImlzcyI6bnVsbH0.JRuw8opcFJ7jcGHnmvNceEntZuwuuQT077dVN2crzFE_xZ-nDr4L6KZ2nQ764bOC1K3r3zictmTrj0-gFv6RLLDwPWegZu6ZtYLnnFw3rPPbMb4bMQ5LQlsIGDzxOBRP5JM60GukUnvLT0wrwn589hUpePxJVDUxwjgvFjDBT-d6DUTJG5awqRnO-8X5Rpx4ciYxljxH5B_aqBWUZhiWXhebYC10Hmku3OvFbXIBaWHaRgW0UWB1VbJ-wokprxsv_rtrAShpzQBPRbiq8Zm178U_c1r-N1qAtb9OaYlXgAKKznaa9KXvs2xxkKsY3uu7DOle7tC3O_D4BLlOYldPciX7H20Q9T9dLDOUOsq8IGKJqUNeaOMY27PQEecvrr2BCS4YS4KDY84JHjUOsBQsCzVMuYNjSOC0X2BO21u_IwkT3aEwZDXjAKZ4oC31bh5hrfQwyURa9xNi3rDU0uqGKDxNDgcK8kLNElVHDTQ5HbD3TB90dVa-JjG4LEKyQxJR2oe1bclLxM_CWxXRsje-77Rk_HleH3YPZ_1V_asKYp0DAQxM7Ng5lpPnwU6gIC_wIXJj9PM53R4VOjPmne6Ws1R_Cd2Zbm-MK7DcyxyN3AsxPR98n1bXdep4l6o5mTAt250l4sTJkLiNYpWgIZ3FGfFmBgpXKLiJwrK_mBVuogI";
        //Obtiene el id de grupo de test
        String idVariable = searchIdByName(name, AuthorizationBearer);
        //Obtiene el sesssioniD de cada test
        String sessionIds = getSessionIds(idVariable, AuthorizationBearer);
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
