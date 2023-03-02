package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.SqlWrapper;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql.ConnectMethod;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.SQLDefine;

public interface UpdateSqlWrapper<T> extends SqlWrapper<T> {

    /**
     * 根据传入的实体设置修改sql
     *
     * @param entity 实体
     * @return 当前修改sql构造器
     */
    UpdateSqlWrapper<T> update(T entity);


    /**
     * 根据选择的字段设置修改sql
     *
     * @param selectField 选择的字段
     * @param param       传入的参数
     * @return 当前修改sql构造器
     */
    UpdateSqlWrapper<T> update(FieldFunction<T, ?> selectField, Object param);

    /**
     * 根据条件和选择的字段设置修改sql
     *
     * @param selectField 选择的字段
     * @param param       传入的参数
     * @return 当前修改sql构造器
     */
    UpdateSqlWrapper<T> update(Boolean condition, FieldFunction<T, ?> selectField, Object param);


    /**
     * 设置修改sql条件
     *
     * @param fieldFunction 选择的字段
     * @param param         参数
     * @param define        连接的定义 = > < != ...
     * @param method        连接方式 and 或者 or
     * @return 当前修改sql构造器
     */
    UpdateSqlWrapper<T> where(FieldFunction<T, ?> fieldFunction, Object param, SQLDefine define, ConnectMethod method);


    @Override
    @Deprecated
    default SqlWrapper<T> select(FieldFunction<T, ?>... selectFields) {
        return null;
    }
}
