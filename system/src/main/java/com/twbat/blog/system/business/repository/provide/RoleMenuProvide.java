package com.twbat.blog.system.business.repository.provide;

import com.twbat.blog.common.util.util.SymbolUtil;
import com.twbat.blog.common.util.util.config.Constant;
import com.twbat.blog.common.util.util.sql.SQLKey;

import java.util.Map;

/**
 * @author darkltl
 * @date 2021-09-22 11:28
 * 角色菜单SQL规定类
 */
public class RoleMenuProvide {

    public String getListPermissionsByRoleIdSql(Map<String, Object> param) {
        /*
            select sm.* from system_menu as sm
            left join system_role_menu as srm on sm.menu_id = srm.menu_id

         */
        Integer roleId = (Integer) param.get("roleId");
        assert roleId != null : "role id can not be null";
        // 如果是超级管理员
        String baseSql =
                SQLKey.SELECT.getKey() + SymbolUtil.BLANK_SPACE + SQLKey.ALL.getKey() + SymbolUtil.BLANK_SPACE + "system_menu" + SymbolUtil.BLANK_SPACE;
        if (roleId.intValue() == Integer.valueOf(Constant.ROLE_ADMIN_ID.getCode()).intValue()) {
            return baseSql;
        }
        // append where
        baseSql += SQLKey.WHERE.getKey() + SymbolUtil.BLANK_SPACE;
        baseSql += "menu_id" + SymbolUtil.BLANK_SPACE + SQLKey.IN.getKey() + SymbolUtil.BLANK_SPACE + SQLKey.LEFT_BRACKET + SymbolUtil.BLANK_SPACE;
        // init append builder

        // 查询超级管理员
        return null;
    }
//
//    public String getListPermissionByMapper(Map<String,Object> param) {
//        SystemRoleMenuRelationMapper roleMenuRelationMapper = (SystemRoleMenuRelationMapper) param.get("mapper");
//        Integer roleId = (Integer) param.get("roleId");
//        List<SystemRoleMenuRelation> res = roleMenuRelationMapper.selectList(new LambdaQueryWrapper<SystemRoleMenuRelation>().eq(SystemRoleMenuRelation::getRoleId, roleId));
//        // 超级管理员
//        if (res != null && res.size() == 1 && res.get(0).getMenuId() == 0) {
//            return SQLKey.SELECT.getKey()+""
//        }
//
//    }
}
