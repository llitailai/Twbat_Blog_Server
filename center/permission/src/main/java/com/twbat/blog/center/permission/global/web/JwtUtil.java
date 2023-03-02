package com.twbat.blog.center.permission.global.web;

import com.twbat.blog.common.util.util.auth.Token;
import com.twbat.blog.common.util.util.config.Constant;

/**
 * @author darkltl
 * @date 2021-09-08 13:13
 * @description token工具
 */
public class JwtUtil {

    public static String getToken() {
        return RequestUtil.getRequest().getHeader(Constant.TOKEN_HEADER.getCode()).split(Constant.TOKEN_PREFIX.getCode())[1].trim();
    }

    public static Integer getUserId() {
        return Token.getUserId(Token.getTokenInfo(getToken()));
    }
}
