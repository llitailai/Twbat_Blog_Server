package cn.twbat.web.api.function.controller.article;

import cn.twbat.web.api.business.domain.article.dto.ListArticleConditionDto;
import cn.twbat.web.api.business.domain.article.dto.SaveArticleDto;
import cn.twbat.web.api.business.domain.article.dto.UpdateArticleInfoDto;
import cn.twbat.web.api.business.service.article.ArticleService;
import cn.twbat.web.api.global.ApiController;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import com.twbat.blog.common.web.valid.annotations.RequiredValidParam;
import com.twbat.blog.common.web.valid.annotations.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 21:21
 * @desciption 博客文章控制器
 */
@RestController
public class ArticleController extends ApiController {

    /**
     * 文章服务 提供对于文章操作的服务支持
     */
    @Resource
    private ArticleService articleService;

    /**
     * 保存文章
     * @param dto 保存文章参数dto
     * @return 全局统一返回对象
     *
     * @annotation @Required 携带该注解拦截器就会校验请求是否携带token,以及token是否正常
     * @annotation @RequiredValidParam 携带该注解就会校验参数是否符合@Valid注解上携带的规则
     * @annotation @Valid 校验注解,携带该注解但不设置其他参数,默认校验规则为不允许为空
     */
    @PostMapping("/article/save")
    @Required
    @RequiredValidParam
    public ApiResult<?> saveArticle(@Valid SaveArticleDto dto) {
        return articleService.saveArticle(dto);
    }

    /**
     * 获取文章列表(携带分页)
     * @param condition 查询条件dto
     * @return 全局统一返回对象
     */
    @GetMapping("/article/list")
    @RequiredValidParam
    @Required
    public ApiResult<?> listArticleByPage(@Valid ListArticleConditionDto condition) {
        return articleService.listArticleByPage(condition);
    }

    /**
     * 根据文章ID获取文章信息
     * @param articleId 文章ID
     * @return 全局统一返回对象
     */
    @GetMapping("/article/getArticleInfo")
    @Required
    public ApiResult<?> getArticleInfoById(Long articleId) {
        return articleService.getArticleInfoById(articleId);
    }

    /**
     * 修改文章信息
     * @param updateDto 修改信息dto
     * @return 全局统一返回对象
     */
    @PostMapping("/article/update")
    @RequiredValidParam
    @Required
    public ApiResult<?> updateArticleInfo(@Valid UpdateArticleInfoDto updateDto) {
        return articleService.updateArticleInfo(updateDto);
    }
}
