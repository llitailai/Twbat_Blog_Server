package com.twbat.blog.common.database.mysql.sql._wrapper.overstory;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;

/**
 * @author darkltl
 * @date 2021-09-25 15:28
 * @description SQL组装器顶层接口
 */
public interface SqlWrapper<T> {

    /**
     * 设置选择的字段
     *
     * @param selectFields 选择字段
     * @return 当前SQL构造器
     */
    SqlWrapper<T> select(FieldFunction<T, ?>... selectFields);


    /**
     * 设置表名称
     *
     * @param tableName 表名称
     * @return 当前SQL构造器
     */
    SqlWrapper<T> tableName(String tableName);

    /**
     * 设置表名称
     *
     * @param tableName 表名称
     * @param alias     别名
     * @return 当前SQL构造器
     */
    SqlWrapper<T> tableName(String tableName, String alias);

    String getSql();

}
