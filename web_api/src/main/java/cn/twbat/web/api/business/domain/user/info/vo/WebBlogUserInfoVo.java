package cn.twbat.web.api.business.domain.user.info.vo;

import com.twbat.blog.common.util.util.domain.BasicVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 15:07
 * @desciption
 * WEB端博客用户信息VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebBlogUserInfoVo {

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户年龄
     */
    private String age;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户简介
     */
    private String intro;

    public static WebBlogUserInfoVo getEmptyVo() {
        return new WebBlogUserInfoVo("", "", "", "", "");
    }

    public String getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age + BasicVo.AGE_TEXT;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = BasicVo.getSex(sex);
    }
}
