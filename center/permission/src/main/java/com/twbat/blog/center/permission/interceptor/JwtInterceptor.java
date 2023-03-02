package com.twbat.blog.center.permission.interceptor;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.twbat.blog.center.permission.count.IpCountComponent;
import com.twbat.blog.center.permission.domain.ip.IpRecordPo;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;
import com.twbat.blog.center.permission.global.exception.valid.TokenFormatException;
import com.twbat.blog.center.permission.global.exception.valid.TokenInvalidationException;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.auth.Token;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import com.twbat.blog.common.util.util.config.Constant;
import com.twbat.blog.common.web.valid.util.IpUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author darkltl
 * @className JwtInterceptor
 * @email darkltl@163.com
 * @date 2021/8/16 - 15:40
 * @description
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(JwtInterceptor.class);

    private static final String CLS_NAME = JwtInterceptor.class.getSimpleName();

    @Resource
    private IpCountComponent ipCountComponent;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 请求的真实IP
            String ipAddr = IpUtil.getIpAddr(request);
            // 请求方法
            String requestMethod = request.getMethod();
            // 目标方法名称
            String methodName = method.getName();
            // 记录IP
            ipCountComponent.recordIp(buildIpRecord(ipAddr, requestMethod, methodName));
            // 携带参数
            LOG.info("请求IP : {},请求方法: {},请求函数: {},请求时间: {}", ipAddr, requestMethod, methodName, DateUtils.getTime());
            Required annotation = method.getAnnotation(Required.class);
            if (annotation != null) {
                flag = annotation.required();
            }
        }
        if (flag) {
            String header = request.getHeader(Constant.TOKEN_HEADER.getCode());
            LOG.info("clsName : {},executeTime: {},param [header] : {}", CLS_NAME, DateUtils.getTime(), header);
            validTokenHeader(header);
            parseToken(header);
        }
        return true;
    }

    private IpRecordPo buildIpRecord(String ip, String requestMethod, String methodName) {
        IpRecordPo ipRecordPo = new IpRecordPo();
        ipRecordPo.setIp(ip);
        ipRecordPo.setVisitMethod(methodName);
        ipRecordPo.setVisitRequestMethod(requestMethod);
        ipRecordPo.setVisitTime(DateUtils.getNowDate());
        return ipRecordPo;
    }

    private void parseToken(String header) {
        String[] parse = header.split(Constant.TOKEN_PREFIX.getCode());
        if (parse == null || parse.length == 1) {
            LOG.error("header format error,don't find prefix bearer, current time: {}", DateUtils.getTime());
            throw new TokenFormatException(BusinessExceptionEnum.BUSINESS_TOKEN_INVALID);
        }
        // 校验token是否过期
        DecodedJWT tokenInfo = Token.getTokenInfo(parse[1]);
        String key = tokenInfo.getToken() + Token.getUserId(tokenInfo);
        Integer res = (Integer) redisTemplate.opsForValue().get(key);
        // 如果redis中没有该token或者token不是正常状态抛出异常 因为该情况代表token已经失效
        if (res == null) {
            throw new TokenInvalidationException(BusinessExceptionEnum.BUSINESS_TOKEN_INVALID);
        }
    }

    private void validTokenHeader(String header) {
        if (header == null || !header.startsWith(Constant.TOKEN_PREFIX.getCode())) {
            LOG.error("header format error,current time : {}", DateUtils.getTime());
            throw new TokenFormatException(BusinessExceptionEnum.BUSINESS_TOKEN_INVALID);
        }
    }
}
