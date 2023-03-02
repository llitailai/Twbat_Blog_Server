package com.twbat.blog.system.business.repository.settings;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.system.business.domain.settings.po.SystemSettingsPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/1/11 - 11:22
 * @desciption 系统设置DAO
 */
@Mapper
public interface SystemSettingsMapper extends BaseMapper<SystemSettingsPo> {
}
