package cn.twbat.web.api.business.repository.user;

import cn.twbat.web.api.business.domain.user.po.WebBlogUserPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 21:06
 * @desciption web端用户Mapper
 */
@Mapper
public interface WebBlogUserMapper extends BaseMapper<WebBlogUserPo> {
}
