package cn.twbat.web.api.business.repository.article;

import cn.twbat.web.api.business.domain.article.po.ArticleTagPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 13:57
 * @desciption
 * 文章标签关联关系表Mapper
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTagPo> {
}
