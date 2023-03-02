package cn.twbat.web.api.business.domain.article.vo;

import cn.twbat.web.api.business.domain.tag.vo.TagVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/14 - 13:51
 * @desciption
 * 文章VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo implements Serializable {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章摘要
     */
    private String articleDesc;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章创建时间
     */
    private Date createTime;

    /**
     * 文章标签
     */
    private List<TagVo> tags;
}
