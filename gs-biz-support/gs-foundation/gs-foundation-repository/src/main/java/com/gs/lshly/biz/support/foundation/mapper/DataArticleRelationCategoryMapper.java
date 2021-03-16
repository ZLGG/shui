package com.gs.lshly.biz.support.foundation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.DataArticleRelationCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.ArticleCategoryView;
import com.gs.lshly.biz.support.foundation.mapper.view.RelationCategoryView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 文章栏目-文章 关联表 Mapper 接口
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-19
 */
@Repository
public interface DataArticleRelationCategoryMapper extends BaseMapper<DataArticleRelationCategory> {

    @Select("SELECT ctg.*,rel.article_id " +
            "FROM gs_data_article_category ctg " +
            "LEFT JOIN `gs_data_article_relation_category` rel ON ctg.`id` = rel.`category_id`" +
            "WHERE ctg.flag = 0 and ${ew.sqlSegment}")
    List<RelationCategoryView> mapperListCategoryWithArticleId(@Param(value = "ew") QueryWrapper<RelationCategoryView> we);

}
