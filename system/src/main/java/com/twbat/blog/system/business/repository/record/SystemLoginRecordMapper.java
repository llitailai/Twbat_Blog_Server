package com.twbat.blog.system.business.repository.record;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.system.business.domain.user.po.record.SystemLoginRecordPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darkltl
 * @date 2021-09-13 17:01
 * 系统用户登录日志记录DAO
 */
@Mapper
public interface SystemLoginRecordMapper extends BaseMapper<SystemLoginRecordPo> {
}
