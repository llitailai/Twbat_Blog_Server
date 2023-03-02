package cn.twbat.web.api.business.domain.tag.po;

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
 * @date 2022/2/11 - 20:06
 * @desciption 标签PO
 */
@Data
@TableName("tag")
public class TagPo implements Serializable {

    /**
     * 标签ID
     */
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Long tagId;

    /**
     * 标签名称
     */
    @TableField("tag_name")
    private String tagName;

    /**
     * 创建用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
