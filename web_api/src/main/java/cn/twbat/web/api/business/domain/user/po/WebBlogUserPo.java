package cn.twbat.web.api.business.domain.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 21:03
 * @desciption web端用户表po
 * @annotation 注意@Builder注解,
 * 使用@Builder注解必须携带@AllArgsConstructor和NoArgsConstructor,否则会出现异常
 */
@TableName("web_blog_user")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebBlogUserPo implements Serializable {

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户账号
     */
    @TableField("account")
    private String account;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;
}
