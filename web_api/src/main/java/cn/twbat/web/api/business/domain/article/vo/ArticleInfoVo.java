package cn.twbat.web.api.business.domain.article.vo;

import cn.twbat.web.api.business.domain.tag.vo.TagVo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/15 - 22:30
 * @desciption
 * 文章信息VO
 */
@Data
public class ArticleInfoVo implements Serializable {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章ID
     */
    private Integer authorId;

    /**
     * 文章状态 公开/私有
     */
    private Integer state;

    /**
     * 文章创建时间
     */
    private Date createTime;

    /**
     * 文章标签集合
     */
    private List<TagVo> tags;

    /**
     * 文章内容 (正文) markdown
     */
    private String articleContent;

    /**
     * 文章摘要
     */
    private String articleDesc;

    /**
     * 修改是否显示状态
     */
    private Boolean editIsShow;
}
