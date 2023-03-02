package cn.twbat.web.api.business.domain.permission.code.scan;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import cn.twbat.web.api.WebApiApplication;
import cn.twbat.web.api.business.domain.permission.annotation.ModuleInfo;
import cn.twbat.web.api.business.domain.permission.annotation.PermissionServiceAnnotation;
import cn.twbat.web.api.business.service.permissions.PermissionService;
import com.twbat.blog.common.web.valid.util.DateUtils;
import com.twbat.blog.common.web.valid.util.StringUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 10:50
 * @desciption
 */
public class ScanPermissionService {

    private static final String TARGET = "target";

    private static final String CLASSES = "classes";

    private static final String SUFFIX = "impl";


    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanPermissionService.class);


    /**
     * 模块信息 (默认指的是模块名称)
     */
    @SuppressWarnings("all")
    private String moduleInfo = null;

    /**
     * 权限实现类Map集合
     * Key : String permissionType 权限类型
     * value: Object permissionImplInstance 权限实现类实例
     */
    private Map<String,Object> permissionImplMap ;


    /**
     * 获取权限实现类Map集合
     * @return 权限实现类Map集合
     */
    public Map<String, Object> getPermissionImplMap() {
        return permissionImplMap;
    }

    /**
     * 无参构造函数
     * 用于初始化信息
     */
    public ScanPermissionService() {
        this.moduleInfo = WebApiApplication.class.getAnnotation(ModuleInfo.class).name();
        // 获取当前项目路径
        String property = System.getProperty("user.dir");
        // 拼接模块名称
        String prefix = property + File.separator + this.moduleInfo;
        // 拼接打包后文件夹
        prefix += File.separator+TARGET + File.separator + CLASSES + File.separator;
        // 最终转换号的路径
        String finalPath = prefix + resolvePackagePath(PermissionService.class.getPackage().getName());
        // 赋值扫描完成的权限实现类
        this.permissionImplMap = scanService(finalPath);
    }

    /**
     * 解析包路径
     * @param var0 包路径
     * @return 解析后的包路径
     */
    private String resolvePackagePath(String var0) {
        assert StringUtils.isNotEmpty(var0) : "packageName can not be null";
        return resolvePackageToPath(var0) + File.separator + SUFFIX;
    }

    /**
     * 解析包路径为Java路径
     * @param pack
     * @return
     */
    private String resolvePackageToPath(String pack) {
        return pack.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
    }


    /**
     * 扫描函数
     * 将扫描到的权限实现封装到Map集合中
     * @param path 路径
     * @return 扫描后的权限实现Map集合
     */
    private Map<String,Object> scanService(String path) {
        assert StringUtils.isNotEmpty(this.moduleInfo) : "Module Info Can not Be null";

        // 通过传入的路径(目录,权限实现类的目录,path组成请看无参构造函数)
        File file = new File(path);
        LOGGER.info("Permission Impl File Path: {}",file);
        // 获取路径下的所有文件名称
        String[] list = file.list();
        // 封装类文件名称的集合
        List<String> clsNames = new ArrayList<>();
        // 如果路径下具有文件,则继续扫描
        if (list != null) {
            // 创建URL数组,用于稍后创建URLClassLoader时提供参数
            URL[] url = new URL[list.length];
            // 变量递增
            int index = 0;
            for (String s : list) {
                // s 是后缀为.class的文件
                File clsFile = new File(path, s);
                clsNames.add(s);
                try {
                    // 将类文件转换为URL并添加到URL数组中
                    url[index++] = clsFile.toURI().toURL();
                } catch (MalformedURLException e) {
                    LOGGER.error("Scan Permission Service Impl Convert URL Exception : {},Time: {}",e,DateUtils.getTime());
                }
            }
            // 创建URL类加载器
            URLClassLoader classLoader = new URLClassLoader(url);
            // 封装权限实现实例的返回结果数组
            ConcurrentHashMap<String,Object> resMap = new ConcurrentHashMap<>(clsNames.size());
            clsNames.forEach(
                    clsName -> {
                        try {
                            // 将.class文件后缀取消
                            clsName = clsName.substring(0,clsName.lastIndexOf(".class"));
                            // 加载class文件
                            Class<?> cls = classLoader.loadClass(PermissionService.class.getPackage().getName()+"."+SUFFIX+"."+clsName);
                            // 获取class文件上的权限服务注解
                            final PermissionServiceAnnotation res = cls.getAnnotation(PermissionServiceAnnotation.class);
                            // 如果权限服务注解为空则代表不是权限服务实现类
                            if (res != null) {
                                // 判断注解中的value是否不为空
                                if (StringUtils.isNotEmpty(res.value())) {
                                    try {
                                        // 将注解中的value和权限服务实现类实例存放到最终返回结果集
                                        resMap.put(res.value(),cls.newInstance());
                                    } catch (InstantiationException | IllegalAccessException e) {
                                        LOGGER.error(" Scan Permission Service Impl Not Find No Args Constructor, Permission Service Impl Name : {},Exception Time : {}",clsName,DateUtils.getTime());
                                    }
                                }
                            }
                        } catch (ClassNotFoundException e) {
                           LOGGER.error("Scan Class Not Found Exception, clsName:{},exception time: {}",clsName, DateUtils.getTime());
                        }
                    }
            );

            return resMap;
        }
        return null;
    }


    public static void main(String[] args) {
        ScanPermissionService service = new ScanPermissionService();
    }
}
