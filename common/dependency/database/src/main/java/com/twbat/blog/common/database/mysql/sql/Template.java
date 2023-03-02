package com.twbat.blog.common.database.mysql.sql;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author darkltl
 * @date 2021-09-22 14:03
 * SQL模板定义
 */
public interface Template {

    String easySql(String tableName, LinkedHashMap<String, Object> param, ArrayList<Integer> method);


    String joinSql();
}
