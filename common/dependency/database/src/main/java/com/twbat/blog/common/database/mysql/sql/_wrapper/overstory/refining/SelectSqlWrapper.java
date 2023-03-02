package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.SqlWrapper;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql.JoinMethod;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.dialect.mysql.LikeMethod;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining.impl.JoinSqlWrapper;

/**
 * @author darkltl
 * @date 2021-10-11 09:36
 * @description 查询语句SQL封装器
 */
public interface SelectSqlWrapper<T> extends SqlWrapper<T> {

    /**
     * 设置等于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> eq(FieldFunction<T, ?> selectField, Object param);

    /**
     * 根据condition设置等于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @param condition   当条件为true时添加
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> eq(Boolean condition, FieldFunction<T, ?> selectField, Object param);

    /**
     * 设置小于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> lt(FieldFunction<T, ?> selectField, Object param);

    /**
     * 根据condition设置小于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @param condition   当条件为true时添加
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> lt(Boolean condition, FieldFunction<T, ?> selectField, Object param);

    /**
     * 设置大于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> gt(FieldFunction<T, ?> selectField, Object param);

    /**
     * 根据condition设置大于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @param condition   当条件为true时添加
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> gt(Boolean condition, FieldFunction<T, ?> selectField, Object param);

    /**
     * 设置不等于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> ne(FieldFunction<T, ?> selectField, Object param);

    /**
     * 根据condition设置不等于条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @param condition   当条件为true时添加
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> ne(Boolean condition, FieldFunction<T, ?> selectField, Object param);

    /**
     * 设置模糊查询条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> like(FieldFunction<T, ?> selectField, Object param);

    /**
     * 根据condition设置模糊条件条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @param condition   当条件为true时添加
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> like(Boolean condition, FieldFunction<T, ?> selectField, Object param);

    /**
     * 根据选择的模糊查询方式设置模糊查询条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @param likeMethod  模糊查询方式
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> like(FieldFunction<T, ?> selectField, Object param, LikeMethod likeMethod);

    /**
     * 根据条件和选择的模糊查询方式设置模糊查询条件的where函数
     *
     * @param selectField 选择的字段
     * @param param       值
     * @param likeMethod  模糊查询方式
     * @param condition   当条件为true时添加
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> like(Boolean condition, FieldFunction<T, ?> selectField, Object param, LikeMethod likeMethod);

    /**
     * 设置连接条件的where函数
     *
     * @param joinTableName 连接的表名称
     * @param aliasName     连接表名称的别名
     * @return 连接查询SQL构造器
     */
    JoinSqlWrapper<T> join(String joinTableName, String aliasName);

    /**
     * 根据条件设置连接条件的where函数
     *
     * @param joinTableName 连接的表名称
     * @param aliasName     连接表名称的别名
     * @param condition     当条件为true时添加
     * @return 连接查询SQL构造器
     */
    JoinSqlWrapper<T> join(Boolean condition, String joinTableName, String aliasName);

    /**
     * 根据连接方式设置连接条件的where函数
     *
     * @param joinTableName 连接的表名称
     * @param aliasName     连接表名称的别名
     * @param joinMethod    连接方式
     * @return 连接查询SQL构造器
     */
    JoinSqlWrapper<T> join(String joinTableName, String aliasName, JoinMethod joinMethod);


    /**
     * 根据条件和连接方式设置连接条件的where函数
     *
     * @param joinTableName 连接的表名称
     * @param aliasName     连接表名称的别名
     * @param joinMethod    连接方式
     * @param condition     条件
     * @return 连接查询SQL构造器
     */
    JoinSqlWrapper<T> join(Boolean condition, String joinTableName, String aliasName, JoinMethod joinMethod);

    /**
     * 连接查询的on条件
     *
     * @param selectField 选择的字段
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> on(FieldFunction<T, ?> selectField);

    /**
     * 设置子查询选择字段
     *
     * @param sql       子查询sql
     * @param aliasName 查询别名
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> childSelect(String sql, String aliasName);

    /**
     * 根据条件设置子查询选择字段
     *
     * @param sql       子查询sql
     * @param aliasName 查询别名
     * @param condition 当条件为true时添加
     * @return 当前查询SQL构造器
     */
    SelectSqlWrapper<T> childSelect(Boolean condition, String sql, String aliasName);

}
