package com.twbat.blog.system.business.service.menu;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.system.business.domain.menu.dto.SaveMenuDto;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;

/**
 * @author darkltl
 * @date 2021-09-14 10:41
 * 系统菜单服务定义类
 */
public interface SystemMenuService {

    /**
     *
     * 获取初始化系统菜单
     *
     * todo : 后续需要根据权限来获取对应菜单项
     * @param currentUser 当前登录用户
     * @return 全局统一返回对象
     */
    public ApiResult<?> listSystemMenu(SystemUserPo currentUser);


    /**
     *
     * 获取系统所有菜单
     *
     * @return 全局统一返回对象
     */
    ApiResult<?> listSystemMenus();


    /**
     * 获取菜单选择项列表
     *
     * @return 全局统一返回对象
     */
    ApiResult<?> listSelectMenus();

    /**
     * 添加菜单
     *
     * @param saveMenuDto 添加菜单dto对象
     * @return 全局统一返回对象
     */
    ApiResult<?> saveMenu(SaveMenuDto saveMenuDto);
}
