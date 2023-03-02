package cn.twbat.web.api.business.service.tag;

import cn.twbat.web.api.business.domain.tag.dto.SaveTagDto;
import cn.twbat.web.api.business.domain.tag.po.TagPo;
import cn.twbat.web.api.business.repository.tag.TagMapper;
import cn.twbat.web.api.business.service.tag.component.TagBasicComponent;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twbat.blog.common.util.util.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/11 - 20:12
 * @desciption 标签服务
 */
@Service
public class TagService extends ServiceImpl<TagMapper, TagPo> {

    @Resource
    private TagBasicComponent tagBasicComponent;

    /**
     * 批量保存标签集合
     *
     * @param tags 标签集合
     * @return 全局统一返回对象
     */
    public ApiResult<?> batchSaveTags(List<SaveTagDto> tags) {
        List<Long> tagIds = tagBasicComponent.batchSaveTag(tags, this);
        return ApiResult.success(tagIds);
    }

    /**
     * 根据关键字搜索标签
     *
     * @param key 关键字
     * @return 全局统一返回对象
     */
    public ApiResult<?> searchTags(String key) {
        return ApiResult.success(tagBasicComponent.searchTags(key));
    }

    /**
     * 获取所有的标签
     *
     * @return 全局统一返回对象
     */
    public ApiResult<?> listTags() {
        return ApiResult.success(tagBasicComponent.listTags());
    }
}
