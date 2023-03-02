package cn.twbat.web.api.business.domain.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/12 - 21:01
 * @desciption
 * 标签VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo implements Serializable {

    /**
     * 标签ID
     */
    private Long tagId;

    /**
     * 标签名 (为了符合element ui 组件的要求,回显数据中tagName为value)
     */
    private String value;

    /**
     * 创建标签用户名名称
     */
    private String createUser;

}
