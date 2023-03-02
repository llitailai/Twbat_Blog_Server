package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.impl;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql.JoinMethod;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql.LikeMethod;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.global.GlobalAttribute;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.SelectSqlWrapper;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.util.FieldUtil;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.util.ParamUtil;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.SQLDefine;

import java.util.ArrayList;
import java.util.List;

public class SelectSqlWrapperImpl<T> implements SelectSqlWrapper<T> {

    private final List<String> selectFields = new ArrayList<>();

    private GlobalAttribute globalAttribute;

    private String where = "";

    private List<String> set = new ArrayList<>();

    public SelectSqlWrapperImpl() {
        globalAttribute = GlobalAttribute.getInstance();
    }

    @Override
    public SelectSqlWrapperImpl<T> select(FieldFunction<T, ?>... selectFields) {
        for (FieldFunction<T, ?> selectField : selectFields) {
            this.selectFields.add(FieldUtil.getFiledName(selectField));
        }
        return this;
    }

    @Override
    public SelectSqlWrapperImpl<T> tableName(String tableName) {
        globalAttribute.putTableName(tableName);
        return this;
    }

    @Override
    public SelectSqlWrapperImpl<T> tableName(String tableName, String alias) {
        globalAttribute.putTableName(tableName).putAliasName(alias);
        return this;
    }

    @Override
    public String getSql() {
        return null;
    }

    @Override
    public SelectSqlWrapper<T> eq(FieldFunction<T, ?> selectField, Object param) {
        return eq(true, selectField, param);
    }

    @Override
    public SelectSqlWrapper<T> eq(Boolean condition, FieldFunction<T, ?> selectField, Object param) {
        if (condition) {
            param = ParamUtil.formatParam(param);
            putSet(SQLDefine.EQ, selectField, param);
        }
        return this;
    }

    @Override
    public SelectSqlWrapper<T> lt(FieldFunction<T, ?> selectField, Object param) {
        return lt(true, selectField, param);
    }

    @Override
    public SelectSqlWrapper<T> lt(Boolean condition, FieldFunction<T, ?> selectField, Object param) {
        if (condition) {
            param = ParamUtil.formatParam(param);
            putSet(SQLDefine.LT, selectField, param);
        }
        return this;
    }

    @Override
    public SelectSqlWrapper<T> gt(FieldFunction<T, ?> selectField, Object param) {
        return gt(true, selectField, param);
    }

    @Override
    public SelectSqlWrapper<T> gt(Boolean condition, FieldFunction<T, ?> selectField, Object param) {
        if (condition) {
            param = ParamUtil.formatParam(param);
            putSet(SQLDefine.GT, selectField, param);
        }
        return this;
    }

    @Override
    public SelectSqlWrapper<T> ne(FieldFunction<T, ?> selectField, Object param) {
        return ne(true, selectField, param);
    }

    @Override
    public SelectSqlWrapper<T> ne(Boolean condition, FieldFunction<T, ?> selectField, Object param) {
        if (condition) {
            param = ParamUtil.formatParam(param);
            putSet(SQLDefine.NE, selectField, param);
        }
        return this;
    }

    @Override
    public SelectSqlWrapper<T> like(FieldFunction<T, ?> selectField, Object param) {
        return null;
    }

    @Override
    public SelectSqlWrapper<T> like(Boolean condition, FieldFunction<T, ?> selectField, Object param) {
        return null;
    }

    @Override
    public SelectSqlWrapper<T> like(FieldFunction<T, ?> selectField, Object param, LikeMethod likeMethod) {
        return null;
    }

    @Override
    public SelectSqlWrapper<T> like(Boolean condition, FieldFunction<T, ?> selectField, Object param, LikeMethod likeMethod) {
        return null;
    }

    @Override
    public JoinSqlWrapper<T> join(String joinTableName, String aliasName) {
        return null;
    }

    @Override
    public JoinSqlWrapper<T> join(Boolean condition, String joinTableName, String aliasName) {
        return null;
    }

    @Override
    public JoinSqlWrapper<T> join(String joinTableName, String aliasName, JoinMethod joinMethod) {
        return null;
    }

    @Override
    public JoinSqlWrapper<T> join(Boolean condition, String joinTableName, String aliasName, JoinMethod joinMethod) {
        return null;
    }

    @Override
    public SelectSqlWrapper<T> on(FieldFunction<T, ?> selectField) {
        return null;
    }

    @Override
    public SelectSqlWrapper<T> childSelect(String sql, String aliasName) {
        return null;
    }

    @Override
    public SelectSqlWrapper<T> childSelect(Boolean condition, String sql, String aliasName) {
        return null;
    }

    private void putSet(SQLDefine sqlDefine, FieldFunction<T, ?> selectField, Object param) {
        set.add(FieldUtil.getFiledName(selectField) + " " + sqlDefine.getKey() + " " + param);
    }
}
