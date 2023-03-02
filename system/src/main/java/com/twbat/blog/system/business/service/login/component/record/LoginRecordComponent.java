package com.twbat.blog.system.business.service.login.component.record;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.center.permission.global.web.RequestUtil;
import com.twbat.blog.common.web.valid.util.DateUtils;
import com.twbat.blog.common.web.valid.util.IpUtil;
import com.twbat.blog.system.business.domain.user.po.record.SystemLoginRecordPo;
import com.twbat.blog.system.business.repository.record.SystemLoginRecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author darkltl
 * @date 2021-09-13 17:22
 * 系统用户登录记录组件
 */
@Component
public class LoginRecordComponent {


    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRecordComponent.class);

    @Resource
    private SystemLoginRecordMapper systemLoginRecordMapper;


    /**
     * 记录用户登录
     * @param userId 用户ID
     */
    public void recordLogin(Integer userId) {
        String ipAddress = IpUtil.getIpAddr(RequestUtil.getRequest());
        LOGGER.debug("登录用户ID:{},登录用户IP:{}", userId, ipAddress);
        int res = systemLoginRecordMapper.insert(buildLoginRecordPo(userId, ipAddress));
        LOGGER.debug("记录用户登录日志成功,影响行数:{}", res);
    }

    /**
     * 构建用户登录记录PO
     * @param userId 用户ID
     * @param ip ip
     * @return 用户登陆记录po
     */
    private SystemLoginRecordPo buildLoginRecordPo(Integer userId, String ip) {
        SystemLoginRecordPo res = new SystemLoginRecordPo();
        res.setRecordIp(ip);
        res.setLoginTime(DateUtils.getNowDate());
        res.setCreateTime(DateUtils.getNowDate());
        res.setUserId(userId);
        return res;
    }

}
