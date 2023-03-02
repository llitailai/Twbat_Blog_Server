package cn.twbat.web.api.business.repository.tag;

import cn.twbat.web.api.business.domain.tag.po.TagPo;
import cn.twbat.web.api.business.domain.tag.vo.TagVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/11 - 20:11
 * @desciption
 * 文章标签Mapper
 */
@Mapper
public interface TagMapper extends BaseMapper<TagPo> {


    /**
     * 通过关键字查询标签 (服务于element-ui 远程搜索) 列表
     *
     * @param key 关键字
     * @return 查询出符合条件的标签列表
     */
    @Results(id = "searchTagsResultMap", value = {
            @Result(property = "tagId", column = "tag_id", id = true),
            @Result(property = "value", column = "tag_name"),
            @Result(property = "createUser", column = "nickname")
    })
    @Select("<script>\n" +
            "SELECT \n" +
            "tag.tag_id,\n" +
            "tag.tag_name,\n" +
            "ui.nickname\n" +
            "FROM TAG AS tag\n" +
            "LEFT JOIN user_info AS ui\n" +
            "ON tag.user_id = ui.user_id \n" +
            "<if test = 'key != null '>\n" +
            "tag.tag_name like concat('%',#{key},'%')\n" +
            "</if>\n" +
            "</script>\n")
    List<TagVo> searchTags(@Param("key") String key);
}
