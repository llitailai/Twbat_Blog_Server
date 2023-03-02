package cn.twbat.web.api.business.domain.article.vo;

import cn.twbat.web.api.business.domain.tag.vo.SaveTagVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 21:06
 * @desciption
 * 保存文章回显VO
 */
@Data
public class SaveArticleVo implements Serializable {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 文章标签
     */
    private List<SaveTagVo> tags;
}
