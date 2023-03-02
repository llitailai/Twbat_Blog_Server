package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.refining;

import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.SqlWrapper;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;

/**
 * @author darkltl
 * @date 2021-09-25 15:41
 * @descrption 插入语句SQL封装器
 */
public interface InsertSqlWrapper<T> extends SqlWrapper<T> {


    /**
     * 设置插入的字段
     *
     * @param selectField 选择字段
     * @param param       对应值
     * @return 当前InsertSqlWrapper
     */
    InsertSqlWrapper<T> set(FieldFunction<T, ?> selectField, Object param);


    /**
     * 设置插入的字段
     *
     * @param condition   条件,条件为true则拼接进入values,反之不添加
     * @param selectField 选择字段
     * @param param       对应值
     * @return 当前InsertSqlWrapper
     */
    InsertSqlWrapper<T> set(Boolean condition, FieldFunction<T, ?> selectField, Object param);


    /**
     * 根据传入的对象,取出对象的属性,如果对象属性中不为空
     * 则set进values内部
     *
     * @param entity 对象
     * @return 当前InsertSqlWrapper
     */
    InsertSqlWrapper<T> set(T entity);

    /**
     * InsertSqlWrapper不实现该函数
     * 请勿调用
     *
     * @param selectFields 选择字段
     * @return RunTimeException
     * @throws RuntimeException
     */
    @Override
    @Deprecated
    default SqlWrapper<T> select(FieldFunction<T, ?>... selectFields) {
        throw new RuntimeException("don't impl");
    }

}
