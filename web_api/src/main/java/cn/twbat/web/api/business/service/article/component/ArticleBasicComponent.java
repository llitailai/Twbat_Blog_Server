package cn.twbat.web.api.business.service.article.component;

import cn.twbat.web.api.business.domain.article.dto.ListArticleConditionDto;
import cn.twbat.web.api.business.domain.article.dto.SaveArticleDto;
import cn.twbat.web.api.business.domain.article.dto.UpdateArticleInfoDto;
import cn.twbat.web.api.business.domain.article.po.ArticlePo;
import cn.twbat.web.api.business.domain.article.vo.ArticleInfoVo;
import cn.twbat.web.api.business.domain.article.vo.ArticleVo;
import cn.twbat.web.api.business.domain.article.vo.SaveArticleVo;
import cn.twbat.web.api.business.domain.tag.dto.SaveTagDto;
import cn.twbat.web.api.business.domain.tag.po.TagPo;
import cn.twbat.web.api.business.domain.tag.vo.SaveTagVo;
import cn.twbat.web.api.business.repository.article.ArticleMapper;
import cn.twbat.web.api.business.service.article.ArticleTagService;
import cn.twbat.web.api.business.service.tag.TagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.business.blog.BlogNotFoundException;
import com.twbat.blog.center.permission.global.exception.business.blog.TagExceedAssignNumberException;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.web.valid.util.StringUtils;
import com.twbat.blog.common.web.vo.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum.ARTICLE_TAG_EXCEED_ASSIGN_NUMBER_EXCEPTION;
import static com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum.BLOG_NOT_FOUND_EXCEPTION;
import static com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum.ERROR;
import static com.twbat.blog.common.config.setting.constant.TableConditionFieldConfig.ARTICLE_CREATE_TAG_NUMBER;
import static com.twbat.blog.common.config.setting.constant.TableConditionFieldConfig.NOT_DEL_FLAG;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 14:13
 * @desciption
 * 文章基础功能组件
 */
@Component
public class ArticleBasicComponent {

    /**
     * 标签服务,用于提供对于标签增删改查的服务支持
     */
    @Resource
    private TagService tagService;

    /**
     * 文章标签关联关系表服务,用于提供对于文章与标签的关系映射进行操作的服务支持
     */
    @Resource
    private ArticleTagService articleTagService;

    /**
     * 文章DAO 对于文章访问数据库(增删改查)提供支持
     */
    @Resource
    private ArticleMapper articleMapper;


    /**
     * 保存文章功能
     * @param saveArticleDto 保存文章数据传输对象
     * @return 回显
     * @exception  TagExceedAssignNumberException 当标签数量超出限制时,会发生该异常
     */
    @Transactional(rollbackFor = Exception.class)
    public SaveArticleVo saveArticle(SaveArticleDto saveArticleDto) {
        List<Long> existTagIds = saveArticleDto.getExistTagIds();
        List<String> createTags = saveArticleDto.getCreateTags();
        // 校验标签总数量是否超出限制
        if (this.validTagNumber(existTagIds, createTags)) {
            throw new TagExceedAssignNumberException(ARTICLE_TAG_EXCEED_ASSIGN_NUMBER_EXCEPTION);
        }
        // 如果有自己创建的标签,先将标签保存至数据库,并将其返回的ID添加到已存在标签ID集合中
        if (!StringUtils.collectionIsEmpty(createTags)) {
            if (existTagIds == null) {
                existTagIds = new ArrayList<>();
            }
            existTagIds.addAll(this.saveTags(createTags, saveArticleDto.getCreateTime()));
        }
        // 创建文章
        ArticlePo articlePo = new ArticlePo();
        BeanUtils.copyProperties(saveArticleDto, articlePo);
        articlePo.setDelFlag(NOT_DEL_FLAG);
        articlePo.setUserId(JwtUtil.getUserId());
        if (articleMapper.insert(articlePo) == 0) {
            throw new SystemException(ERROR);
        }
        // 批量保存文章标签
        articleTagService.batchSaveArticleTags(articlePo.getArticleId(), existTagIds);
        // 查询已插入的标签信息
        SaveArticleVo res = new SaveArticleVo();
        res.setArticleId(articlePo.getArticleId());
        res.setTags(this.getSaveTagVos(tagService.listByIds(existTagIds)));
        return res;
    }

