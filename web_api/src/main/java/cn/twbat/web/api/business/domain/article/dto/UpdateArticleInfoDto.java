package cn.twbat.web.api.business.domain.article.dto;

import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/16 - 21:47
 * @desciption
 * 修改文章信息DTO
 */
@Data
public class UpdateArticleInfoDto implements Serializable {

    /**
     * 文章标题
     */
    @Valid
    private String articleTitle;

    /**
     * 文章摘要
     */
    @Valid
    private String articleDesc;

    /**
     * 文章状态 公开/私有
     */
    @Valid
    private Integer state;

    /**
     * 文章内容 (正文) markdown
     */
    @Valid
    private String articleContent;

    /**
     * 文章修改时间
     */
    @Valid
    private Date updateTime;

    /**
     * 文章ID
     */
    @Valid
    private Long articleId;

}
