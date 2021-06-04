package com.gs.lshly.biz.support.commodity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.commodity.entity.CtccCategory;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;

/**
 * <p>
 * 电信专题分类 Mapper 接口
 * </p>
 *
 * @author yingjun
 * @since 2021-04-20
 */
public interface CtccCategoryMapper extends BaseMapper<CtccCategory> {

	/**
	 * 查询参于电信国际的商品
	 * @param categoryId
	 * @return
	 */
    @Select("select * from gs_goods_info where id in(select goods_id from gs_ctcc_pt_activity_goods where category_id= #{categoryId} )")
    List<BbcCtccCategoryGoodsVO.GoodsListVO> listGoodsByCategoryId(@Param("categoryId") String categoryId);

}
