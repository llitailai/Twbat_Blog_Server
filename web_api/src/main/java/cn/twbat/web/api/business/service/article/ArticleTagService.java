package cn.twbat.web.api.business.service.article;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import cn.twbat.web.api.business.domain.article.po.ArticleTagPo;
import cn.twbat.web.api.business.repository.article.ArticleTagMapper;
import cn.twbat.web.api.business.service.article.component.ArticleTagBasicComponent;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;
import com.twbat.blog.common.util.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 13:58
 * @desciption
 * 文章与标签关联关系服务
 */
@Service
public class ArticleTagService extends ServiceImpl<ArticleTagMapper, ArticleTagPo> {

    /**
     * 文章标签关联关系表基础功能组件
     */
    @Resource
    private ArticleTagBasicComponent articleTagBasicComponent;

    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(ArticleTagService.class);

    /**
     * 保存文章与标签关联关系
     * @param articleId 文章ID
     * @param tagsId 标签ID集合
     * @exception SystemException 出现未知系统异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchSaveArticleTags(Long articleId, List<Long> tagsId) {
        List<ArticleTagPo> res = articleTagBasicComponent.buildBatchSaveArticleTagsEntity(articleId, tagsId);
        if (res != null && res.size() != 0) {
            // 如果批量保存失败,抛出异常进行回滚事务
            if (!this.saveBatch(res)) {
                LOG.error("保存文章与标签关联关系出现异常,异常时间:{},文章ID:{},标签ID:{},构建关联关系对象结果:{}",
                        DateUtils.getTime(),articleId,tagsId,res);
                throw new SystemException(SystemExceptionEnum.ERROR);
            }
        }
    }

}
