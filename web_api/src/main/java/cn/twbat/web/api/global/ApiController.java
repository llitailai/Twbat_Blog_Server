package cn.twbat.web.api.global;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import cn.twbat.web.api.business.domain.user.po.WebBlogUserPo;
import cn.twbat.web.api.business.repository.user.WebBlogUserMapper;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.config.Constant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/1/7 - 10:56
 * @desciption Api父级控制器
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    /**
     * Web端博客用户DAO
     */
    @Resource
    private WebBlogUserMapper webBlogUserMapper;

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
    protected WebBlogUserPo getCurrentUser() {
        Integer userId = JwtUtil.getUserId();
        String redisKey = Constant.WEB_USER_REDIS_KEY.getCode() + userId;
        WebBlogUserPo cacheObj = (WebBlogUserPo) redisTemplate.opsForValue().get(redisKey);
        if (cacheObj == null) {
            cacheObj = webBlogUserMapper.selectById(userId);
            redisTemplate.opsForValue().set(redisKey, cacheObj);
        }
        return cacheObj;
    }

    /**
     * 清除缓存
     */
    protected void clearCache() {
        String key = Constant.WEB_USER_REDIS_KEY.getCode() + JwtUtil.getUserId();
        LOG.info("delete key : {}", key);
        redisTemplate.delete(key);
    }


    public ApiResult<?> permission(String permissionCode) {
        return ApiResult.success();
    }
}
