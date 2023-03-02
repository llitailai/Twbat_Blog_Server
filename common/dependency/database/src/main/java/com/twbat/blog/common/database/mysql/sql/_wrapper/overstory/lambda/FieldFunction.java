package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author darkltl
 * @date 2021-09-25 15:30
 * @description 字段函数(lambda) 用于lambda调取对应对象中属性
 */
public interface FieldFunction<T, R> extends Function<T, R>, Serializable {

}
