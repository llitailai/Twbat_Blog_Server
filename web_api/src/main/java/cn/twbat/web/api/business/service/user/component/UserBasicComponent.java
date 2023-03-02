package cn.twbat.web.api.business.service.user.component;

import cn.twbat.web.api.business.domain.user.po.WebBlogUserPo;
import cn.twbat.web.api.business.repository.user.WebBlogUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.util.util.auth.Token;
import com.twbat.blog.common.util.util.config.Constant;
import com.twbat.blog.common.util.util.encrypt.MD5Util;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 21:08
 * @desciption 用户基础功能组件
 */
@Component
public class UserBasicComponent {

    /**
     * web端用户Mapper
     */
    @Resource
    private WebBlogUserMapper webBlogUserMapper;

    /**
     * 线程安全的用户对象
     */
    private final AtomicReference<WebBlogUserPo> userAtomic = new AtomicReference<>();

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 根据用户账号判断用户是否存在 存在true 不存在false
     *
     * @param account 账号
     * @return boolean true : 存在 false : 不存在
     */
    public boolean userExist(String account) {
        return webBlogUserMapper.selectCount(
                new LambdaQueryWrapper<WebBlogUserPo>().eq(WebBlogUserPo::getAccount, account)
        ) > 0;
    }

    /**
     * 判断用户密码是否正确
     * true : 正确
     * false : 错误
     *
     * @return boolean true: 正确 false: 错误
     */
    public boolean passwordIsSuccess(String account, String password) {
        WebBlogUserPo user = webBlogUserMapper.selectOne(
                new LambdaQueryWrapper<WebBlogUserPo>().select(WebBlogUserPo::getPassword, WebBlogUserPo::getUserId).eq(WebBlogUserPo::getAccount, account)
        );
        userAtomic.set(user);
        return MD5Util.verify(password, user.getPassword());
    }

    /**
     * 获取token 并存储到redis中
     *
     * @return token
     */
    public String getToken() {
        WebBlogUserPo user = userAtomic.get();
        String token = Token.createToken(user.getUserId(), user.getPassword());
        redisTemplate.opsForValue().set(token + user.getUserId(), Token.TOKEN_FLAG);
        return token;
    }

    /**
     * 注销 登出
     */
    public void logOut(String token) {
        String key = token + JwtUtil.getUserId();
        redisTemplate.delete(key);
        String userKey = Constant.WEB_USER_REDIS_KEY.getCode() + JwtUtil.getUserId();
        redisTemplate.delete(userKey);
    }

}
