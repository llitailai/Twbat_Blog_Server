package com.twbat.blog.system.business.repository.role.relation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.common.util.util.SymbolUtil;
import com.twbat.blog.system.business.domain.role.relation.SystemRoleMenuRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author darkltl
 * @date 2021-09-22 11:21
 * 系统角色菜单关联关系表DAO
 */
@Mapper
public interface SystemRoleMenuRelationMapper extends BaseMapper<SystemRoleMenuRelation> {

    @Select("SELECT" + SymbolUtil.LINE_FEED +
            "" +
            "")
    public Set<String> listPermissionsByRoleId(Integer roleId);
}
