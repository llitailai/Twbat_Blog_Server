package cn.twbat.web.api.business.service.user.component;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import cn.twbat.web.api.business.domain.user.dto.RegisterUserDto;
import cn.twbat.web.api.business.domain.user.po.UserBingingPo;
import cn.twbat.web.api.business.domain.user.po.WebBlogUserPo;
import cn.twbat.web.api.business.repository.user.WebBlogUserMapper;
import cn.twbat.web.api.business.repository.user.bind.UserBindMapper;
import cn.twbat.web.api.business.service.user.UserPasswordEncryptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.business.code.CodeBeOverdueException;
import com.twbat.blog.center.permission.global.exception.business.code.ManyTimesApplyCodeException;
import com.twbat.blog.center.permission.global.exception.business.email.EmailIsAlreadyUseException;
import com.twbat.blog.center.permission.global.exception.business.user.UserPasswordNotIdenticalException;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.config.setting.enums.UserBindTypeEnum;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.RandomUtil;
import com.twbat.blog.common.util.util.bean.BeanUtils;
import com.twbat.blog.common.util.util.email.EmailConfig;
import com.twbat.blog.common.util.util.email.SendEmailUtil;
import com.twbat.blog.common.util.util.encrypt.MD5Util;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import java.util.concurrent.TimeUnit;

import static com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum.*;
import static com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum.ERROR;
import static com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum.SEND_EMAIL_EXCEPTION;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 19:59
 * @desciption
 */
@Component
public class UserRegisterComponent {

    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterComponent.class);
    /**
     * 发送邮件工具
     */
    @Resource
    private SendEmailUtil sendEmailUtil;
    /**
     * Redis Template
     */
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 用户绑定信息DAO
     */
    @Resource
    private UserBindMapper userBindMapper;

    /**
     * WEB端博客用户DAO
     */
    @Resource
    private WebBlogUserMapper webBlogUserMapper;




    /**
     * 密码一致性校验
     * 校验传入的密码与重复输入的密码是否一致
     *
     * @param password    密码
     * @param repPassword 重复输入的密码
     * @return boolean true: 一致 false: 不一致
     */
    private boolean passwordIdenticalValid(final String password, final String repPassword) {
        return password.equals(repPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerUser(RegisterUserDto paramDto, UserPasswordEncryptService service) {
        // 通过私钥解密加密的密码
        String password = service.convertPassword(paramDto.getPassword());
        String repPassword = service.convertPassword(paramDto.getRepPassword());

        // 判断用户名是否已被绑定
        if (emailIsBindingOtherUser(paramDto.getAccount())) {
            throw new EmailIsAlreadyUseException(EMAIL_IS_ALREADY_USE_EXCEPTION);
        }

        if (!passwordIdenticalValid(password,repPassword)) {
            throw new UserPasswordNotIdenticalException(USER_PASSWORD_NOT_IDENTICAL_EXCEPTION);
        }
        WebBlogUserPo user = BeanUtils.copyProperties(
                paramDto,
                WebBlogUserPo::new,
                (s, t) -> {
                    t.setPassword(MD5Util.generate(password));
                }
        );
        if (webBlogUserMapper.insert(
                user
        ) == 1) {
            UserBingingPo bingingPo = BeanUtils.copyProperties(
                    paramDto,
                    UserBingingPo::new,
                    (s, t) -> {
                        t.setUserId(user.getUserId());
                        t.setBindTime(DateUtils.getNowDate());
                        t.setBindType(UserBindTypeEnum.EMAIL.getValue());
                        t.setBindValue(s.getEmail());
                    }
            );
            if (userBindMapper.insert(
                    bingingPo
            ) != 1) {
                LOGGER.error("记录用户绑定信息失败,失败时间:{},失败参数:{},参数:{}",DateUtils.getTime(),paramDto,bingingPo);
               throw new SystemException(ERROR);
            }

        } else {
            LOGGER.error("注册用户失败,数据库插入失败,失败时间:{},参数:{},参数:{}",DateUtils.getTime(),paramDto,user);
            throw new SystemException(ERROR);
        }
    }


    public ApiResult<?> sendRegisterEmail(String email) {
        try {
            // 判断邮箱是否已被注册或绑定
            if (emailIsBindingOtherUser(email)) {
                throw new EmailIsAlreadyUseException(EMAIL_IS_ALREADY_USE_EXCEPTION);
            }
            // 验证码
            String code = RandomUtil.generateString(EmailConfig.CODE_LENGTH);
            recordCodeToRedis(email,code);
            sendEmailUtil.sendEmails(email,code);
        } catch (MessagingException e) {
            LOGGER.error("发送注册邮件失败,出现异常,异常时间:{},异常信息:{},参数:{}", DateUtils.getTime(), e, email);
            return ApiResult.error(SEND_EMAIL_EXCEPTION.getMsg());
        }

        return ApiResult.success();
    }

    /**
     * 判断Email是否被其他人绑定
     * @param email 邮箱
     * @return boolean true 已被绑定 false 未绑定
     */
    private boolean emailIsBindingOtherUser(String email) {
        return userBindMapper.selectCount(new LambdaQueryWrapper<UserBingingPo>().eq(UserBingingPo::getBindValue, email)) != 0 || webBlogUserMapper.selectCount(new LambdaQueryWrapper<WebBlogUserPo>().eq(WebBlogUserPo::getAccount, email)) != 0;
    }

    /**
     * 验证 验证码是否正确
     * @param code 传入的验证码
     * @param email 邮箱
     * @return 全局统一返回对象
     */
    public ApiResult<?> verificationCode(String code,String email) {
        String var0 = getCodeByRedis(email);
        return var0.equals(code) ? ApiResult.success() : ApiResult.error();
    }

    /**
     * 获取Redis中的验证码 (根据邮箱)
     * @param email 邮箱
     * @return 验证码
     * @exception CodeBeOverdueException 如果通过邮箱并没有获取到对应验证码,则会抛出该异常
     */
    private synchronized String getCodeByRedis(String email) {
        synchronized (this) {
            Object o = redisTemplate.opsForValue().get(email);
            if (o == null) {
                throw new CodeBeOverdueException(CODE_BE_OVERDUE_EXCEPTION);
            }
            // 验证后就删除
            redisTemplate.delete(email);
            return o.toString();
        }
    }

    /**
     * 记录验证码到Redis中
     * @param email 邮件 redis key
     * @param code 验证码 redis value
     * @exception ManyTimesApplyCodeException 如果redis已存在key值为该邮件的,抛出异常
     */
    private synchronized void recordCodeToRedis(String email, String code) {
        Object res = redisTemplate.opsForValue().get(email);
        if (res == null) {
            synchronized (this) {
                res = redisTemplate.opsForValue().get(email);
                if (res == null) {
                    redisTemplate.opsForValue().set(email, code,5, TimeUnit.MINUTES);
                    return;
                }
            }
        }
        throw new ManyTimesApplyCodeException(MANY_TIMES_APPLY_CODE_EXCEPTION);
    }
}
