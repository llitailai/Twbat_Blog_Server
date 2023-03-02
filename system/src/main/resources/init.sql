CREATE
DATABASE twbat_blog_sys;

USE twbat_blog_sys;

CREATE TABLE system_user
(
-- 后台用户量不多,用smallint即可
    user_id       smallint primary key auto_increment comment '用户主键ID',
    user_tel      varchar(64) not null comment '用户电话号码 (加密)',
    user_password varchar(64) not null comment '8-16位长度密码 (加密)',
    status        tinyint(1) not null comment '用户状态,0启用 ,1已删除 ,2已停用 (停用可复原,删除不可见)',
    create_time   datetime    not null comment '创建时间',
    create_user   smallint    not null comment '创建人',
    update_time   datetime    not null comment '修改时间(默认创建时变更新修改时间)',
    update_user   smallint    not null comment '修改人'
) comment '系统用户表';


CREATE TABLE system_user_info
(
    info_id     smallint primary key auto_increment comment '系统用户信息表',
    user_id     smallint     not null comment '系统用户ID',
    nickname    varchar(16)  not null comment '系统用户昵称',
    avatar      varchar(256) not null comment '系统用户头像',
    introduce   varchar(500) not null default '' comment '个人介绍,最多500字',
    tel_prefix  char(3)      not null comment '用户手机号前缀3位',
    tel_suffix  char(4)      not null comment '用户手机号尾号4位',
    create_time datetime     not null comment '创建时间',
    create_user smallint     not null comment '创建人',
    update_time datetime     not null comment '修改时间(默认创建时变更新修改时间)',
    update_user smallint     not null comment '修改人'
) comment '系统用户信息表';

CREATE TABLE system_log
(
    log_id            bigint primary key auto_increment comment '日志ID',
    log_name          varchar(256)  not null comment '日志名称',
    log_level         varchar(16)   not null comment '日志等级',
    is_sensitive_info tinyint(1) not null default 0 comment '是否具有敏感信息 0具备,1不具备',
    log_model         varchar(32)   not null comment '日志模块名称',
    log_package       varchar(128)  not null comment '日志发生所在包名',
    log_class         varchar(64)   not null comment '日志发生所在类名',
    log_method        varchar(64)   not null comment '日志发生所在函数',
    log_param         varchar(1024) not null comment '日志参数,过长文本,敏感文本,用true / false表示,传入为true,未穿入为false',
    log_content       varchar(128)  not null comment '日志说明',
    log_time          datetime      not null comment '日志记录时间',
    user_id           smallint      not null comment '执行操作用户id',
    create_time       datetime      not null comment '创建时间'
) comment '系统日志表';

CREATE TABLE system_menu
(
    menu_id         smallint primary key auto_increment comment '菜单id',
    menu_name       varchar(8)   not null comment '菜单名称',
    parent_id       smallint comment '父级菜单ID',
    menu_icon       varchar(255) not null comment '菜单图标',
    menu_type       tinyint(1) not null comment '菜单类型 0 菜单 1 按钮',
    menu_sort       int          not null comment '菜单排序',
    menu_component  varchar(10)  not null comment '组件名称',
    menu_permission varchar(64) default '' comment '组件权限标识',
    menu_url        varchar(64)  not null comment '组件URL',
    create_time     datetime     not null comment '创建时间',
    create_user     smallint     not null comment '创建人',
    update_time     datetime     not null comment '修改时间(默认创建时变更新修改时间)',
    update_user     smallint     not null comment '修改人'
) comment '菜单表(路由表)';

CREATE TABLE system_permission_behavior
(
    behavior_id          int primary key auto_increment comment '行为ID',
    behavior_name        varchar(16) not null comment '行为名称',
    behavior_code        varchar(32) not null comment '行为代码',
    behavior_description varchar(64) not null comment '行为描述',
    create_time          datetime    not null comment '创建时间',
    create_user          smallint    not null comment '创建人',
    update_time          datetime    not null comment '修改时间(默认创建时变更新修改时间)',
    update_user          smallint    not null comment '修改人'
) comment '系统权限行为定义表';

