package com.twbat.blog.center.permission.global.exception;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.center.permission.global.exception.business.permission.PermissionServiceNotFoundException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.web.valid.exception.base.ValidException;
import com.twbat.blog.common.web.valid.util.DateUtils;
import com.twbat.blog.common.web.valid.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author darkltl
 * @className BlogExceptionHandler
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:27
 * @description 博客全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class BlogExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogExceptionHandler.class);

    @ExceptionHandler(value = PermissionServiceNotFoundException.class)
    public ApiResult permissionExceptionHandler(PermissionServiceNotFoundException permissionServiceNotFoundException) {
        if (permissionServiceNotFoundException.getThrowable() != null) {
            LOGGER.warn("权限异常,时间:{},异常信息:{}", DateUtils.getTime(), permissionServiceNotFoundException.getThrowable());
        }
        if (StringUtils.isNotEmpty(permissionServiceNotFoundException.getErrorMsg())) {
            return getResult(SystemExceptionEnum.ERROR.getCode(), permissionServiceNotFoundException.getErrorMsg());
        }
        return getResult(permissionServiceNotFoundException.getBusinessExceptionEnum().getCode(), permissionServiceNotFoundException.getBusinessExceptionEnum().getMsg());
    }

    @ExceptionHandler(value = BusinessException.class)
    public ApiResult businessExceptionHandler(BusinessException exception) {
        if (exception.getThrowable() != null) {
            LOGGER.warn("出现业务异常,时间:{},异常信息:{}", DateUtils.getTime(), exception.getThrowable());
        }
        return getResult(exception.getBusinessExceptionEnum().getCode(), exception.getBusinessExceptionEnum().getMsg());
    }



    @ExceptionHandler(value = ValidException.class)
    public ApiResult validExceptionHandler(ValidException validException) {
        if (validException.getThrowable() != null) {
            LOGGER.warn("校验参数异常,时间:{},异常信息:{}", DateUtils.getTime(), validException.getThrowable());
        }
        return getResult(validException.getValidExceptionEnum().getCode(), validException.getValidExceptionEnum().getMsg());
    }

    @ExceptionHandler(value = RuleException.class)
    public ApiResult ruleExceptionHandler(RuleException ruleException) {
        if (ruleException.getThrowable() != null) {
            LOGGER.warn("校验参数异常,时间:{},异常信息:{}", DateUtils.getTime(), ruleException.getThrowable());
        }
        return getResult(ruleException.getRuleExceptionEnum().getCode(), ruleException.getRuleExceptionEnum().getMsg());
    }

    @ExceptionHandler(value = SystemException.class)
    public ApiResult systemExceptionHandler(SystemException systemException) {
        if (systemException.getThrowable() != null) {
            LOGGER.warn("校验参数异常,时间:{},异常信息:{}", DateUtils.getTime(), systemException.getThrowable());
        }
        return getResult(systemException.getSystemExceptionEnum().getCode(), systemException.getSystemExceptionEnum().getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResult exceptionHandler(Exception exception) {
        LOGGER.error("出现系统异常,时间:{},异常信息:{}", DateUtils.getTime(), exception);
        return getResult(500, "系统异常");
    }


    private ApiResult getResult(Integer code, String msg) {
        return ApiResult.builder().code(code).msg(msg).build();
    }
}