    /**
     * 将标签po列表转换为回显标签vo列表(保存标签VO)
     * @param tagPos 标签po列表
     * @return 回显标签vo列表(保存标签VO)
     */
    private List<SaveTagVo> getSaveTagVos(List<TagPo> tagPos) {
        return tagPos.stream().map(
                item -> {
                    SaveTagVo res = new SaveTagVo();
                    res.setTagId(item.getTagId());
                    res.setValue(item.getTagName());
                    res.setMeCreate(JwtUtil.getUserId().intValue() == item.getUserId().intValue());
                    return res;
                }
        ).collect(Collectors.toList());
    }

    /**
     * 保存标签并返回标签ID
     * @param tagNames 标签名称
     * @param createTime 创建时间
     * @return 标签ID集合
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("all")
    public List<Long> saveTags(List<String> tagNames, Date createTime) {
        List<SaveTagDto> res = tagNames.stream().map(name -> {
            SaveTagDto saveTagDto = new SaveTagDto();
            saveTagDto.setTagName(name);
            saveTagDto.setCreateTime(createTime);
            return saveTagDto;
        }).collect(Collectors.toList());
        return ((List<Long>) tagService.batchSaveTags(res).getData());
    }

    /**
     * 校验标签是否超出数量
     * existTagIds.size() + createTags.size() > 5
     * @param existTagIds 已存在的标签 (用户选择的标签,需要与文章进行关联)
     * @param createTags 需要创建的标签 (用户选择的标签,需要与文章进行关联)
     * @return boolean true : 超出 false : 未超出
     */
    private boolean validTagNumber(List<Long> existTagIds, List<String> createTags) {
        return (existTagIds == null ? 0 : existTagIds.size()) + (createTags == null ? 0 : createTags.size()) > ARTICLE_CREATE_TAG_NUMBER;
    }

    /**
     * 根据条件及分页获取文章列表
     * @param condition 条件
     * @return 分页对象
     */
    public PageVo<ArticleVo> listArticleByPage(ListArticleConditionDto condition) {
        // 进行分页
        Page<ArticleVo> articleVos = PageHelper.startPage(condition.getPage(), condition.getSize()).doSelectPage(
                () -> articleMapper.listArticle(condition)
        );
        // 如果分页后的数据为空,直接返回空对象
        if (articleVos == null) {
            return new PageVo<>();
        }

        // 设置分页vo属性
        PageVo<ArticleVo> pageVo = new PageVo<>();
        pageVo.setAttribute(articleVos);
        return pageVo;
    }

    /**
     * todo: 二期工程进行研发,目前文章并未设置筛选/搜索条件
     * @param conditionDto 条件dto
     * @return 查询Wrapper
     */
    @SuppressWarnings("all")
    private LambdaQueryWrapper<ArticlePo> getListArticleConditionWrapper(ListArticleConditionDto conditionDto) {
        return null;
    }

    /**
     * 根据文章ID获取文章信息
     * @param articleId 文章ID
     * @return 文章信息VO
     * @exception BlogNotFoundException 如果文章信息不存在则会抛出该异常
     */
    public ArticleInfoVo getArticleInfoById(Long articleId) {
        ArticleInfoVo article = articleMapper.getArticleInfoById(articleId);
        if (article == null ) {
            throw new BlogNotFoundException(BLOG_NOT_FOUND_EXCEPTION);
        }
        // 如果是本人,显示编辑按钮,否则不显示
        article.setEditIsShow(article.getAuthorId().intValue() == JwtUtil.getUserId().intValue());
        return article;
    }

    /**
     * 修改文章信息
     * @param updateDto 需要修改的文章信息DTO
     * @return rows 影响行数
     */
    public int updateArticleInfo(UpdateArticleInfoDto updateDto) {
        ArticlePo updateArticlePo = getUpdateArticlePo(updateDto);
        return articleMapper.updateById(updateArticlePo);
    }

    /**
     * 将修改文章信息的dto转换为po对象
     * @param updateDto 修改文章信息dto
     * @return 文章po
     */
    private ArticlePo getUpdateArticlePo(UpdateArticleInfoDto updateDto) {
        ArticlePo target = new ArticlePo();
        BeanUtils.copyProperties(updateDto,target);
        return target;
    }
}
