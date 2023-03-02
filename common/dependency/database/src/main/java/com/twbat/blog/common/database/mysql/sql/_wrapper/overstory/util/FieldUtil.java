package com.twbat.blog.common.database.mysql.sql._wrapper.overstory.util;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.twbat.blog.common.database.mysql.sql._wrapper.overstory.lambda.FieldFunction;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class FieldUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldUtil.class);

    private final static Map<String, String> FIELD_CACHE = new HashMap<>();

    public static String getFiledName(FieldFunction<?, ?> function) {
        Method writeReplaceMethod = null;
        try {
            writeReplaceMethod = function.getClass().getDeclaredMethod("writeReplace");
        } catch (NoSuchMethodException e) {
            LOGGER.error("writeReplaceMethod not find,exception:{}", e);
            return StringUtils.EMPTY;
        }
        boolean accessible = writeReplaceMethod.isAccessible();
        writeReplaceMethod.setAccessible(true);
        SerializedLambda serializedLambda = null;
        try {
            serializedLambda = (SerializedLambda) writeReplaceMethod.invoke(function);
        } catch (IllegalAccessException e) {
            LOGGER.error("writeReplaceMethod访问权限被关闭 exception:{}", e);
            return StringUtils.EMPTY;
        } catch (InvocationTargetException e) {
            LOGGER.error("writeReplaceMethod未找到, exception:{}", e);
            return StringUtils.EMPTY;
        }
        writeReplaceMethod.setAccessible(accessible);
        String fieldName = serializedLambda.getImplMethodName().substring("get".length());
        fieldName = fieldName.replaceFirst(fieldName.charAt(0) + "", (fieldName.charAt(0) + "").toLowerCase());
        if (FIELD_CACHE.containsKey(fieldName)) {
            return FIELD_CACHE.get(fieldName);
        }
        Field field = null;
        try {
            Class<?> fieldClass = Class.forName(serializedLambda.getImplClass().replace("/", "."));
            field = fieldClass.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        //转驼峰命名
        String databaseFieldName = fieldName.replaceAll("[A-Z]", "_$0").toLowerCase();

        FIELD_CACHE.put(fieldName, databaseFieldName);
        return databaseFieldName;
    }

    public static String getDatabaseField(String fieldName) {
        String res = FIELD_CACHE.get(fieldName);
        if (res == null) {
            res = getDatabaseFieldName(fieldName);
            FIELD_CACHE.put(fieldName, res);
        }
        return res;
    }

    public static HashMap<String, Object> convertHashMapByEntity(Object entity) {
        HashMap<String, Object> res = new HashMap<>();
        Class entityCls = getClass(entity);
        Field[] fields = getFields(entityCls);
        for (Field field : fields) {
            field.setAccessible(true);
            String methodName = getMethodName(field.getName());
            Object invoke = invoke(entity, methodName, entityCls);
            if (invoke == null || "".equals(invoke.toString().trim())) {
                continue;
            }
            res.put(getDatabaseField(field.getName()), invoke);
        }
        return res;
    }

    private static final Object invoke(Object obj, String methodName, Class cls) {
        Method method = null;
        try {
            method = cls.getMethod(methodName);
            Object invoke = method.invoke(obj);
            return invoke;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getDatabaseFieldName(String name) {
        return name.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static final Field[] getFields(Class cls) {
        return cls.getDeclaredFields();
    }

    private static final Class getClass(Object var0) {
        if (var0 == null) {
            return null;
        }
        return var0.getClass();
    }

    private static final String getMethodName(String param) {
        return "get" + capitalize(param);
    }

    private static String capitalize(String param) {
        char[] chars = param.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
}
