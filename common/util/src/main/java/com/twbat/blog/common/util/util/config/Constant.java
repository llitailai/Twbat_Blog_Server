package com.twbat.blog.common.util.util.config;

/**
 * @author darkltl
 * @className Constant
 * @email darkltl@163.com
 * @date 2021/8/16 - 14:01
 * @description
 */
public enum Constant {
    USER_ID("userId", "token所存储的用户信息key"),
    USER_PASSWORD("userPassword", "token所存储的用户信息key"),
    APPLICATION_CONFIG_NAME("application.yml", "配置文件名称"),
    TOKEN_SECRET_NAME("blog.secret", "Token密匙名称"),
    TOKEN_BLOG_OUT_DAY("blog.out.day", "Token过期时间"),
    TOKEN_BLOG_EXTEND_EXPIRATION("blog.extend.expiration", "token延长期限"),
    TOKEN_HEADER("Authorization", "token头部标签"),
    TOKEN_PREFIX("Bearer ", "token前缀"),
    SYSTEM_USER_REDIS_KEY("SYSTEM_USER_REDIS_KEY", "系统用户redis key"),
    WEB_USER_REDIS_KEY("WEB_USER_REDIS_KEY", "web用户redis key"),
    IP_COUNT_REDIS_KEY("IP_COUNT_REDIS_KEY", "IP统计 redis key"),
    TAG_NAME_REDIS_KEY("TAG_NAME_REDIS_KEY_", "标签名称key"),
    TAGS_REDIS_KEY("TAGS_LIST_KEY", "标签集合key"),
    SYSTEM_ADMIN_TEL_ONE("13993974926", "第一个管理员账号"),
    ROLE_ADMIN_ID("1", "超级管理员角色ID"),
    TOKEN_FLAG("TokenFlag", "Token正常使用状态"),
    RESOURCE_PREFIX("/profile", "资源映射路径 前缀");

    /**
     * 常量值
     */
    private String code;

    /**
     * 常量意义
     */
    private String msg;

    Constant(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    /**
     * 序号
     *
     * @return
     */
    public int sequence() {
        return ordinal();
    }
}
