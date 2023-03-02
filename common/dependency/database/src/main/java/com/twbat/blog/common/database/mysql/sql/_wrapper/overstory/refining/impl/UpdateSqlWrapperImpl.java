package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.impl;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.Key;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql.ConnectMethod;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.global.GlobalAttribute;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.UpdateSqlWrapper;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.template.SqlTemplate;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.util.FieldUtil;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.util.ParamUtil;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.SQLDefine;

import java.util.*;

public class UpdateSqlWrapperImpl<T> implements UpdateSqlWrapper<T> {

    private GlobalAttribute globalAttribute;

    private List<String> set = new ArrayList<>();

    private String where = "";

    public UpdateSqlWrapperImpl() {
        this.globalAttribute = GlobalAttribute.getInstance();
    }

    @Override
    public UpdateSqlWrapper<T> tableName(String tableName) {
        globalAttribute.putTableName(tableName);
        return this;
    }

    @Override
    public UpdateSqlWrapper<T> tableName(String tableName, String alias) {
        globalAttribute.putTableName(tableName).putAliasName(alias);
        return this;
    }

    @Override
    public String getSql() {
        String val = SqlTemplate.UPDATE_TEMPLATE.getVal();
        StringBuilder builder = new StringBuilder();
        for (int i = 0, len = set.size(); i < len; i++) {
            String str = set.get(i);
            if (i != 0) {
                builder.append(", " + str);
            } else {
                builder.append(str);
            }
        }
        String sql = val.replace(Key.UPDATE_TABLE_NAME_KEY.key(), globalAttribute.getTableName().get()).replace(Key.UPDATE_SET_KEY.key(), builder.toString()).replace(Key.UPDATE_WHERE_KEY.key(), where);
        renew();
        return sql;
    }


    private void renew() {
        where = "";
        set = new ArrayList<>();
    }

    @Override
    public UpdateSqlWrapper<T> update(T entity) {
        HashMap<String, Object> entityMap = FieldUtil.convertHashMapByEntity(entity);
        if (entityMap == null || entityMap.isEmpty()) {
            throw new SqlParseException(String.format("the entity is no field , entity: {}", entity));
        }
        Iterator<Map.Entry<String, Object>> iterator = entityMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            set.add(next.getKey() + " " + SQLDefine.EQ.getKey() + " " + next.getValue());
        }
        return this;
    }

    @Override
    public UpdateSqlWrapper<T> update(FieldFunction<T, ?> selectField, Object param) {
        param = ParamUtil.formatParam(param);
        set.add(FieldUtil.getFiledName(selectField) + " " + SQLDefine.EQ.getKey() + " " + param);
        return this;
    }

    @Override
    public UpdateSqlWrapper<T> update(Boolean condition, FieldFunction<T, ?> selectField, Object param) {
        if (condition) {
            update(selectField, param);
        }
        return this;
    }

    @Override
    public UpdateSqlWrapper<T> where(FieldFunction<T, ?> fieldFunction, Object param, SQLDefine contact, ConnectMethod connectMethod) {
        String filedName = FieldUtil.getFiledName(fieldFunction);
        String connect = "";
        if (connectMethod != null) {
            connect = connectMethod.getVal();
        }
        if (param instanceof String || param instanceof Date) {
            param = "'" + param + "'";
        }
        where += filedName + contact.getKey() + param + " " + connect + " ";
        return this;
    }


//    public static void main(String[] args) {
//        UpdateSqlWrapperImpl<TableA> updateSqlWrapper = new UpdateSqlWrapperImpl<>();
//        TableA tableA = new TableA();
//        tableA.setId(1);
//        tableA.setAge(null);
//        tableA.setName("a");
//        System.out.println(updateSqlWrapper.tableName("abc").update(tableA).where(TableA::getId, 1, SQLDefine.NE, ConnectMethod.AND).where(TableA::getAge, 2, SQLDefine.EQ, null).getSql());
//        System.out.println(updateSqlWrapper.tableName("tbd").update(TableA::getAge, 1).update(TableA::getName, "张三").where(TableA::getId, 1, SQLDefine.EQ, ConnectMethod.AND).where(TableA::getAge, 2, SQLDefine.NE, null).getSql());
//    }
}
