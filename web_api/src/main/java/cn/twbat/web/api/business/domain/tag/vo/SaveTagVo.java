package cn.twbat.web.api.business.domain.tag.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 21:08
 * @desciption
 * 文章保存回显VO
 */
@Data
public class SaveTagVo implements Serializable {

    /**
     * 文章标签名称 (为了符合element ui 组件的要求,回显数据中tagName为value)
     */
    private String value;

    /**
     * 标签ID
     */
    private Long tagId;

    /**
     * 是否是本人创建
     */
    private Boolean meCreate;
}
