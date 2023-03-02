package com.twbat.blog.system.controller.menu;

import com.twbat.blog.center.permission.annotation.Permission;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import com.twbat.blog.system.base.BaseController;
import com.twbat.blog.system.business.domain.menu.dto.SaveMenuDto;
import com.twbat.blog.system.business.service.menu.SystemMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author darkltl
 * @date 2021-09-14 09:29
 * 菜单控制器
 */
@RestController
public class MenuController extends BaseController {

    /**
     * 系统菜单服务定义类,用于提供对系统菜单操作的服务支持
     */
    @Resource
    private SystemMenuService systemMenuService;

    /**
     * 获取初始化菜单栏菜单列表接口
     * todo : 后续需要增加权限相关条件
     *
     * @return 菜单列表全局统一返回对象
     */
    @GetMapping("/menu/list")
    @Required
    public ApiResult<?> listInitMenu() {
        return systemMenuService.listSystemMenu(getCurrentUser());
    }


    /**
     * 获取所有菜单列表,用于数据展示接口
     * todo: 后续需要加分页或其他条件
     *
     * @return 菜单列表数据全局统一返回列表
     */
    @GetMapping("/menu/listMenus")
    @Permission(permissionCode = "root:menu:list")
    public ApiResult<?> listMenus() {
        return systemMenuService.listSystemMenus();
    }

    /**
     * 获取添加菜单项时菜单列表选择框数据接口
     * todo: 后续需添加相应条件
     *
     * @return 菜单选择项列表全局统一返回对象
     */
    @GetMapping("/menu/listSelectMenus")
    public ApiResult<?> listSelectMenus() {
        return systemMenuService.listSelectMenus();
    }


    /**
     * 添加菜单接口
     *
     * @param saveMenuDto 添加菜单dto对象
     * @return 全局统一返回对象
     */
    @PostMapping("/menu/save")
    @Required
    public ApiResult<?> saveMenu(@RequestBody SaveMenuDto saveMenuDto) {
        return ApiResult.success(systemMenuService.saveMenu(saveMenuDto));
    }
}
