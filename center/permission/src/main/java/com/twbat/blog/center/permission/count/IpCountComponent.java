package com.twbat.blog.center.permission.count;

import com.twbat.blog.center.permission.domain.ip.IpRecordPo;
import com.twbat.blog.common.util.util.config.Constant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author darkltl
 * @version jdk1.8
 * @email darkltl@163.com
 * @date 2021/9/10 21:03
 * @description
 * @since 1.0
 * IP统计组件
 */
@Component
public class IpCountComponent {


    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 记录IP
     * 将IP记录到内存中,每日凌晨通过定时任务写入到数据库中
     *
     * @param ip ip地址
     */
    public synchronized void recordIp(final IpRecordPo ip) {
        final String key = Constant.IP_COUNT_REDIS_KEY.getCode() + ip.getIp();
        IpRecordPo val = (IpRecordPo) redisTemplate.opsForValue().get(key);
        if (val == null) {
            redisTemplate.opsForValue().set(key, ip);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        }
    }

    /**
     * 获取今日IP总个数
     *
     * @return IP个数
     */
    public synchronized int getCount() {
        Set keys = redisTemplate.keys(Constant.IP_COUNT_REDIS_KEY.getCode() + "*");
        return keys.size();
    }
}
