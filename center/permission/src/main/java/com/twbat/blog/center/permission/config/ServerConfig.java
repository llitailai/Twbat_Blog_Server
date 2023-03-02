package com.twbat.blog.center.permission.config;

import com.twbat.blog.center.permission.global.web.RequestUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 21:28
 * @desciption
 * 服务配置项
 */
@Component
public class ServerConfig {

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     * @return 服务地址
     */
    public String getUrl() {
        HttpServletRequest request = RequestUtil.getRequest();
        return getDomain(request);
    }
}
