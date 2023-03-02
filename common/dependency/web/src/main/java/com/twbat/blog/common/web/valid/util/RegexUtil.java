package com.twbat.blog.common.web.valid.util;

/**
 * @author darkltl
 * @date 2021-09-09 10:09
 * 正则工具类
 */
public class RegexUtil {


    /**
     * 手机号正则表达式校验公式
     */
    private static final String MOBILE_REGEX = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    /**
     * 密码正则表达式校验公式
     * 至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符
     */
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$";


    /**
     * 邮箱验证正则表达式
     */
    private static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 校验是否是手机号
     *
     * @param tel
     * @return
     */
    public static boolean isTel(String tel, String regex) {
        if (StringUtils.isEmpty(regex)) {
            return tel.matches(MOBILE_REGEX);
        }
        return tel.matches(regex);
    }

    /**
     * 校验是否是密码
     *
     * @param password 密码文本
     * @param regex    正则表达式
     * @return boolean
     * true : 符合条件
     * false : 不符合条件
     */
    public static boolean isPassword(String password, String regex) {
        if (StringUtils.isEmpty(regex)) {
            return password.matches(PASSWORD_REGEX);
        }
        return password.matches(regex);
    }

    /**
     * 判断是否是数字类型
     *
     * @param obj 参数
     * @return boolean
     * true : 是数值
     * false : 不是数值
     */
    public static boolean isNumber(Object obj) {
        return obj instanceof Number;
    }

    public static boolean isEmail(String email,String regex) {
        if (StringUtils.isEmpty(regex)) {
            return email.matches(EMAIL_REGEX);
        }
        return email.matches(regex);
    }
}
