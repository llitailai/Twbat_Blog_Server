package com.twbat.blog.center.permission.repository.ip;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twbat.blog.center.permission.domain.ip.IpRecordPo;
import com.twbat.blog.center.permission.repository.ip.mapper.IpRecordMapper;
import org.springframework.stereotype.Service;

/**
 * @author darkltl
 * @version jdk1.8
 * @email darkltl@163.com
 * @date 2021/9/11 15:42
 * @description
 * @since 1.0
 */
@Service
public class IpRecordService extends ServiceImpl<IpRecordMapper, IpRecordPo> {
}
