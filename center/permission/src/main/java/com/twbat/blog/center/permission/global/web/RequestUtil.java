package com.twbat.blog.center.permission.global.web;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author darkltl
 * @date 2021-09-08 13:09
 * @description web请求工具
 */
public class RequestUtil {

    public static String getHeader(String name) {
        return getRequest().getHeader(name);
    }

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getServletRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }
}
