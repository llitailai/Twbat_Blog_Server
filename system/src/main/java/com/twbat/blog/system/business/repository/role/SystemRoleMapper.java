package com.twbat.blog.system.business.repository.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.system.business.domain.role.po.SystemRolePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darkltl
 * @date 2021-09-14 15:38
 * 系统角色Dao
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRolePo> {
}
