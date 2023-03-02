package cn.twbat.web.api.business.domain.article.dto;

import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 19:32
 * @desciption 保存文章dto
 */
@Data
public class SaveArticleDto implements Serializable {
    /**
     * 文章标题
     */
    @Valid(lengthGt = 2, lengthLt = 51)
    private String articleTitle;

    /**
     * 文章摘要
     */
    @Valid(lengthGt = 0, lengthLt = 101)
    private String articleDesc;

    /**
     * 文章正文
     */
    @Valid
    private String articleContent;


    /**
     * 文章状态(私密,公开)
     */
    @Valid
    private Integer state;

    /**
     * 创建时间
     */
    @Valid
    private Date createTime;

    /**
     * 新创建的标签集合
     */
    private List<String> createTags;

    /**
     * 已经存在的标签集合
     */
    private List<Long> existTagIds;
}
