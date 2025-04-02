package java_core_api.api_java_core.utils;

import jakarta.servlet.http.HttpServletRequest;

public class BaseUrl {

    public static String getBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}
