package cn.twbat.web.api.function.controller.tag;

import cn.twbat.web.api.business.domain.tag.dto.SaveTagDto;
import cn.twbat.web.api.business.service.tag.TagService;
import cn.twbat.web.api.global.ApiController;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/12 - 21:10
 * @desciption
 * 标签控制器
 */
@RestController
public class TagController extends ApiController {

    /**
     * 标签服务 提供对标签操作的服务支持
     */
    @Resource
    private TagService tagService;


    /**
     * 根据关键字搜索标签
     * @param key 关键字
     * @return 全局统一返回对象
     */
    @GetMapping("/tag/search")
    @Required
    public ApiResult<?> searchTags(String key) {
        return tagService.searchTags(key);
    }

    /**
     * 批量保存标签集合
     * @param tags 需要保存的标签集合
     * @return 全局统一返回对象
     */
    @Required
    @PostMapping("/tag/batchSave")
    public ApiResult<?> batchSaveTags(@RequestBody List<SaveTagDto> tags) {
        return tagService.batchSaveTags(tags);
    }

    /**
     * 获取所有的标签
     * @return 全局统一返回对象
     */
    @Required
    @GetMapping("/tag/list")
    public ApiResult<?> listTags() {
        return tagService.listTags();
    }
}
