package com.twbat.blog.center.permission.global.exception.enums;

/**
 * @author darkltl
 * @className BusinessExceptionEnum
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:33
 * @description 业务异常以6开头
 * 700 为业务异常
 */
public enum BusinessExceptionEnum {
    BUSINESS_TOKEN_INVALID("token无效", 700),
    REGISTER_USER_PHONE_EXIST("注册手机号已存在", 701),
    USER_NOT_EXIST_EXCEPTION("用户账号或密码错误", 702),
    USER_INFO_IS_EXIST_NULL("用户信息已存在", 703),
    FILE_NOT_FOUND_EXCEPTION("文件不存在", 704),
    MANY_TIMES_APPLY_CODE_EXCEPTION("验证码获取次数过多,请稍后再试!", 705),
    CODE_BE_OVERDUE_EXCEPTION("验证码已失效,请重新获取!", 706),
    USER_PASSWORD_NOT_IDENTICAL_EXCEPTION("两次输入密码不一致!", 707),
    EMAIL_IS_ALREADY_USE_EXCEPTION("邮件已经被绑定了,请更换邮件", 708),
    ARTICLE_TAG_EXCEED_ASSIGN_NUMBER_EXCEPTION("文章创建标签超出指定数量", 801),
    BLOG_NOT_FOUND_EXCEPTION("该博客不存在", 802),
    USER_INFO_IS_NULL("用户信息为空", 900),
    PERMISSION_SERVICE_NOT_FOUND_EXCEPTION("权限服务实现类未找到！", 1000),
    ;


    private String msg;

    private Integer code;

    BusinessExceptionEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return code;
    }
}
