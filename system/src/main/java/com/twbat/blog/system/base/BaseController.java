package com.twbat.blog.system.base;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.util.util.config.Constant;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;
import com.twbat.blog.system.business.repository.user.SystemUserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022-02-17 - 10:54
 * @desciption 服务端父级控制器
 */
@RestController
@RequestMapping("/system")
public class BaseController {

    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    /**
     * 系统用户Mapper
     */
    @Resource
    private SystemUserMapper systemUserMapper;

    /**
     * RedisTemplate
     */
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    protected SystemUserPo getCurrentUser() {
        Integer userId = JwtUtil.getUserId();
        String redisKey = Constant.SYSTEM_USER_REDIS_KEY.getCode() + userId;
        SystemUserPo cacheObj = (SystemUserPo) redisTemplate.opsForValue().get(redisKey);
        if (cacheObj == null) {
            cacheObj = systemUserMapper.selectById(userId);
            redisTemplate.opsForValue().set(redisKey, cacheObj);
        }
        return cacheObj;
    }

    /**
     * 清除缓存
     */
    protected void clearCache() {
        String key = Constant.SYSTEM_USER_REDIS_KEY.getCode() + JwtUtil.getUserId();
        LOG.info("delete key : {}", key);
        redisTemplate.delete(key);
    }
}
