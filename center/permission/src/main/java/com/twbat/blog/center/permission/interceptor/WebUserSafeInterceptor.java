package com.twbat.blog.center.permission.interceptor;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.center.permission.domain.WebUserSafePo;
import com.twbat.blog.center.permission.repository.WebUserSafeMapper;
import com.twbat.blog.center.permission.util.SafeUtil;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.auth.Token;
import com.twbat.blog.common.util.util.config.Constant;
import com.twbat.blog.common.web.valid.util.IpUtil;
import com.twbat.blog.common.web.valid.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 2:01
 * @desciption web用户安全拦截器
 */
@Component
public class WebUserSafeInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebUserSafeInterceptor.class);
    @Resource
    private WebUserSafeMapper webUserSafeMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LOGGER.info("WebUserSafeInterceptor 开始拦截.拦截时间: {}", DateUtils.getTime());
        // 获取登录时间
        Date time = DateUtils.getNowDate();
        LOGGER.info("获取登录时间 : {}", DateUtils.getDateTimeStr(time));
        // 获取登录设备
        String openSystem = SafeUtil.getOpenSystem(request);
        LOGGER.info("获取登录设备 : {}", openSystem);
        // 获取登录IP
        String loginIp = IpUtil.getIpAddr(request);
        LOGGER.info("获取登录IP : {}", loginIp);
        // 获取登录城市
        String address = SafeUtil.getAddresses("ip=" + loginIp, "utf-8");
        LOGGER.info("获取address : {}", address);
        JSONObject jsonObject = JSONObject.parseObject(address);
        String region = jsonObject.getString("region");
        String city = jsonObject.getString("city");
        if (StringUtils.isEmpty(region)) {
            region = "中国大陆";
        }
        if (StringUtils.isEmpty(city)) {
            city = "中国大陆";
        }
        city = region + "/" + city;
        LOGGER.info("获取城市 : {}", city);
        // 获取登录国家
        String county = jsonObject.getString("country_id");
        if (StringUtils.isEmpty(county)) {
            county = "中国";
        }
        LOGGER.info("获取登录国家 : {}", county);
        // 用户ID
        String header = request.getHeader(Constant.TOKEN_HEADER.getCode());
        String token = header.split(Constant.TOKEN_PREFIX.getCode())[1].trim();
        Integer userId = Token.getUserId(Token.getTokenInfo(token));
        LOGGER.info("获取用户ID : {}", userId);
        WebUserSafePo res = WebUserSafePo.builder()
                .loginTime(time)
                .loginEquipment(openSystem)
                .loginIp(loginIp)
                .loginCity(city)
                .loginCountry(county)
                .userId(userId)
                .creteTime(DateUtils.getNowDate())
                .build();

        try {
            if (webUserSafeMapper.insert(res) <= 0) {
                LOGGER.warn("并未插入成功");
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("流程出现异常.异常信息:{}", e);
        }
        LOGGER.info("流程正确执行完毕");
        return true;
    }

}
