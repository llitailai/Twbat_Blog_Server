package com.twbat.blog.system.business.service.login.component.valid;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.global.exception.business.user.UserNotExistException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;
import com.twbat.blog.common.util.util.encrypt.MD5Util;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;
import com.twbat.blog.system.business.repository.user.SystemUserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author darkltl
 * @date 2021-09-13 16:45
 * 用户登录校验组件
 */
@Component
public class UserLoginValidComponent {

    /**
     * 系统用户DAO
     */
    @Resource
    private SystemUserMapper systemUserMapper;

    /**
     * 线程安全的数值
     */
    private final AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 校验登录用户是否合法
     * @param phone 手机号
     * @param password 密码
     * @exception UserNotExistException 如果用户不存在则会抛出该异常
     */
    public void valid(String phone, String password) {
        // 校验用户是否存在
        if (userIsExist(phone)) {
            // 存在校验用户密码是否正确
            if (userPasswordIsReally(password, phone)) {
                return;
            }
        }
        throw new UserNotExistException(BusinessExceptionEnum.USER_NOT_EXIST_EXCEPTION);
    }

    /**
     * 根据用户手机号校验用户是否存在
     * true: 存在
     * false : 不存在
     *
     * @param phone 用户手机号
     * @return boolean
     * true: 存在
     * false: 不存在
     */
    private boolean userIsExist(String phone) {
        return systemUserMapper.selectCount(new LambdaQueryWrapper<SystemUserPo>().eq(SystemUserPo::getUserTel, phone)) > 0;
    }

    /**
     * 校验用户密码是否正确
     * true: 正确
     * false: 不正确
     *
     * @param password 密码
     * @param phone    用户手机号(需根据手机号查询对应数据实体)
     * @return boolean
     * true: 正确
     * false: 不正确
     */
    private boolean userPasswordIsReally(String password, String phone) {
        SystemUserPo res = systemUserMapper.selectOne(new LambdaQueryWrapper<SystemUserPo>().eq(SystemUserPo::getUserTel, phone).select(SystemUserPo::getUserPassword, SystemUserPo::getUserId));
        setLoginUserId(res.getUserId());
        return MD5Util.verify(password, res.getUserPassword());
    }


    /**
     * 获取当前登录用户ID
     *
     * @return 当前登录用户ID
     */
    public Integer getLoginUserId() {
        return atomicInteger.get();
    }

    /**
     * 设置当前登录用户ID
     *
     * @param userId 用户ID
     */
    private void setLoginUserId(Integer userId) {
        atomicInteger.set(userId);
    }
}
