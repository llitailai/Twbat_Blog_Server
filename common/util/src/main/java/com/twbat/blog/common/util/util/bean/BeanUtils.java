package com.twbat.blog.common.util.util.bean;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 21:36
 * @desciption
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    private static final String SET = "set";
    private static final String GET = "get";

    private static final Logger LOG = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 一般该方法服务于DTO转PO,PO转VO实体
     * @param source 源对象
     * @param target 目标对象
     * @param <T> 泛型标识,代表目标对象
     * @return 返回目标对象
     */
    public static <T> T convert(Object source,T target){
        LOG.info("this parameter source: -> {},this parameter target -> {}",source,target);
        assert source != null;
        BeanUtils.copyProperties(source,target);
        return target;
    }


    /**
     * 复制属性
     * @param source 源对象
     * @param supplier 目标
     * @param <S> 源对象泛型
     * @param <T> 目标对象泛型
     * @return 目标对象
     */
    public static <S,T> T copyProperties(S source, Supplier<T> supplier) {
       return copyProperties(source,supplier, (BeanCallBack<Object, T>) null);
    }

    /**
     * 复制属性
     * @param source 源对象
     * @param supplier 目标
     * @param callBack 回调函数
     * @param <S> 源对象泛型
     * @param <T> 目标对象泛型
     * @return 目标对象
     */
    public static <S,T> T copyProperties(S source, Supplier<T> supplier,BeanCallBack<S,T> callBack) {
        assert source != null;
        T target = supplier.get();
        copyProperties(source,target);
        if (callBack != null) {
            callBack.callback(source,target);
        }
        return target;
    }


    /**
     * 复制集合属性
     * @param sources 源对象集合
     * @param supplier 目标
     * @param <S> 源对象泛型
     * @param <T> 目标对象泛型
     * @return 目标对象
     */
    public static <S,T> List<T> copyPropertiesByCollection(List<S> sources,Supplier<T> supplier) {
        return copyPropertiesByCollection(sources, supplier, null);
    }


    /**
     * 复制集合属性
     * @param sources 源对象集合
     * @param supplier 目标
     * @param callBack 回调函数
     * @param <S> 源对象泛型
     * @param <T> 目标对象泛型
     * @return 目标对象
     */
    public static <S,T> List<T> copyPropertiesByCollection(List<S> sources,Supplier<T> supplier,BeanCallBack<S,T> callBack) {
        assert sources != null && sources.size() != 0;
        List<T> targets = new ArrayList<>();
        sources.forEach(
                source -> {
                    T target = copyProperties(source,supplier,callBack);
                    targets.add(target);
                }
        );
        return targets;
    }

}
