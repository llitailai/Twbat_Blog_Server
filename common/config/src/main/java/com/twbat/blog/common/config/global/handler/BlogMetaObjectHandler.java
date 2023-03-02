package com.twbat.blog.common.config.global.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author darkltl
 * @className BlogMetaObjectHandler
 * @email darkltl@163.com
 * @date 2021/8/16 - 13:28
 * @description
 */
@Slf4j
@Component
public class BlogMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert");
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update");
        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
    }
}
