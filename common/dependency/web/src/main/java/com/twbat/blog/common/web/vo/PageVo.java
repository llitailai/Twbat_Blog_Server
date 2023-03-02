package com.twbat.blog.common.web.vo;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/14 - 15:34
 * @desciption
 */
@Data
public class PageVo<T> implements Serializable {

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 总数
     */
    private Long total;

    /**
     * 每页显示数量
     */
    private Integer size;

    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 总页数
     */
    private Integer pages;


    /**
     * 设置属性
     * @param page pageHelper 分页
     */
    public void setAttribute(Page<T> page) {
        this.setData(page.getResult());
        this.setTotal(page.getTotal());
        this.setSize(page.getPageSize());
        this.setCurrent(page.getPageNum());
        this.setPages(page.getPages());
    }
}
