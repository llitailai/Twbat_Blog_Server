package com.twbat.blog.system.aop;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.common.web.valid.util.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author darkltl
 * @date 2021-09-10 20:27
 * 拦截Controller中的方法做方法执行时间计时
 */
@Aspect
@Component
public class SystemMethodExecuteTimeAop {

    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemMethodExecuteTimeAop.class);

    @Pointcut("execution(public * com.twbat.blog.system.controller.*.*Controller.*(..))")
    public void methodExecuteTimeAop() {

    }

    @Around("methodExecuteTimeAop()")
    public Object doRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("执行方法: {},耗时: {} ms,返回结果:{},记录时间:{}", joinPoint.getSignature().getName(), end - startTime, result, DateUtils.getTime());
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            LOGGER.warn("执行方法: {} 出现异常,耗时: {} ms,记录时间:{}", joinPoint.getSignature().getName(), end - startTime, DateUtils.getTime());
            throw e;
        }
    }
}