CREATE TABLE system_permission
(
    permission_id   int primary key auto_increment comment '权限ID',
    parent_id       int default null comment '父级权限ID',
    permission_code varchar(128) not null comment '权限代码',
    permission_name varchar(32)  not null comment '权限名称',
    behavior_code   varchar(32)  not null comment '权限行为',
    menu_id         smallint     not null comment '所属视图对象',
    create_time     datetime     not null comment '创建时间',
    create_user     smallint     not null comment '创建人',
    update_time     datetime     not null comment '修改时间(默认创建时变更新修改时间)',
    update_user     smallint     not null comment '修改人'
) comment '系统权限表';

CREATE TABLE system_role
(
    role_id     int primary key auto_increment comment '角色ID',
    role_name   varchar(32) not null comment '角色名称',
    create_time datetime    not null comment '创建时间',
    create_user smallint    not null comment '创建人',
    update_time datetime    not null comment '修改时间(默认创建时变更新修改时间)',
    update_user smallint    not null comment '修改人'
)comment '角色表';


CREATE TABLE system_user_role
(
    user_role_id bigint primary key auto_increment comment '用户角色关联关系表ID',
    role_id      int      not null comment '角色ID',
    user_id      int      not null comment '用户ID',
    create_time  datetime not null comment '创建时间',
    create_user  smallint not null comment '创建人',
    update_time  datetime not null comment '修改时间(默认创建时变更新修改时间)',
    update_user  smallint not null comment '修改人'
) comment '系统用户角色关联关系表';


CREATE TABLE system_ip_record
(
    ip_id                bigint primary key auto_increment comment '系统访问IP统计表ID',
    ip                   varchar(32)  not null comment '访问IP',
    visit_time           datetime     not null comment '访问时间',
    visit_method         varchar(128) not null comment '访问函数',
    visit_request_method varchar(8)   not null comment '访问请求方法',
    create_time          datetime     not null comment '创建时间',
    create_user          varchar(128) not null comment '创建人{创建线程名称+创建线程ID}'
) comment '系统访问IP统计表';



CREATE TABLE system_login_record
(
    record_id   bigint primary key auto_increment comment '用户登录日志记录ID',
    record_ip   varchar(32) not null comment '用户登录IP',
    login_time  datetime    not null comment '用户登录时间',
    user_id     smallint    not null comment '登录用户ID',
    create_time datetime    not null comment '创建时间'
)


CREATE TABLE system_settings
(
    setting_id    int primary key auto_increment comment '系统设置表ID',
    setting_name  varchar(64)   not null comment '系统设置名称',
    setting_value varchar(1024) not null comment '系统设置值',
    setting_code  varchar(128)  not null comment '系统代码',
    remark        varchar(500) default '' comment '备注',
    create_time   datetime      not null comment '创建时间',
    update_time   datetime comment '修改时间',
    create_user   varchar(128)  not null comment '创建人',
    update_user   varchar(128) comment '修改人'
) comment '系统设置表';

CREATE TABLE system_photo
(
    photo_id    int primary key auto_increment comment '照片ID',
    photo_name  varchar(628)  not null comment '照片名称',
    photo_url   varchar(1024) not null comment '照片地址',
    photo_type  tinyint       not null comment '照片类型',
    upload_time datetime      not null comment '上传时间',
    create_time datetime      not null comment '创建时间'
)


