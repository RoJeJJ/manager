package com.roje.web.server.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roje.web.server.common.ResultCode;
import com.roje.web.server.common.RspBody;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Ro
 */
public class HttpUtils {

    private static ObjectMapper defaultMapper = new ObjectMapper();

    private HttpUtils() {
    }

    public static void response(HttpStatus status, final HttpServletResponse response) throws IOException {
        response.setStatus(status.value());
        response.getWriter().write(status.name());
        response.flushBuffer();
    }

    public static <DATA> void sendJsonData(ResultCode code, DATA data, final HttpServletResponse response) throws IOException {
        RspBody<DATA> rsp = RspBody.<DATA>builder().code(code).data(data).build();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(defaultMapper.writeValueAsString(rsp));
        response.flushBuffer();
    }

    public static void sendJsonData(ResultCode code, final HttpServletResponse response) throws IOException {
        sendJsonData(code, null, response);
    }


    public static boolean isAjaxRequest(final HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With")));
    }

    public static RequestMatcher requestMethodMatcher(HttpMethod method) {
        return request -> method.name().equals(request.getMethod());
    }
}
