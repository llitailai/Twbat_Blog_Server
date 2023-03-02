package com.twbat.blog.center.permission.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.center.permission.domain.SystemRolePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darkltl
 * @className SystemRoleRepository
 * @email darkltl@163.com
 * @date 2021/7/22 - 15:45
 * @description 系统角色仓库
 */
@Mapper
public interface SystemRoleRepository extends BaseMapper<SystemRolePO> {
}
