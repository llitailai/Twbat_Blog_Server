package com.twbat.blog.common.web.valid.util;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author darkltl
 * @version jdk1.8
 * @email darkltl@163.com
 * @date 2021/9/10 20:29
 * @description
 * @since 1.0
 * ip地址工具类
 */
public class IpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);

    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request 请求
     * @return ip地址字符串
     * @see https://www.cnblogs.com/pxblog/p/13360768.html
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            LOGGER.info("通过X-Real-IP 成功获取IP,IP: {}", ip);
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            LOGGER.info("通过X-Forwarded-For 成功获取IP,IP: {}", ip);
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
        } else {
            LOGGER.info("通过Remote 成功获取IP,IP: {}", ip);
            ip = request.getRemoteAddr();
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = "127.0.0.1";
            }
        }
        return ip;

    }
}
