package com.twbat.blog.common.web.valid.util;

import java.util.Collection;

/**
 * @author darkltl
 * @email darkltl@163.com
 * @description 字符串工具类
 */
public class StringUtils {

    /**
     * 判断传入的参数是否为空
     *
     * @param obj 参数
     * @return boolean
     * true : 为空
     * false : 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return ((String) obj).length() == 0;
        }

        return false;
    }


    /**
     * 判断传入的参数是否不为空
     *
     * @param obj 参数
     * @return boolean
     * true : 不为空
     * false : 为空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断传入的集合是否是空集合
     *
     * @param collection 集合
     * @return boolean
     * true : 空集合
     * false : 非空集合
     */
    public static boolean collectionIsEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static void main(String[] args) {
        Object obj = "";
        System.out.println(isEmpty(obj.toString()));
    }

}
