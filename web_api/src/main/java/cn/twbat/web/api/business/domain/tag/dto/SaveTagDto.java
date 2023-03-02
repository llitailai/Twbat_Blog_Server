package cn.twbat.web.api.business.domain.tag.dto;

import lombok.Data;

import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/11 - 20:15
 * @desciption
 * 保存标签DTO
 */
@Data
public class SaveTagDto {

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 创建时间
     */
    private Date createTime;
}
