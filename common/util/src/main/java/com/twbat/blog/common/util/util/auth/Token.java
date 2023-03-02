package com.twbat.blog.common.util.util.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.Profiles;
import com.twbat.blog.common.util.util.config.Constant;

/**
 * @author darkltl
 * @className Token
 * @email darkltl@163.com
 * @date 2021/8/16 - 13:34
 * @description
 */
public class Token {

    /**
     * 正常token状态标识
     */
    public static final Integer TOKEN_FLAG = 0;
    /**
     * 失效token状态标识
     */
    public static final Integer TOKEN_NO_FLAG = 1;
    private static String key;
    private static Integer outDay;

    /**
     * 生成Token
     *
     * @param userId   用户ID
     * @param password 用户加密密码
     * @return 生成完毕的token
     */
    public static String createToken(Integer userId, String password) {
        return JWT.create()
                .withClaim(Constant.USER_ID.getCode(), userId)
                .withClaim(Constant.USER_PASSWORD.getCode(), password)
                .withExpiresAt(DateUtils.addDays(DateUtils.getNowDate(), getOutDay()))
                .sign(Algorithm.HMAC256(getKey()));
    }


    /**
     * 获取token信息 如果token有误则返回null
     *
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(getKey())).build().verify(token);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户ID
     *
     * @param decodedJWT
     * @return
     */
    public static Integer getUserId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(Constant.USER_ID.getCode()).asInt();
    }


    /**
     * 是否需要重新生成Token
     *
     * @param decodedJWT
     * @param password
     * @return
     */
    public static boolean isAgainCreateToken(DecodedJWT decodedJWT, String password) {
        return decodedJWT.getExpiresAt().before(DateUtils.addDays(DateUtils.getNowDate(), getOutDay())) || isUpdatePassword(password, decodedJWT);
    }


    /**
     * 是否修改密码
     *
     * @param password
     * @param decodedJWT
     * @return
     */
    private static boolean isUpdatePassword(String password, DecodedJWT decodedJWT) {
        String oldPassword = decodedJWT.getClaim(getKey()).asString();
        return oldPassword.equals(password);
    }


    /**
     * 获取Key
     *
     * @return Key
     */
    private static synchronized String getKey() {
        if (key != null) {
            return Token.key;
        }
        Token.key = Profiles.getValue(Constant.TOKEN_SECRET_NAME.getCode()).toString();
        return Token.key;
    }

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    private static synchronized int getOutDay() {
        if (outDay != null) {
            return outDay;
        }
        Token.outDay = Integer.valueOf(Profiles.getValue(Constant.TOKEN_BLOG_OUT_DAY.getCode()).toString());
        return outDay;
    }


    public static void main(String[] args) {
        System.out.println(createToken(1, "abcdefg"));
    }

}
