package com.twbat.blog.common.database.mysql.sql.wrapper;

import com.twbat.blog.common.database.mysql.sql.wrapper.define.JoinDefine;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.LikeDefine;
import com.twbat.blog.common.database.mysql.sql.wrapper.define.SqlMethodDefine;
import com.twbat.blog.common.database.mysql.sql.wrapper.lambda.FieldFunction;

import java.util.Collection;

/**
 * @author darkltl
 * @date 2021-09-22 14:06
 * SQL条件构造器
 */
public interface SqlWrapper<T> {

    /**
     * 等于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> eq(FieldFunction<T, ?> function, Object param);

    /**
     * 等于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> eq(FieldFunction<T, ?> function, Object param, boolean flag);

    /**
     * 模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> like(FieldFunction<T, ?> function, Object param);

    /**
     * 模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> like(FieldFunction<T, ?> function, Object param, boolean flag);


    /**
     * 模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param define   like规则
     * @return 当前条件构造器
     */
    SqlWrapper<T> like(FieldFunction<T, ?> function, Object param, LikeDefine define);

    /**
     * 模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param define   like规则
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> like(FieldFunction<T, ?> function, Object param, LikeDefine define, boolean flag);

    /**
     * 模糊相反条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param);

    /**
     * 模糊相反条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param, boolean flag);

    /**
     * 模糊相反条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param define   like规则
     * @return 当前条件构造器
     */
    SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param, LikeDefine define);

    /**
     * 模糊相反条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param define   like规则
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> notLike(FieldFunction<T, ?> function, Object param, LikeDefine define, boolean flag);

    /**
     * 区分大小写模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param);

    /**
     * 区分大小写模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param, boolean flag);

    /**
     * 区分大小写模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param define   like规则
     * @return 当前条件构造器
     */
    SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param, LikeDefine define);

    /**
     * 区分大小写模糊条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param define   like规则
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> binaryLike(FieldFunction<T, ?> function, Object param, LikeDefine define, boolean flag);

    /**
     * 不等于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> ne(FieldFunction<T, ?> function, Object param);

    /**
     * 不等于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> ne(FieldFunction<T, ?> function, Object param, boolean flag);

    /**
     * 或者条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> in(FieldFunction<T, ?> function, Collection<T> param);

    /**
     * 或者条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> in(FieldFunction<T, ?> function, Collection<T> param, boolean flag);


    /**
     * 查询条件构造器
     *
     * @param columns see {@link FieldFunction<T,?>}
     * @return 当前条件构造器
     */
    SqlWrapper<T> select(FieldFunction<T, ?>... columns);

    /**
     * 小于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> lt(FieldFunction<T, ?> function, Object param);

    /**
     * 小于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> lt(FieldFunction<T, ?> function, Object param, boolean flag);

    /**
     * 大于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> gt(FieldFunction<T, ?> function, Object param);

    /**
     * 大于条件构造器
     *
     * @param function see {@link FieldFunction<T,?>}
     * @param param    参数
     * @param flag     传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> gt(FieldFunction<T, ?> function, Object param, boolean flag);

    /**
     * 自定义Where
     * 如果是多条件,必须具备 and 或 or 连接符
     *
     * @param where 拼接条件
     * @return 当前条件构造器
     */
    SqlWrapper<T> customizeWhere(String where);

    /**
     * 表别名
     *
     * @param aliasName 别名
     * @return 当前条件构造器
     */
    SqlWrapper<T> as(String aliasName);

    /**
     * 连接表 默认Inner Join
     *
     * @param joinTableName 连接表名称
     * @param aliasName     别名
     * @return 当前条件构造器
     */
    SqlWrapper<T> join(String joinTableName, String aliasName);

    /**
     * 连接表 指定连接方式
     *
     * @param joinTableName 连接表名称
     * @param aliasName     连接表别名
     * @param joinDefine    连接方式
     * @return 当前条件构造器
     */
    SqlWrapper<T> join(String joinTableName, String aliasName, JoinDefine joinDefine);

    /**
     * 连接表 指定连接方式
     *
     * @param joinTableName 连接表名称
     * @param aliasName     连接表别名
     * @param joinDefine    连接方式
     * @param flag          传入一个条件,条件为true则不拼接到SQL中
     * @return 当前条件构造器
     */
    SqlWrapper<T> join(String joinTableName, String aliasName, JoinDefine joinDefine, boolean flag);

    /**
     * 连接条件
     *
     * @param con0 条件1
     * @param con1 条件2
     * @return 当前条件构造器
     */
    SqlWrapper<T> on(String con0, String con1);

    /**
     * 并且条件构造器
     *
     * @return
     */
    SqlWrapper<T> and();

    /**
     * 或者条件构造器
     *
     * @return
     */
    SqlWrapper<T> or();

    /**
     * 表名称
     *
     * @param name 表名称
     * @return 当前条件构造器
     */
    SqlWrapper<T> tableName(String name);

    /**
     * SQL执行方法 (SELECT ? INSERT ? UPDATE ?)
     *
     * @param sqlMethodDefine SQL执行方法规定
     * @return 当前条件构造器
     */
    SqlWrapper<T> sqlMethod(SqlMethodDefine sqlMethodDefine);

    /**
     * 插入时的数据,需与插入时选择的select对应
     * 注意,需与select字段相符
     * 如未选择select字段则需要与选择的PO实体字段对应
     *
     * @param param 插入参数
     * @return 当前条件构造器
     */
    SqlWrapper<T> param(Object param);

    String getSql();

}
