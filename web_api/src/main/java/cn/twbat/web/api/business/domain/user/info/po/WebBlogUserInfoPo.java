package cn.twbat.web.api.business.domain.user.info.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 14:55
 * @desciption web端用户信息
 */
@Data
@TableName("user_info")
public class WebBlogUserInfoPo implements Serializable {

    /**
     * 用户信息表主键ID
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Integer infoId;


    /**
     * 头像地址
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 用户昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 用户年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 用户性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 用户简介
     */
    @TableField("intro")
    private String intro;

    /**
     * 用户主键ID(关联)
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
