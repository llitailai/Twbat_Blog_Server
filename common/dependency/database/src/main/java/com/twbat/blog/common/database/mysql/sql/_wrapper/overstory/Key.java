package com.twbat.blog.common.database.mysql.sql._wrapper.overstory;

/**
 * @author darkltl
 * @date 2021-10-12 10:24
 * @description 关键字
 */
public enum Key {
    SELECT_FIELD_KEY("SFK0001"),
    TABLE_NAME_kEY("TNK0002"),
    TABLE_ALIAS_NAME_KEY("TANK0003"),
    WHERE_KEY("WK0004"),
    JOIN_KEY("JK0005"),
    INSERT_FIELD_KEY("IFK0006"),
    INSERT_TABLE_NAME_KEY("ITNK0007"),
    INSERT_VALUES_KEY("IVK0008"),
    UPDATE_TABLE_NAME_KEY("UTNK0009"),
    UPDATE_SET_KEY("USK0010"),
    UPDATE_WHERE_KEY("UWK0011"),
    ;

    private final String key;

    private Key(final String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
