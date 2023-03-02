package cn.twbat.web.api.business.domain.article.dto;

import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/14 - 13:39
 * @desciption 查询文章列表条件dto
 */

@Data
public class ListArticleConditionDto implements Serializable {


    /**
     * 分页参数 - 页数
     */
    @Valid
    private Integer page;

    /**
     * 分页参数 - 条数
     */
    @Valid
    private Integer size;


}
