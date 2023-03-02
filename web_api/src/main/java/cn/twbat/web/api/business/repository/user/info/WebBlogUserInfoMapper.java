package cn.twbat.web.api.business.repository.user.info;

import cn.twbat.web.api.business.domain.user.info.po.WebBlogUserInfoPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 14:59
 * @desciption
 * Web端博客用户信息Mapper
 */
@Mapper
public interface WebBlogUserInfoMapper extends BaseMapper<WebBlogUserInfoPo> {
}
