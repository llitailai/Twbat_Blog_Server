package com.twbat.blog.system.business.repository.menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twbat.blog.system.business.domain.menu.po.SystemMenuPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author darkltl
 * @date 2021-09-14 10:39
 * 系统菜单Dao
 */
@Mapper
public interface SystemMenuMapper extends BaseMapper<SystemMenuPo> {

}
