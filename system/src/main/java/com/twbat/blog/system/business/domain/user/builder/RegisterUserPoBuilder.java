package com.twbat.blog.system.business.domain.user.builder;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;
import com.twbat.blog.center.permission.global.exception.register.RegisterPhoneExistException;
import com.twbat.blog.common.util.util.encrypt.MD5Util;
import com.twbat.blog.system.business.domain.user.dto.user.register.RegisterSystemUserDto;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;
import com.twbat.blog.system.business.repository.user.SystemUserMapper;
import com.twbat.blog.system.constant.enums.SystemUserStateEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author darkltl
 * @date 2021-09-10 16:01
 * 注册用户实体构建器
 */
@Component
public class RegisterUserPoBuilder {

    /**
     * 系统用户DAO
     */
    @Resource
    private SystemUserMapper systemUserMapper;

    /**
     * 根据系统用户注册数据传输实体构建系统用户实体
     * 用于插入数据库保存
     *
     * @param dto 系统用户注册数据传输实体
     * @return SystemUserPo
     * 系统用户实体
     * @throws RegisterPhoneExistException 注册手机号已存在异常
     *                                     当用户想要注册的手机号已存在在数据库时,不可注册
     */
    public SystemUserPo builderRegisterUserPo(RegisterSystemUserDto dto) {
        // 校验用户手机号是否存在
        if (systemUserMapper.selectCount(new LambdaQueryWrapper<SystemUserPo>().eq(SystemUserPo::getUserTel, dto.getUserTel())) > 0) {
            throw new RegisterPhoneExistException(BusinessExceptionEnum.REGISTER_USER_PHONE_EXIST);
        }
        return SystemUserPo.builder().userTel(dto.getUserTel()).userPassword(MD5Util.generate(dto.getUserPassword())).status(SystemUserStateEnum.USE.ordinal()).build();
    }
}
