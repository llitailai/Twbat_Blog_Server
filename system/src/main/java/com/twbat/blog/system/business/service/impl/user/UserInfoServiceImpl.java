package com.twbat.blog.system.business.service.impl.user;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.system.business.repository.user.SystemUserMapper;
import com.twbat.blog.system.business.service.user.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author darkltl
 * @date 2021-09-14 10:52
 * 获取用户信息服务实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 系统用户DAO
     */
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public ApiResult<?> listSystemUsers() {
        return ApiResult.success(systemUserMapper.selectList(null));
    }
}
