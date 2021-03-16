package com.gs.lshly.biz.support.foundation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.foundation.entity.DataArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.ArticleCategoryView;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 平台文章 Mapper 接口
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-19
 */
@Repository
public interface DataArticleMapper extends BaseMapper<DataArticle> {

    @Select("select a.id,a.title,a.content,a.logo,a.read_count,a.pc_show,a.idx,a.send_time,b.category_id,c.name from gs_data_article_relation_category  as b left join gs_data_article as a on a.id=b.article_id left join gs_data_article_category as c on b.category_id=c.id  where a.terminal=#{ter} and a.flag=0 and a.title like CONCAT('%',#{queryName},'%')")
    IPage<DataArticleVO.ListVO> queryAll(Page<?> page,String queryName, @Param("ter") Integer ter);

    @Select("SELECT art.*,rela.category_id FROM gs_data_article art " +
            "LEFT JOIN `gs_data_article_relation_category` rela ON art.`id` = rela.`article_id` " +
            "WHERE art.flag = 0 and ${ew.sqlSegment}")
    List<ArticleCategoryView> mapperListWithCategory(@Param(value = "ew") QueryWrapper<ArticleCategoryView> we);

    @Select("SELECT art.*,rela.category_id FROM gs_data_article art " +
            "LEFT JOIN `gs_data_article_relation_category` rela ON art.`id` = rela.`article_id`" +
            "WHERE art.flag = 0 and ${ew.sqlSegment}")
    IPage<ArticleCategoryView> mapperPageWithCategory(IPage<ArticleCategoryView> page,@Param(value = "ew") QueryWrapper<ArticleCategoryView> we);
}
