package cn.twbat.web.api.business.service.article;

import cn.twbat.web.api.business.domain.article.dto.ListArticleConditionDto;
import cn.twbat.web.api.business.domain.article.dto.SaveArticleDto;
import cn.twbat.web.api.business.domain.article.dto.UpdateArticleInfoDto;
import cn.twbat.web.api.business.service.article.component.ArticleBasicComponent;
import com.twbat.blog.center.permission.global.exception.business.blog.BlogNotFoundException;
import com.twbat.blog.common.util.util.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 14:12
 * @desciption 文章服务
 */
@Service
public class ArticleService {

    /**
     * 文章基础功能组件 用于提供对于文章操作的基础功能支持
     */
    @Resource
    private ArticleBasicComponent articleBasicComponent;

    /**
     * 保存文章
     *
     * @param saveArticleDto 文章dto
     * @return 全局统一返回对象
     */
    public ApiResult<?> saveArticle(SaveArticleDto saveArticleDto) {
        return ApiResult.success(articleBasicComponent.saveArticle(saveArticleDto));
    }

    /**
     * 根据条件及分页获取文章列表
     * @param condition 条件
     * @return 分页对象
     */
    public ApiResult<?> listArticleByPage(ListArticleConditionDto condition) {
        return ApiResult.success(articleBasicComponent.listArticleByPage(condition));
    }

    /**
     * 根据文章ID获取文章信息
     * @param articleId 文章ID
     * @return 文章信息VO
     * @exception BlogNotFoundException 如果文章信息不存在则会抛出该异常
     */
    public ApiResult<?> getArticleInfoById(Long articleId) {
        return ApiResult.success(articleBasicComponent.getArticleInfoById(articleId));
    }

    /**
     * 修改文章信息
     * @param updateDto 需要修改的文章信息DTO
     * @return rows 影响行数
     */
    public ApiResult<?> updateArticleInfo(UpdateArticleInfoDto updateDto) {
        return ApiResult.success(articleBasicComponent.updateArticleInfo(updateDto));
    }
}
