package cn.twbat.web.api.business.service.tag.component;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import cn.twbat.web.api.business.domain.tag.dto.SaveTagDto;
import cn.twbat.web.api.business.domain.tag.po.TagPo;
import cn.twbat.web.api.business.domain.tag.vo.TagVo;
import cn.twbat.web.api.business.repository.tag.TagMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.config.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/11 - 20:14
 * @desciption 标签基础功能组件
 */
@Component
public class TagBasicComponent {

    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(TagBasicComponent.class);

    /**
     * 文章标签Mapper
     */
    @Resource
    private TagMapper tagMapper;

    /**
     * RedisTemplate
     */
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 批量保存标签
     * @param tags 标签
     * @param iService 服务
     * @return 标签ID集合
     * @exception SystemException 当批量保存失败时发生该异常
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchSaveTag(List<SaveTagDto> tags, IService<TagPo> iService) {
        List<TagPo> tagPoList = new ArrayList<>();
        // todo 后续重写BeanUtils完成集合的copy对象
        tags.forEach(
                tag -> {
                    TagPo target = new TagPo();
                    if (!tagNameIsRepeat(tag.getTagName())) {
                        BeanUtils.copyProperties(tag, target);
                        target.setUserId(JwtUtil.getUserId());
                        tagPoList.add(target);
                    }
                }
        );
        if (!iService.saveBatch(tagPoList)) {
            LOG.error("批量保存标签出现异常,异常时间:{},参数保存标签:{},IService:{},po结果集:{}",
                    DateUtils.getTime(),tags,iService,tagPoList);
            throw new SystemException(SystemExceptionEnum.ERROR);
        } else {
            redisTemplate.delete(Constant.TAGS_REDIS_KEY);
        }

        return tagPoList.stream().map(TagPo::getTagId).collect(Collectors.toList());

    }

    /**
     * 标签名称是否重复
     * @param tagName 标签名称
     * @return boolean true: 重复 false: 不重复
     */
    private synchronized boolean tagNameIsRepeat(String tagName) {
        synchronized (this) {
            Object o = redisTemplate.opsForValue().get(Constant.TAG_NAME_REDIS_KEY.getCode() + tagName);
            if (o == null) {
                if (tagMapper.selectCount(new LambdaQueryWrapper<TagPo>().eq(TagPo::getTagName, tagName)) == 0) {
                    return false;
                }
                redisTemplate.opsForValue().set(Constant.TAG_NAME_REDIS_KEY.getCode() + tagName, tagName, 12, TimeUnit.HOURS);
            }
            return true;

        }
    }

    /**
     * 通过关键字查询标签 (服务于element-ui 远程搜索) 列表
     * @param key 关键字
     * @return 标签VO集合
     */
    public List<TagVo> searchTags(String key) {
        return tagMapper.searchTags(key);
    }

    /**
     * 获取标签列表 (远程搜索服务)
     * @return 标签VO集合
     */
    @SuppressWarnings("all")
    public List<TagVo> listTags() {
        Object obj = redisTemplate.opsForValue().get(Constant.TAGS_REDIS_KEY);
        if (obj != null) {
            return ((List<TagVo>) obj);
        }
        List<TagVo> result = tagMapper.searchTags(null);
        redisTemplate.opsForValue().set(Constant.TAGS_REDIS_KEY, result, 1, TimeUnit.HOURS);
        return result;
    }
}
