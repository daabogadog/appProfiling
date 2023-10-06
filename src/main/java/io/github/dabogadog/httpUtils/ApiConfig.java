package io.github.dabogadog.httpUtils;

public class ApiConfig {

    public static final String BASE_URL_LISTEJECUTION = "https://beta-api.lambdatest.com/mobile/v1.0/build?offset=0&timezone=300&limit=20";
    public static final String BASE_URL_SESSIONID = "https://beta-api.lambdatest.com/mobile/v1.0/tests?buildid=";
    public static final String PARAMS_SESSIONID = "&offset=0&limit=1500&timezone=300";
    public static final String BASE_URL = "https://jhoan.garcia:8WDbXsiNt2x9snBBcCgC4JGIok8cSs5Isow0kNuK5UxsCIi2Xu@mobile-api.lambdatest.com/mobile-automation/api/v1/sessions/";
    public static final String PARAMS_APIBATTERY = "/log/appprofile?metricstype=battery";
    public static final String PARAMS_CPUMEMORY = "/log/appprofile?metricstype=cpu";
    public static final String PARAMS_APINETWORK = "/log/appprofile?metricstype=network";
    public static final String PARAMS_APIPROFILING = "/log/appprofile?metricstype=meta";
    public static final String PARAMS_APIRENDERING = "/log/appprofile?metricstype=fps";
    public static final String ACCEPT_LANGUAGE = "es-419,es;q=0.9";
    public static final String ACCEPT = "application/json, text/plain, */*";


}
