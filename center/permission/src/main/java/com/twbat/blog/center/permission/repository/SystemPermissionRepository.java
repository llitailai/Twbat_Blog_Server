package com.twbat.blog.center.permission.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.center.permission.domain.SystemPermissionPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darkltl
 * @className SystemPermissionRepository
 * @email darkltl@163.com
 * @date 2021/7/22 - 15:43
 * @description 系统权限数据仓库
 */
@Mapper
public interface SystemPermissionRepository extends BaseMapper<SystemPermissionPO> {
}
