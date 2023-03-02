package cn.twbat.web.api.business.service.user.info.component;

import cn.twbat.web.api.business.domain.user.info.dto.SaveWebBlogUserInfoDto;
import cn.twbat.web.api.business.domain.user.info.po.WebBlogUserInfoPo;
import cn.twbat.web.api.business.domain.user.info.vo.WebBlogUserInfoVo;
import cn.twbat.web.api.business.repository.user.info.WebBlogUserInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.global.exception.business.user.UserInfoIsExistException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;
import com.twbat.blog.common.util.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 15:00
 * @desciption 用户信息基础功能组件
 */
@Component
public class UserInfoBasicComponent {

    /**
     * Web端博客用户信息Mapper
     */
    @Resource
    private WebBlogUserInfoMapper webBlogUserInfoMapper;

    /**
     * 线程安全的用户信息对象
     */
    private final AtomicReference<WebBlogUserInfoPo> userInfoAtomic = new AtomicReference<>();

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息po实体
     */
    public WebBlogUserInfoPo getUserInfoByUserId(Integer userId) {
        WebBlogUserInfoPo userInfo = webBlogUserInfoMapper.selectOne(
                new LambdaQueryWrapper<WebBlogUserInfoPo>().eq(WebBlogUserInfoPo::getUserId, userId)
        );
        userInfoAtomic.set(userInfo);
        return userInfo;
    }


    /**
     * 获取用户信息VO对象
     *
     * @return 用户信息VO对象
     */
    public WebBlogUserInfoVo getUserInfoVo() {
        WebBlogUserInfoPo userInfo = userInfoAtomic.get();
        WebBlogUserInfoVo res = new WebBlogUserInfoVo();
        BeanUtils.copyProperties(userInfo, res);
        return res;
    }

    /**
     * 保存用户信息
     *
     * @param dto    用户信息dto
     * @param userId 用户ID
     * @return 回显用户信息vo
     * @throws UserInfoIsExistException 用户信息已存在异常 (防止用户多次请求造成脏数据)
     */
    public WebBlogUserInfoVo saveUserInfo(SaveWebBlogUserInfoDto dto, Integer userId) {
        if (webBlogUserInfoMapper.selectCount(new LambdaQueryWrapper<WebBlogUserInfoPo>().eq(WebBlogUserInfoPo::getUserId, userId)) > 0) {
            throw new UserInfoIsExistException(BusinessExceptionEnum.USER_INFO_IS_EXIST_NULL);
        }
        WebBlogUserInfoPo po = new WebBlogUserInfoPo();
        BeanUtils.copyProperties(dto, po);
        po.setUserId(userId);
        po.setCreateTime(DateUtils.getNowDate());

        if (webBlogUserInfoMapper.insert(po) > 0) {
            WebBlogUserInfoVo res = new WebBlogUserInfoVo();
            BeanUtils.copyProperties(po, res);
            return res;
        }
        return WebBlogUserInfoVo.getEmptyVo();
    }
}
