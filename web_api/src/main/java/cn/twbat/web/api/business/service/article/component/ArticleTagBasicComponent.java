package cn.twbat.web.api.business.service.article.component;

import cn.twbat.web.api.business.domain.article.po.ArticleTagPo;
import com.twbat.blog.common.util.util.DateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 14:02
 * @desciption
 * 文章标签关联关系表基础功能组件
 */
@Component
public class ArticleTagBasicComponent {

    /**
     * 构建批量保存文章标签所需的关联关系表(文章与标签关联关系表)实体
     * @param articleId 文章ID
     * @param tagsId 标签ID
     * @return 文章与标签关联关系表PO
     */
    public List<ArticleTagPo> buildBatchSaveArticleTagsEntity(Long articleId, List<Long> tagsId) {
        // 如果文章ID或者tagsId任意参数为空,直接返回空集合
        if (articleId == null || tagsId == null || tagsId.size() == 0) {
            return Collections.emptyList();
        }
        List<ArticleTagPo> res = new ArrayList<>();
        tagsId.forEach(
                tagId -> {
                    ArticleTagPo target = new ArticleTagPo();
                    target.setArticleId(articleId);
                    target.setTagId(tagId);
                    target.setCreateTime(DateUtils.getNowDate());
                    res.add(target);
                }
        );
        return res;
    }
}
