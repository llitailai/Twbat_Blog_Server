package com.twbat.blog.common.util.util.bean;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 21:40
 * @desciption
 * BeanUtils Callback interface
 */
@FunctionalInterface
public interface BeanCallBack<S,T> {

    /**
     * 回调函数
     * @param s source
     * @param t target
     */
    void callback(S s, T t);
}
