package cn.twbat.web.api.business.repository.article;

import cn.twbat.web.api.business.domain.article.dto.ListArticleConditionDto;
import cn.twbat.web.api.business.domain.article.po.ArticlePo;
import cn.twbat.web.api.business.domain.article.vo.ArticleInfoVo;
import cn.twbat.web.api.business.domain.article.vo.ArticleVo;
import cn.twbat.web.api.business.domain.tag.vo.TagVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.xmltags.XMLScriptBuilder;

import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 14:12
 * @desciption
 * 文章Mapper
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticlePo> {




    /**
     * @desciption 根据条件查询文章列表
     *
     *               this.nodeHandlerMap.put("trim", new XMLScriptBuilder.TrimHandler());
     *               this.nodeHandlerMap.put("where", new XMLScriptBuilder.WhereHandler());
     *               this.nodeHandlerMap.put("set", new XMLScriptBuilder.SetHandler());
     *               this.nodeHandlerMap.put("foreach", new XMLScriptBuilder.ForEachHandler());
     *               this.nodeHandlerMap.put("if", new XMLScriptBuilder.IfHandler());
     *               this.nodeHandlerMap.put("choose", new XMLScriptBuilder.ChooseHandler());
     *               this.nodeHandlerMap.put("when", new XMLScriptBuilder.IfHandler());
     *               this.nodeHandlerMap.put("otherwise", new XMLScriptBuilder.OtherwiseHandler());
     *               this.nodeHandlerMap.put("bind", new XMLScriptBuilder.BindHandler());
     *      Mybatis XMLScriptBuilder nodeHandlerMap
     *      注意 Mybatis中对于Script标签中所支持的动态标签 都在nodeHandlerMap中 且注意 不要将这些支持的标签以大写形式书写
     * @see XMLScriptBuilder#initNodeHandlerMap()
     *      在Mybatis parseDynamicTags函数中有一句 XMLScriptBuilder.NodeHandler handler = (XMLScriptBuilder.NodeHandler)this.nodeHandlerMap.get(nodeName);
     *      也就时获取动态标签是根据你书写的标签名称根据key去nodeHandlerMap中寻找是否是SCRIPT中所支持的动态标签 ,所以一定不要大写
     *      否则会抛出异常  throw new BuilderException("Unknown element <" + nodeName + "> in SQL statement.");
     * @see org.apache.ibatis.scripting.xmltags.XMLScriptBuilder#parseDynamicTags(XNode)
     * @param conditionDto 条件传输对象,后续如果条件更新则会需要该参数来进行条件赋值
     * @return 返回的是文章VO视图的集合列表
     */
    @Results(id = "listArticle", value = {
            @Result(property = "articleId", column = "articleId", id = true),
            @Result(property = "articleId", column = "articleId"),
            @Result(property = "articleTitle", column = "articleTitle"),
            @Result(property = "articleDesc", column = "articleDesc"),
            @Result(property = "author", column = "author"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "tags", column = "articleId", many = @Many(select = "listTagsByArticleId", fetchType = FetchType.LAZY, resultMap = "listTagsByArticleIdResult"))
    }
    )
    @Select("<script> SELECT\n" +
            "art.article_id AS articleId,\n" +
            "art.article_title AS articleTitle,\n" +
            "art.article_desc AS articleDesc,\n" +
            "ui.nickname AS author,\n" +
            "art.create_time AS createTime\n" +
            "FROM\n" +
            "article AS art\n" +
            "LEFT JOIN user_info AS ui ON art.user_id = ui.user_id\n" +
            "</script>")
    @SuppressWarnings("all")
    List<ArticleVo> listArticle(@Param("dto") ListArticleConditionDto conditionDto);


    /**
     * 根据文章ID获取文章信息
     * @param articleId 文章ID
     * @return 文章信息VO
     */
    @Results(id = "getArticleInfoById", value = {
            @Result(property = "articleId", column = "articleId", id = true),
            @Result(property = "articleId", column = "articleId"),
            @Result(property = "articleTitle", column = "articleTitle"),
            @Result(property = "articleContent", column = "articleContent"),
            @Result(property = "articleDesc", column = "articleDesc"),
            @Result(property = "state", column = "state"),
            @Result(property = "authorId", column = "authorId"),
            @Result(property = "author", column = "author"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "tags", column = "articleId", many = @Many(select = "listTagsByArticleId", fetchType = FetchType.LAZY, resultMap = "listTagsByArticleIdResult"))
    }
    )
    @Select("<script> SELECT\n" +
            "art.article_id AS articleId,\n" +
            "art.article_title AS articleTitle,\n" +
            "art.article_content AS articleContent,\n" +
            "art.article_desc AS articleDesc,\n" +
            "art.state AS state,\n" +
            "ui.user_id AS authorId,\n" +
            "ui.nickname AS author,\n" +
            "art.create_time AS createTime\n" +
            "FROM\n" +
            "article AS art\n" +
            "LEFT JOIN user_info AS ui ON art.user_id = ui.user_id\n" +
            "where \n" +
            " art.article_id = #{articleId}\n" +
            "</script>")
    ArticleInfoVo getArticleInfoById(Long articleId);


    /**
     * @desciption 根据文章ID获取文章所有标签
     *
     * @param articleId 文章ID
     * @return 标签VO列表
     */
    @Results(id = "listTagsByArticleIdResult", value = {
            @Result(property = "tagId", column = "tag_id", id = true),
            @Result(property = "value", column = "tag_name"),
            @Result(property = "createUser", column = "nickname")
    })
    @Select(
            "SELECT \n" +
                    "tag.tag_id,\n" +
                    "tag.tag_name,\n" +
                    "ui.nickname\n" +
                    "FROM article_tag AS a_tag\n" +
                    "LEFT JOIN tag AS tag \n" +
                    "ON a_tag.tag_id = tag.tag_id \n" +
                    "LEFT JOIN user_info AS ui\n" +
                    "ON tag.user_id = ui.user_id \n" +
                    "WHERE a_tag.article_id = #{articleId} \n"
    )
    @SuppressWarnings("all")
    List<TagVo> listTagsByArticleId(@Param("articleId") Long articleId);
}
