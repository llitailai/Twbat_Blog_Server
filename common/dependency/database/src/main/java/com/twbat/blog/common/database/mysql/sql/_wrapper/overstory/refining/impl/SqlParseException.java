package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.impl;

public class SqlParseException extends RuntimeException {

    public SqlParseException() {
        super();
    }

    public SqlParseException(String message) {
        super(message);
    }

    public SqlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlParseException(Throwable cause) {
        super(cause);
    }

    protected SqlParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
