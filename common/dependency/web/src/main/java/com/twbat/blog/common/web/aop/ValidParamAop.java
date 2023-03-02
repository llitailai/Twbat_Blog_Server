package com.twbat.blog.common.web.aop;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import cn.twbat.logger.core.log.util.SensitiveInformationHandleUtil;
import com.twbat.blog.common.web.valid.annotations.Valid;
import com.twbat.blog.common.web.valid.enums.ValidEnum;
import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.handler.DefaultHandler;
import com.twbat.blog.common.web.valid.util.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author darkltl
 * @date 2021-09-08 17:00
 * @description
 */
@Aspect
@Component
public class ValidParamAop {

    private static final Logger LOG = LoggerFactory.getLogger(ValidParamAop.class);
    /**
     * 自定义类关键字,项目包名中的关键字,可以是com.user.blog,也可以是user,方便aop拦截并实例化对象,尽可能不要太广泛
     */
    private static final String KEY = "twbat";

    @Pointcut("@annotation(com.twbat.blog.common.web.valid.annotations.RequiredValidParam)")
    public void validParamPointCut() {

    }

    @Before("validParamPointCut()")
    public void beforeValid(JoinPoint joinPoint) {
        LOG.info("拦截{}函数成功,携带参数:{}", joinPoint.getSignature().getName(), SensitiveInformationHandleUtil.handle(Arrays.toString(joinPoint.getArgs())));
        // 获取目标方法对象
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取目标方法的参数列表数组
        Parameter[] parameters = method.getParameters();
        validParams(parameters, joinPoint.getArgs());
    }

    /**
     * 校验参数具体实现
     *
     * @param parameters 参数列表
     * @param args       参数列表对应的值
     */
    private void validParams(Parameter[] parameters, Object[] args) {
        for (int i = 0, len = parameters.length; i < len; i++) {
            Parameter parameter = parameters[i];
            Valid valid = getValidAnnotation(parameter);
            String uuId = UUID.randomUUID().toString();
            LOG.info("{}-校验参数是否具备校验注解,参数:{},注解:{},记录时间:{}", uuId, SensitiveInformationHandleUtil.handle(parameter.toString()), valid, DateUtils.getTime());
            // 判断parameter是否是自定义类
            Class cls = null;
            if (isCustomizeType(parameter.toString())) {
                try {
                    cls = Class.forName(parameter.getParameterizedType().getTypeName());
                } catch (ClassNotFoundException e) {
                    LOG.error("{}类未找到,类名: {},异常:{}", uuId, parameter.getParameterizedType().getTypeName(), e);
                }
            }
            Object arg = args[i];
            if (valid != null) {
                handler(valid, arg, uuId);
            }
            // 如果cls不等于null,需要获取cls内部的所有字段,来判断字段是否具有Valid注解,如果有则需要进行下列校验
            if (cls != null) {
                LOG.info("{}-当前为自定义类对象,类名:{},记录时间:{}", uuId, cls.getName(), DateUtils.getTime());
                // 获取私有字段
                Field[] fields = cls.getDeclaredFields();
                for (Field field : fields) {
                    // 获取当前对象当前字段是否具有Valid注解
                    Valid annotation = field.getAnnotation(Valid.class);
                    if (annotation != null) {
                        try {
                            // 获取到arg中该字段的值
                            Field declaredField = arg.getClass().getDeclaredField(field.getName());
                            declaredField.setAccessible(true);
                            Object o = declaredField.get(arg);
                            // 调用handler处理值是否合法
                            handler(annotation, o, uuId);
                        } catch (IllegalAccessException e) {
                            LOG.error("在获取字段值时抛出非法链接异常,字段:{},请检查该字段是否开启访问权限,异常时间:{},异常栈:{}", field.getName(), DateUtils.getTime(), e);
                        } catch (NoSuchFieldException e) {
                            LOG.error("在获取字段值时抛出未找到字段异常,字段:{},请检查目标类该字段是否存在,目标类:{},异常时间:{},异常栈:{}", field.getName(), arg.getClass().getName(), DateUtils.getTime(), e);
                        }
                    }
                }
            }
            continue;
        }
    }

