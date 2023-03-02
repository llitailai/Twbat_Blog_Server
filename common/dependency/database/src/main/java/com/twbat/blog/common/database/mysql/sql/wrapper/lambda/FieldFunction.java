package com.twbat.blog.common.database.mysql.sql.wrapper.lambda;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @param <T>
 * @param <R>
 * @author darkltl
 * @date 2021-09-22 14:17
 * 字段lambda Function
 */
public interface FieldFunction<T, R> extends Function<T, R>, Serializable {

}
