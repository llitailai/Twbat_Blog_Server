package cn.twbat.web.api.business.repository.user.bind;

import cn.twbat.web.api.business.domain.user.po.UserBingingPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 22:02
 * @desciption
 * 用户绑定信息DAO
 */
@Mapper
public interface UserBindMapper extends BaseMapper<UserBingingPo> {

}
