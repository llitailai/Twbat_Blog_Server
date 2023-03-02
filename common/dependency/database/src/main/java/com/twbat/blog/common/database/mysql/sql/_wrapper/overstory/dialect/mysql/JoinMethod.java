package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql;

public enum JoinMethod {
    INNER_JOIN("JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    FULL_JOIN("FULL JOIN");

    private final String value;

    JoinMethod(final String value) {
        this.value = value;
    }

    public final String getValue() {
        return value;
    }
}
