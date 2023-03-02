package cn.twbat.web.api.business.service.permissions;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import cn.twbat.web.api.business.domain.permission.annotation.PermissionCode;
import cn.twbat.web.api.business.domain.permission.code.scan.ScanPermissionService;
import com.twbat.blog.center.permission.global.exception.business.permission.PermissionServiceNotFoundException;
import com.twbat.blog.common.util.util.spring.SpringUtil;
import com.twbat.blog.common.web.valid.util.DateUtils;
import com.twbat.blog.common.web.valid.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 13:50
 * @desciption
 * 权限验证组件
 */
@Component
public class PermissionComponent {


    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionComponent.class);
    /**
     * 权限实现类Map集合
     */
    private final Map<String, Object> permissionServiceMap;
    /**
     * 封装权限实现类函数集合
     */
    private Map<String, Map<String, Method>> permissionServiceMethodMap;


    @Resource
    private SpringUtil springUtil;

    public PermissionComponent() {
        ScanPermissionService scanPermissionService = new ScanPermissionService();
        this.permissionServiceMap = scanPermissionService.getPermissionImplMap();
        init();
    }

    /**
     * 初始化 封装权限实现类函数集合
     */
    private void init() {
        Map<String, Map<String, Method>> methodMap = new ConcurrentHashMap<>(this.permissionServiceMap.size());
        permissionServiceMap.forEach(
                (key, val) -> {
                    final Method[] methods = val.getClass().getDeclaredMethods();
                    Map<String, Method> codeMethodMap = new ConcurrentHashMap<>(methods.length);
                    for (Method method : methods) {
                        final PermissionCode code = method.getAnnotation(PermissionCode.class);
                        if (code != null && StringUtils.isNotEmpty(code.value())) {
                            codeMethodMap.put(code.value(), method);
                        }
                    }
                    methodMap.put(key, codeMethodMap);
                    LOGGER.info("init For PermissionComponent Class : Methods: {},  Time: {}", Arrays.toString(methods), DateUtils.getTime());
                }
        );
        this.permissionServiceMethodMap = methodMap;
    }


    /**
     * 该函数通过传入的key,methodKey 直接定位到具体执行权限验证实现函数
     * 通过SpringUtil,key作为参数直接获取 权限验证实现类的代理对象 (为什么key可以直接获取到实现类的代理对象,看 key 参数的介绍)
     * 通过代理对象执行PermissionService类的permission函数进行验证
     * @param key  不详述,具体看链接 {@link cn.twbat.web.api.business.domain.permission.annotation.PermissionServiceAnnotation}
     * @param methodKey 不详述,具体看链接 {@link PermissionCode}
     * @param params 调用函数所需的参数
     */
    public void permission(String key, String methodKey, Object... params) {
        final Object service = this.permissionServiceMap.get(key);
        if (service == null) {
            throw new PermissionServiceNotFoundException(String.format("Not Found Contains @PermissionServiceAnnotation(value=%s)'s Service Implements", key));
        }

        /*
            这里必须要获取代理对象,
            通过代理对象调用PermissionService中的permission函数
            虽然可以通过Class对象直接创建,但是这样会导致@Service中注入的依赖无法使用
         */
        final Object bean = springUtil.getBean(key);
        final Map<String, Method> codeMethodMap = this.permissionServiceMethodMap.get(key);
        if (codeMethodMap != null && codeMethodMap.size() != 0) {
            final Method method = codeMethodMap.get(methodKey);
            if (method != null) {
                try {
                    method.setAccessible(true);
                    ((PermissionService) bean).permission(method,params);
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error("not found method: {},please check your params size is right,param:{}", method.getName(), params);
                }
            }
        }
        throw new PermissionServiceNotFoundException(String.format("Not Found Contains @PermissionCode(value=%s)'s Method In %s Class", methodKey, key));

    }


}
