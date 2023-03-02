package cn.twbat.web.api.business.domain.user.info.dto;

import com.twbat.blog.common.web.valid.annotations.Max;
import com.twbat.blog.common.web.valid.annotations.Min;
import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 16:52
 * @desciption
 * 保存WEB端博客用户信息DTO
 */
@Data
public class SaveWebBlogUserInfoDto implements Serializable {

    /**
     * 用户头像地址
     */
    @Valid
    private String avatar;

    /**
     * 用户昵称
     */
    @Valid
    private String nickname;

    /**
     * 用户年龄
     */
    @Valid(max = @Max(max = 150), min = @Min(min = 1))
    private Integer age;

    /**
     * 用户性别
     */
    @Valid(max = @Max(max = 2), min = @Min(min = 1))
    private Integer sex;

    /**
     * 用户简介
     */
    private String intro;
}
