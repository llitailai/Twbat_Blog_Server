package com.twbat.blog.system.business.service.impl.menu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.global.enums.sys.menu.MenuTypeEnum;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.config.Constant;
import com.twbat.blog.system.business.domain.menu.dto.SaveMenuDto;
import com.twbat.blog.system.business.domain.menu.po.SystemMenuPo;
import com.twbat.blog.system.business.domain.menu.vo.ListMenuVo;
import com.twbat.blog.system.business.domain.menu.vo.MenuVo;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;
import com.twbat.blog.system.business.repository.menu.SystemMenuMapper;
import com.twbat.blog.system.business.service.menu.SystemMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author darkltl
 * @date 2021-09-13 10:42
 * 系统菜单服务实现类
 */
@Service
public class SystemMenuServiceImpl implements SystemMenuService {

    /**
     * 系统菜单DAO
     * 提供查询或新增/修改系统菜单的服务支持
     */
    @Resource
    private SystemMenuMapper systemMenuMapper;

    @Override
    public ApiResult<?> listSystemMenu(SystemUserPo currentUser) {
        HashMap<String, Object> res = new HashMap<>();
        List<SystemMenuPo> systemMenuPos = systemMenuMapper.selectList(null);
        res.put("menus", getRes(systemMenuPos));
        Set<String> permissions = null;
        // todo 这里需要后续增加非管理员账号的权限
        if (Constant.SYSTEM_ADMIN_TEL_ONE.getCode().equals(currentUser.getUserTel())) {
            permissions = systemMenuPos.stream().map(SystemMenuPo::getMenuPermission).collect(Collectors.toSet());
        }
        res.put("permissions", permissions);
        return ApiResult.success(res);
    }


    @Override
    public ApiResult<?> listSystemMenus() {
        List<SystemMenuPo> systemMenuPos = systemMenuMapper.selectList(null);
        return ApiResult.success(getShowData(systemMenuPos));
    }

    /**
     * copy to https://www.renren.io
     *
     * @param systemMenuPos 系统菜单po列表
     * @return  系统菜单vo列表
     */
    private List<ListMenuVo> getShowData(List<SystemMenuPo> systemMenuPos) {
        List<ListMenuVo> res = new ArrayList<>();
        HashMap<Integer, ListMenuVo> bingMap = new HashMap<>(systemMenuPos.size());
        for (SystemMenuPo systemMenuPo : systemMenuPos) {
            ListMenuVo target = new ListMenuVo();
            BeanUtils.copyProperties(systemMenuPo, target);
            res.add(target);
            bingMap.put(target.getMenuId(), target);
        }
        for (ListMenuVo menuVo : res) {
            ListMenuVo parent = bingMap.get(menuVo.getParentId());
            if (parent != null) {
                menuVo.setParentName(parent.getMenuName());
            }
        }
        return res;
    }

    /**
     * 获取菜单选择项列表
     *
     * @return 全局统一返回对象
     */
    @Override
    public ApiResult<?> listSelectMenus() {
        List<ListMenuVo> res = getShowData(systemMenuMapper.selectList(new LambdaQueryWrapper<SystemMenuPo>().ne(SystemMenuPo::getMenuType, MenuTypeEnum.BUTTON.ordinal())));
        ListMenuVo root = new ListMenuVo();
        root.setMenuId(0);
        root.setMenuName("一级菜单");
        root.setParentId(-1);
        res.add(root);
        return ApiResult.success(res);
    }


    @Override
    public ApiResult<?> saveMenu(SaveMenuDto saveMenuDto) {
        SystemMenuPo menuPo = new SystemMenuPo();
        BeanUtils.copyProperties(saveMenuDto, menuPo);
        menuPo.setCreateTime(DateUtils.getNowDate());
        menuPo.setCreateUser(JwtUtil.getUserId());
        menuPo.setUpdateTime(DateUtils.getNowDate());
        menuPo.setUpdateUser(JwtUtil.getUserId());
        menuPo.setMenuComponent("");
        return ApiResult.success(systemMenuMapper.insert(menuPo));
    }

    /**
     * copy to https://www.renren.io
     *
     * @param menus 菜单po列表
     * @return 菜单vo列表
     */
    private List<MenuVo> getRes(List<SystemMenuPo> menus) {
        List<MenuVo> res = new ArrayList<>();
        menus.forEach(
                (source) -> {
                    MenuVo target = new MenuVo();
                    BeanUtils.copyProperties(source, target);
                    res.add(target);
                }
        );

        // 将菜单ID和菜单vo对象绑定在一起
        HashMap<Integer, MenuVo> bindingMap = new HashMap<>(res.size());
        for (MenuVo re : res) {
            bindingMap.put(re.getMenuId(), re);
        }

        Iterator<MenuVo> iterator = res.iterator();
        while (iterator.hasNext()) {
            MenuVo cur = iterator.next();
            MenuVo parent = bindingMap.get(cur.getParentId());
            if (parent != null) {
                parent.getChildren().add(cur);
                iterator.remove();
            }
        }
        return res;
    }
}
