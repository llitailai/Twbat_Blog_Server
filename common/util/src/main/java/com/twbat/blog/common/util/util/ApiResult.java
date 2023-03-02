package com.twbat.blog.common.util.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author darkltl
 * @className ApiResult
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:43
 * @description
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {

    /**
     * 成功响应码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 默认成功响应信息
     */
    private static final String DEFAULT_SUCCESS_MSG = "操作成功";

    /**
     * 默认错误响应码
     */
    private static final int DEFAULT_ERROR_CODE = 500;

    /**
     * 默认错误响应信息
     */
    private static final String DEFAULT_ERROR_MSG = "服务器异常";

    /**
     * 响应数据对象
     * 通常存放调用方需要的数据
     */
    private T data;

    /**
     * 响应状态码
     * 成功200
     * 系统异常500
     * 自定义异常600-?
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    public static ApiResult success() {
        return ApiResult.builder().code(SUCCESS_CODE).msg(DEFAULT_SUCCESS_MSG).build();
    }

    public static ApiResult success(String msg) {
        return ApiResult.builder().code(SUCCESS_CODE).msg(msg).build();
    }

    public static ApiResult success(int row) {
        return row > 0 ? success() : error();
    }

    public static ApiResult success(Object data) {
        return ApiResult.builder().code(SUCCESS_CODE).msg(DEFAULT_SUCCESS_MSG).data(data).build();
    }

    public static ApiResult success(String msg, Object data) {
        return ApiResult.builder().code(SUCCESS_CODE).msg(msg).data(data).build();
    }

    public static ApiResult error(Integer code, String msg) {
        return ApiResult.builder().code(code).msg(msg).build();
    }

    public static ApiResult error() {
        return ApiResult.builder().code(DEFAULT_ERROR_CODE).msg(DEFAULT_ERROR_MSG).build();
    }

    public static ApiResult error(String msg) {
        return ApiResult.builder().code(DEFAULT_ERROR_CODE).msg(msg).build();
    }
}
