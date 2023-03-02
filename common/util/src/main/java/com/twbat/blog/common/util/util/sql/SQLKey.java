package com.twbat.blog.common.util.util.sql;

/**
 * @author darkltl
 * @date 2021-09-22 11:32
 * SQL关键词
 */
public enum SQLKey {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    IF_NULL("IFNULL"),
    ALL("*"),
    FROM("FROM"),
    WHERE("WHERE"),
    IN("IN"),
    AND("AND"),
    OR("OR"),
    LIMIT("LIMIT"),
    ORDER_BY("ORDER BY"),
    GROUP_BY("GROUP_BY"),
    ASC("ASC"),
    DESC("DESC"),
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")");

    private final String key;

    private SQLKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
