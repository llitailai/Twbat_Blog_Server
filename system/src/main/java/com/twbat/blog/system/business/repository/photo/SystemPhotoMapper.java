package com.twbat.blog.system.business.repository.photo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.system.business.domain.photo.po.SystemPhotoPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 17:52
 * @desciption
 * 系统照片数据库访问层,用于提供对于写入,写出持久化保存提供支持
 */
@Mapper
public interface SystemPhotoMapper extends BaseMapper<SystemPhotoPo> {

}