-- insert
    INSERT INTO `twbat_blog_sys`.`system_permission` (`permission_id`, `parent_id`, `permission_code`, `permission_name`, `behavior_code`, `menu_id`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (1, NULL, 'root', '系统管理展示权限', 'system_setting_show', 1, '2021-09-14 12:49:52', 0, '2021-09-14 12:49:57', 0);
INSERT INTO `twbat_blog_sys`.`system_permission` (`permission_id`, `parent_id`, `permission_code`, `permission_name`,
                                                  `behavior_code`, `menu_id`, `create_time`, `create_user`,
                                                  `update_time`, `update_user`)
VALUES (2, 1, 'root:menu:list', '菜单管理展示权限', 'menu_show_permission', 4, '2021-09-14 12:51:52', 0, '2021-09-14 12:52:04',
        0);
INSERT INTO `twbat_blog_sys`.`system_permission` (`permission_id`, `parent_id`, `permission_code`, `permission_name`,
                                                  `behavior_code`, `menu_id`, `create_time`, `create_user`,
                                                  `update_time`, `update_user`)
VALUES (3, 1, 'root:role:list', '角色管理展示权限', 'role_show_permission', 3, '2021-09-14 12:53:13', 0, '2021-09-14 12:53:19',
        0);
INSERT INTO `twbat_blog_sys`.`system_permission` (`permission_id`, `parent_id`, `permission_code`, `permission_name`,
                                                  `behavior_code`, `menu_id`, `create_time`, `create_user`,
                                                  `update_time`, `update_user`)
VALUES (4, 1, 'root:job:list', '定时任务管理展示权限', 'job_show_permission', 6, '2021-09-14 12:53:54', 0, '2021-09-14 12:53:59',
        0);
INSERT INTO `twbat_blog_sys`.`system_permission` (`permission_id`, `parent_id`, `permission_code`, `permission_name`,
                                                  `behavior_code`, `menu_id`, `create_time`, `create_user`,
                                                  `update_time`, `update_user`)
VALUES (5, 1, 'root:admin:list', '管理员列表展示权限', 'admin_show_permission', 2, '2021-09-14 12:55:35', 0,
        '2021-09-14 12:55:40', 0);
INSERT INTO `twbat_blog_sys`.`system_permission` (`permission_id`, `parent_id`, `permission_code`, `permission_name`,
                                                  `behavior_code`, `menu_id`, `create_time`, `create_user`,
                                                  `update_time`, `update_user`)
VALUES (6, 1, 'root:sql:list', 'SQL监控展示权限', 'sql_show_permission', 5, '2021-09-14 12:56:20', 0, '2021-09-14 12:56:27',
        0);

INSERT INTO `twbat_blog_sys`.`system_role` (`role_id`, `role_name`, `create_time`, `create_user`, `update_time`,
                                            `update_user`)
VALUES (1, '超级管理员', '2021-09-14 15:12:00', 0, '2021-09-14 15:12:06', 0);
INSERT INTO `twbat_blog_sys`.`system_user` (`user_id`, `user_tel`, `user_password`, `status`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (1, '13993974926', '17c77d64a16b383b09034735e28965337d7c323e88b52483', 0, '2021-09-13 16:41:11', 0,
        '2021-09-13 16:41:22', 0);

INSERT INTO `twbat_blog_sys`.`system_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_icon`, `menu_type`, `menu_sort`,
                                            `menu_component`, `menu_permission`, `menu_url`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (1, NULL, '系统管理', 'system', 0, 0, 'system', '', '', '2021-09-14 09:40:44', 0, '2021-09-14 09:40:55', 0);
INSERT INTO `twbat_blog_sys`.`system_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_icon`, `menu_type`, `menu_sort`,
                                            `menu_component`, `menu_permission`, `menu_url`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (2, 1, '管理员列表', 'admin', 1, 1, 'admin', 'root:admin:list', 'sys/user', '2021-09-14 09:41:57', 0,
        '2021-09-14 09:42:05', 0);
INSERT INTO `twbat_blog_sys`.`system_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_icon`, `menu_type`, `menu_sort`,
                                            `menu_component`, `menu_permission`, `menu_url`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (3, 1, '角色管理', 'role', 1, 2, 'role', 'root:role:list', 'sys/role', '2021-09-14 09:43:09', 0,
        '2021-09-14 09:43:13', 0);
INSERT INTO `twbat_blog_sys`.`system_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_icon`, `menu_type`, `menu_sort`,
                                            `menu_component`, `menu_permission`, `menu_url`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (4, 1, '菜单管理', 'menu', 1, 3, 'menu', 'root:menu:list', 'sys/menu', '2021-09-14 09:43:09', 0,
        '2021-09-14 09:43:13', 0);
INSERT INTO `twbat_blog_sys`.`system_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_icon`, `menu_type`, `menu_sort`,
                                            `menu_component`, `menu_permission`, `menu_url`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (5, 1, 'SQL监控', 'sql', 1, 4, 'sql', 'root:sql:list', 'http://127.0.0.1:9001/druid/sql.html',
        '2021-09-14 10:11:52', 0, '2021-09-14 10:12:01', 0);
INSERT INTO `twbat_blog_sys`.`system_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_icon`, `menu_type`, `menu_sort`,
                                            `menu_component`, `menu_permission`, `menu_url`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (6, 1, '定时任务', 'job', 1, 5, 'job', 'root:job:list', 'job/schedule', '2021-09-14 10:14:51', 0,
        '2021-09-14 10:14:57', 0);
INSERT INTO `twbat_blog_sys`.`system_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_icon`, `menu_type`, `menu_sort`,
                                            `menu_component`, `menu_permission`, `menu_url`, `create_time`,
                                            `create_user`, `update_time`, `update_user`)
VALUES (8, 1, '日志管理', 'config', 0, 0, '', '', '', '2021-09-14 07:02:27', 1, '2021-09-14 07:02:27', 1);


-- CREATE BLOG WEB DATABASE
CREATE DATABASE blog_web;
use blog_web;
CREATE TABLE web_blog_user
(
    user_id  int primary key auto_increment comment '用户主键ID',
    account  varchar(32) not null comment '用户账号',
    password varchar(64) not null comment '用户密码'
) comment '用户表';


CREATE TABLE user_info
(
    info_id     int primary key auto_increment comment '用户信息表主键ID',
    avatar      varchar(1024) not null comment '用户头像',
    nickname    varchar(32)  default '' comment '用户昵称',
    age         int          default -1 comment '用户年龄',
    sex         tinyint(1) default -1 comment '用户性别',
    intro       varchar(128) default '' comment '用户简介',
    user_id     int           not null comment '用户主键ID(关联)',
    create_time datetime      not null comment '创建时间',
    update_time datetime comment '修改时间'
) comment '用户信息表';

CREATE TABLE user_binging
(
    bind_id     smallint primary key auto_increment comment '用户绑定表主键ID',
    bind_value  varchar(128) not null comment '绑定值',
    bind_type   tinyint      not null comment '绑定类型,0手机 1邮箱 2qq 3微信',
    user_id     int          not null comment '用户主键ID(关联)',
    bind_time   datetime     not null comment '绑定时间',
    update_time datetime comment '修改时间'
) comment '用户绑定表';

CREATE TABLE user_safe
(
    safe_id         bigint primary key auto_increment comment '用户安全表主键ID',
    login_time      datetime     not null comment '用户登录时间',
    login_equipment varchar(128) not null comment '用户登录设备',
    login_ip        varchar(64)  not null comment '用户登录IP',
    login_city      varchar(32)  not null comment '用户登录城市',
    login_country   varchar(32)  not null comment '用户登录国家',
    user_id         int          not null comment '用户主键ID(关联)',
    create_time     datetime     not null comment '创建时间',
    update_time     datetime comment '修改时间'

) comment '用户安全表';


CREATE TABLE article
(
    article_id      bigint primary key auto_increment comment '文章id',
    article_title   varchar(50)  not null comment '文章标题',
    article_desc    varchar(100) not null comment '文章摘要',
    article_content longtext     not null comment '文章正文',
    user_id         int          not null comment '用户ID',
    del_flag        tinyint      not null comment '删除标识',
    state           tinyint      not null comment '文章状态(私密,公开)',
    create_time     datetime     not null comment '创建时间',
    update_time     datetime comment '修改时间'
) comment '文章表';

CREATE TABLE tag
(
    tag_id      bigint primary key auto_increment comment '标签ID',
    tag_name    varchar(12) not null comment '标签名称',
    user_id     int         not null comment '创建用户ID',
    create_time datetime    not null comment '创建时间'
) comment '标签表';

CREATE TABLE article_tag
(
    article_tag_id bigint primary key auto_increment comment '文章与标签关联关系表ID',
    article_id     bigint   not null comment '文章ID',
    tag_id         bigint   not null comment '标签ID',
    create_time    datetime not null comment '创建时间',
    update_time    datetime comment '修改时间'
) comment '文章与标签关联关系表';

