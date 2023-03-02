package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.impl;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.Key;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.global.GlobalAttribute;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.InsertSqlWrapper;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.impl.test.TableA;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.template.SqlTemplate;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.util.FieldUtil;

import java.util.*;

public class InsertSqlWrapperImpl<T> implements InsertSqlWrapper<T> {

    private GlobalAttribute globalAttribute;

    private List<String> set = new ArrayList<>();

    private List<Object> values = new ArrayList<>();

    public InsertSqlWrapperImpl() {
        this.globalAttribute = GlobalAttribute.getInstance();
    }

    public static void main(String[] args) {
        InsertSqlWrapperImpl<TableA> insertSqlWrapper = new InsertSqlWrapperImpl<>();
        insertSqlWrapper.tableName("asd", "sd");
//        System.out.println(GlobalAttribute.getInstance().getTableName()); success
//        System.out.println(GlobalAttribute.getInstance().getAliasName()); success
        TableA tableA = new TableA();
        tableA.setId(1);
        tableA.setAge(null);
        tableA.setName("a");
        System.out.println(insertSqlWrapper.set(TableA::getAge, 10).set(TableA::getId, 1).set(TableA::getName, "张三").getSql());
    }

    @Override
    public InsertSqlWrapper<T> tableName(String tableName) {
        globalAttribute.putTableName(tableName);
        return this;
    }

    @Override
    public InsertSqlWrapper<T> tableName(String tableName, String alias) {
        globalAttribute.putTableName(tableName).putAliasName(alias);
        return this;
    }

    @Override
    public String getSql() {
        String val = SqlTemplate.INSERT_TEMPLATE.getVal();
        StringBuilder valuesBuilder = new StringBuilder("( ");
        StringBuilder selectFieldBuilder = new StringBuilder(" ( ");
        for (int i = 0, len = set.size(); i < len; i++) {
            Object o = values.get(i);
            if (i != 0) {
                selectFieldBuilder.append(", " + set.get(i));
                valuesBuilder.append(parseValues(o, false));
            } else {
                selectFieldBuilder.append(set.get(i));
                valuesBuilder.append(parseValues(o, true));
            }
        }
        valuesBuilder.append(" ) ");
        selectFieldBuilder.append(" ) ");
        String sql = val.replace(Key.INSERT_TABLE_NAME_KEY.key(), globalAttribute.getTableName().get()).replace(Key.INSERT_FIELD_KEY.key(), selectFieldBuilder.toString()).replace(Key.INSERT_VALUES_KEY.key(), valuesBuilder.toString());
        return sql;
    }

    private void renew() {
        values = new ArrayList<>();
        set = new ArrayList<>();
    }

    private String parseValues(Object o, boolean isFirst) {
        if (o instanceof String || o instanceof Date) {
            return isFirst ? " '" + o + "' " : ", " + " '" + o + "' ";
        } else {
            return isFirst ? o + "" : ", " + o;
        }
    }

    @Override
    public InsertSqlWrapper<T> set(FieldFunction<T, ?> selectField, Object param) {
        String filedName = FieldUtil.getFiledName(selectField);
        set.add(filedName);
        values.add(param);
        return this;
    }

    @Override
    public InsertSqlWrapper<T> set(Boolean condition, FieldFunction<T, ?> selectField, Object param) {
        if (condition) {
            set(selectField, param);
        }
        return this;
    }

    @Override
    public InsertSqlWrapper<T> set(T entity) {
        set = new ArrayList<>();
        values = new ArrayList<>();
        HashMap<String, Object> res = FieldUtil.convertHashMapByEntity(entity);
        if (res == null || res.isEmpty()) {
            throw new SqlParseException(String.format("the entity is no field , entity: {}", entity));
        }
        Iterator<Map.Entry<String, Object>> iterator = res.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            set.add(next.getKey());
            values.add(next.getValue());
        }
        return this;
    }

    public List<String> getSet() {
        return set;
    }

}