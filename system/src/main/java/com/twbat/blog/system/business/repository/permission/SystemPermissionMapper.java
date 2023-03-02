package com.twbat.blog.system.business.repository.permission;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.system.business.domain.permission.po.SystemPermissionPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darkltl
 * @date 2021-09-14 13:10
 * 系统权限Dao
 */
@Mapper
public interface SystemPermissionMapper extends BaseMapper<SystemPermissionPo> {
}
