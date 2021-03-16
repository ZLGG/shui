package com.gs.lshly.biz.support.foundation.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.foundation.entity.MerchantArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 商家文章 Mapper 接口
 * </p>
 *
 * @author hyy
 * @since 2020-10-29
 */
public interface MerchantArticleMapper extends BaseMapper<MerchantArticle> {


    @Select(  "select a.id, a.title , b.parent_id ,a.merchant_id, a.cdate, a.udate from gs_merchant_article a join gs_merchant_article_category b on a.category_id=b.id")
    IPage<MerchantArticleVO.ListVO> queryAll(Page<?> page );

}
