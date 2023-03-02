package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql;

public enum ConnectMethod {
    AND("AND"),
    OR("OR");

    private final String val;

    ConnectMethod(final String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
