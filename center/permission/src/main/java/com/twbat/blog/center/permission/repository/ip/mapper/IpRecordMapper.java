package com.twbat.blog.center.permission.repository.ip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.center.permission.domain.ip.IpRecordPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darkltl
 * @version jdk1.8
 * @email darkltl@163.com
 * @date 2021/9/13 0:07
 * @description
 * @since 1.0
 */
@Mapper
public interface IpRecordMapper extends BaseMapper<IpRecordPo> {
}
