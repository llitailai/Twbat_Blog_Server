package com.twbat.blog.common.util.util;

import com.twbat.blog.common.util.util.config.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author darkltl
 * @className Profiles
 * @email darkltl@163.com
 * @date 2021/8/16 - 14:09
 * @description
 */
public class Profiles {

    private static final Properties PROPERTIES;


    static {
        PROPERTIES = new Properties();
        loadProfiles(Profiles.class, Constant.APPLICATION_CONFIG_NAME.getCode());
    }

    public Profiles(Class cls, String configName) {
        loadProfiles(cls, configName);
    }

    private static void loadProfiles(@NonNull Class cls, @NonNull String configName) {
        File file = new File(cls.getClassLoader().getResource(configName).getFile());
        try (FileInputStream fis = new FileInputStream(file)) {
            PROPERTIES.load(fis);
        } catch (Exception e) {
        }
    }

    /**
     * 通过传入的key值获取其配置文件中对应的值
     * 如果不存在key则返回的值为
     *
     * @param key
     * @return
     */
    public static Object getValue(@NonNull String key) {
        if (PROPERTIES.containsKey(key)) {
            if (PROPERTIES.getProperty(key) != null) {
                return PROPERTIES.getProperty(key);
            }
        }
        return StringUtils.EMPTY;
    }

    public static Object getValue(@NonNull String key, @NonNull String defaultValue) {
        if (PROPERTIES.containsKey(key)) {
            return PROPERTIES.getProperty(key, defaultValue);
        }
        return StringUtils.EMPTY;
    }
}