    private void handler(Valid valid, Object arg, String uuId) {
        LOG.info("{}-当前进行校验参数为: {},记录时间: {}", uuId, SensitiveInformationHandleUtil.handle(arg + ""), DateUtils.getTime());
        if (isOpenValid(valid)) {
            LOG.info("{}-开启参数为空验证,开启时间:{}", uuId, DateUtils.getTime());
            DefaultHandler.valid(DefaultHandler.handler(ValidEnum.VALID, arg, valid), ValidExceptionEnum.PARAM_CAN_NOT_BE_NULL_EXCEPTION_ENUM);
            LOG.info("{}-参数为空验证通过,结束时间:{}", uuId, DateUtils.getTime());
        }
        if (isOpenTel(valid)) {
            LOG.info("{}-开启电话格式校验,开启时间:{}", uuId, DateUtils.getTime());
            DefaultHandler.valid(DefaultHandler.handler(ValidEnum.TEL, arg, valid), ValidExceptionEnum.TEL_FORMAT_EXCEPTION_ENUM);
            LOG.info("{}-电话格式验证通过,结束时间:{}", uuId, DateUtils.getTime());
        } else {
            if (isOpenPassword(valid)) {
                LOG.info("{}-开启密码格式验证,开启时间:{}", uuId, DateUtils.getTime());
                DefaultHandler.valid(DefaultHandler.handler(ValidEnum.PASSWORD, arg, valid), ValidExceptionEnum.PASSWORD_FORMAT_EXCEPTION_ENUM);
                LOG.info("{}-密码格式验证通过,结束时间:{}", uuId, DateUtils.getTime());
            }
        }

        if (isOpenLengthGt(valid)) {
            LOG.info("{}-开启字符串长度验证-大于,开启时间: {}", uuId, DateUtils.getTime());
            DefaultHandler.valid(DefaultHandler.handler(ValidEnum.LENGTH_GT, arg, valid), ValidExceptionEnum.VALUE_EXCEEDS_MIN_LENGTH_THE_LIMIT_EXCEPTION_ENUM);
            LOG.info("{}-字符串长度验证通过-大于,结束时间: {}", uuId, DateUtils.getTime());
        }

        if (isOpenLengthLt(valid)) {
            LOG.info("{}-开启字符串长度验证-小于,开启时间: {}", uuId, DateUtils.getTime());
            DefaultHandler.valid(DefaultHandler.handler(ValidEnum.LENGTH, arg, valid), ValidExceptionEnum.VALUE_EXCEEDS_LENGTH_THE_LIMIT_EXCEPTION_ENUM);
            LOG.info("{}-字符串长度验证通过-小于,结束时间: {}", uuId, DateUtils.getTime());
        } else {
            if (isOpenMax(valid)) {
                LOG.info("{}-开启数值最大值验证,开启时间:{}", uuId, DateUtils.getTime());
                DefaultHandler.valid(DefaultHandler.handler(ValidEnum.MAX, arg, valid), ValidExceptionEnum.VALUE_EXCEEDS_MAX_THE_LIMIT_EXCEPTION_ENUM);
                LOG.info("{}-数值最大值验证通过,结束时间: {}", uuId, DateUtils.getTime());
            }
            if (isOpenMin(valid)) {
                LOG.info("{}-开启数值最小值验证,开启时间:{}", uuId, DateUtils.getTime());
                DefaultHandler.valid(DefaultHandler.handler(ValidEnum.MIN, arg, valid), ValidExceptionEnum.VALUE_EXCEEDS_MIN_THE_LIMIT_EXCEPTION_ENUM);
                LOG.info("{}-数值最小值验证通过,结束时间:{}", uuId, DateUtils.getTime());
            }
        }
    }

    private boolean isOpenLengthLt(Valid valid) {
        return valid.lengthLt() != -1;
    }

    private boolean isOpenLengthGt(Valid valid) {
        return valid.lengthGt() != -1;
    }

    private boolean isOpenMin(Valid valid) {
        return valid.min().open();
    }

    private boolean isOpenMax(Valid valid) {
        return valid.max().open();
    }

    private boolean isOpenPassword(Valid valid) {
        return valid.password();
    }

    private boolean isOpenTel(Valid valid) {
        return valid.tel();
    }

    private boolean isOpenValid(Valid valid) {
        return valid.valid();
    }

    private Valid getValidAnnotation(Parameter parameter) {
        return parameter.getAnnotation(Valid.class);
    }

    private boolean isCustomizeType(String name) {
        return name.contains(KEY);
    }
}
