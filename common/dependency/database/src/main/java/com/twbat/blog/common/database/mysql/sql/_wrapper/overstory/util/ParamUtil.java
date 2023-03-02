package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.util;

import java.util.Date;

/**
 * 参数工具
 *
 * @author darkltl
 * @date 2022-01-01 10:04
 */
public class ParamUtil {

    public static String formatParam(Object param) {
        if (param instanceof String || param instanceof Date) {
            param = "'" + param + "'";
        }
        return param + "";
    }
}
