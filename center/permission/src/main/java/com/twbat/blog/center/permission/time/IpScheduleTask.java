package com.twbat.blog.center.permission.time;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.center.permission.domain.ip.IpRecordPo;
import com.twbat.blog.center.permission.repository.ip.IpRecordService;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.config.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author darkltl
 * @version jdk1.8
 * @email darkltl@163.com
 * @date 2021/9/10 21:18
 * @description
 * @since 1.0
 */
@Configuration
@EnableScheduling
public class IpScheduleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpScheduleTask.class);

    /**
     * 用于将IP持久化成功后清理IP
     */
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * ip记录仓库
     * 需要将ip通过该仓库访问数据库进行持久化
     */
    @Resource
    private IpRecordService ipRecordService;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void writeIpToDatabase() {
        LOGGER.info("IP写入数据库任务正在启动...,启动时间: {}", DateUtils.getTime());
        // 获取IP Keys
        Set keys = redisTemplate.keys(Constant.IP_COUNT_REDIS_KEY.getCode() + "*");
        List<IpRecordPo> ips = new ArrayList<>();
        keys.forEach(
                (k) -> {
                    IpRecordPo ipRecordPo = (IpRecordPo) redisTemplate.opsForValue().get(k);
                    if (ipRecordPo != null) {
                        ips.add(ipRecordPo);
                        redisTemplate.delete(k);
                    }
                }
        );

        if (ipRecordService.saveBatch(ips)) {
            LOGGER.info("IP任务批量写入成功,ips:{}", ips);
            return;
        }
        LOGGER.warn("IP任务批量写入失败.插入的ip列表:{}", ips);
    }
}
