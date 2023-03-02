package com.twbat.blog.center.permission.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.annotation.Permission;
import com.twbat.blog.center.permission.domain.SystemRolePO;
import com.twbat.blog.center.permission.domain.SystemUserRolePO;
import com.twbat.blog.center.permission.repository.SystemRoleRepository;
import com.twbat.blog.center.permission.repository.SystemUserRoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author darkltl
 * @className PermissionInterceptor
 * @email darkltl@163.com
 * @date 2021/7/22 - 15:06
 * @description 权限拦截器
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Resource
    private SystemUserRoleRepository systemUserRoleRepository;

    @Resource
    private SystemRoleRepository systemRoleRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
            处理权限流程
            首先从token中拿取用户个人信息
            查询用户所属角色
            通过角色查询是否具备函数上标注的权限代码(permissionCode)
            如果具有则放行,如果不具备则抛出无权限异常
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            Permission permission = handlerMethod.getMethod().getAnnotation(Permission.class);
            if (permission != null && permission.require()) {
                // execute
                SystemUserRolePO role = systemUserRoleRepository.selectOne(new LambdaQueryWrapper<SystemUserRolePO>().select(SystemUserRolePO::getRoleId).eq(SystemUserRolePO::getUserId, userId));
                return systemRoleRepository.selectCount(
                        new LambdaQueryWrapper<SystemRolePO>()
                                .eq(SystemRolePO::getPermissionCode, permission.permissionCode())
                                .eq(SystemRolePO::getRoleId, role.getRoleId())
                ) > 0;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
