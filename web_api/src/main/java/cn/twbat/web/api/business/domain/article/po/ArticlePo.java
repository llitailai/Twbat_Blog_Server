package cn.twbat.web.api.business.domain.article.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 14:08
 * @desciption
 * 文章PO
 */
@TableName("article")
@Data
public class ArticlePo implements Serializable {

    /**
     * 文章id
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /**
     * 文章标题
     */
    @TableField("article_title")
    private String articleTitle;

    /**
     * 文章摘要
     */
    @TableField("article_desc")
    private String articleDesc;

    /**
     * 文章正文
     */
    @TableField("article_content")
    private String articleContent;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 删除标识
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 文章状态(私密,公开)
     */
    @TableField("state")
    private Integer state;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
}
