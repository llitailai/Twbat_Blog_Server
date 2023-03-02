package com.twbat.blog.system.business.service.register;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.system.business.domain.user.builder.RegisterUserPoBuilder;
import com.twbat.blog.system.business.domain.user.dto.user.register.RegisterSystemUserDto;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;
import com.twbat.blog.system.business.repository.user.SystemUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author darkltl
 * @date 2021-09-10 15:58
 * 系统用户注册服务
 */
@Service
public class RegisterService {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(RegisterService.class);

    /**
     * 系统用户数据访问对象,在该服务中用于新增系统用户操作(Insert)
     */
    @Resource
    private SystemUserMapper systemUserMapper;

    /**
     * 注册系统用户数据载体构建器
     * 用于构建系统用户数据载体
     */
    @Resource
    private RegisterUserPoBuilder registerUserPoBuilder;


    /**
     * 系统用户注册服务
     * @param dto 用户注册参数dto
     * @return 全局统一返回对象
     */
    public ApiResult<?> registerService(RegisterSystemUserDto dto) {
        LOG.info("正在注册系统用户 : {},注册时间 : {}", dto.toString(), DateUtils.getTime());
        SystemUserPo register = registerUserPoBuilder.builderRegisterUserPo(dto);
        LOG.info("");
        // 如果等于1则代表插入成功
        if (systemUserMapper.insert(register) == 1) {
            return ApiResult.success();
        }
        LOG.error("出现未知错误,数据库插入失败,功能名称: 注册系统用户,注册数据传输对象:{},异常时间:{}", dto.toString(), DateUtils.getTime());
        return ApiResult.error();
    }
}
