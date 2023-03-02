package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.template;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.Key;

public enum SqlTemplate {
    SELECT_TEMPLATE(" SELECT " + Key.SELECT_FIELD_KEY.key() + " FROM " + Key.TABLE_NAME_kEY.key() + " " + Key.TABLE_ALIAS_NAME_KEY.key() + " " + Key.JOIN_KEY.key() + " WHERE " + Key.WHERE_KEY.key()),
    INSERT_TEMPLATE(" INSERT INTO " + Key.INSERT_TABLE_NAME_KEY.key() + " " + Key.INSERT_FIELD_KEY.key() + " VALUES " + Key.INSERT_VALUES_KEY.key()),
    UPDATE_TEMPLATE(" UPDATE " + Key.UPDATE_TABLE_NAME_KEY.key() + " SET " + Key.UPDATE_SET_KEY.key() + " WHERE " + Key.UPDATE_WHERE_KEY.key());
    private final String val;

    SqlTemplate(final String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
