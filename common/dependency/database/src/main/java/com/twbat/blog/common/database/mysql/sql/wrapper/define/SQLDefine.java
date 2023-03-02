package com.twbat.blog.common.database.mysql.sql.wrapper.define;

/**
 * @author darkltl
 * @date 2021-09-22 11:32
 * SQL关键词
 */
public enum SQLDefine {
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
    LIKE("LIKE"),
    LIKE_SYMBOL("%"),
    INTO("INTO"),
    ORDER_BY("ORDER BY"),
    GROUP_BY("GROUP_BY"),
    ASC("ASC"),
    AS("AS"),
    DESC("DESC"),
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")"),
    EQ("="),
    NE("!="),
    LT("<"),
    GT(">"),
    ON("ON"),
    SET("SET"),
    VALUES("VALUES"),
    VALUE("VALUE");

    private final String key;

    private SQLDefine(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
