package com.gs.lshly.biz.support.commodity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfoTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyang
 * @since 2021-06-09
 */
public interface GoodsInfoTempMapper extends BaseMapper<GoodsInfoTemp> {

    /**
     * 查询商家商品信息
     * @param page
     * @param qw
     * @return
     */
    @Select(
            "SELECT DISTINCT gs.id,gs.merchant_id,gs.shop_id,gs.brand_id,gs.spec_info_id,gs.attribute_info_id," +
                    "gs.extend_params_id,gs.goods_name,gs.goods_title,gs.goods_state,gs.goods_no,gs.sale_price,gs.old_price," +
                    "gs.cost_price,gs.goods_weight,gs.goods_valid_days,gs.goods_image,gs.is_single,gs.point_price,gs.remarks,gs.is_point_good,gs.is_in_member_gift,gs.in_member_point_price,gs.sale_type,gs.third_product_id,gs.exchange_type,gs.ctcc_mold,\n" +
                    "gs.is_show_old_price,gs.goods_price_unit,gs.use_platform,gs.publish_time,gs.cdate as commit_time,gs.udate,gs.apply_type,ar.state as aduit_result, ar.cdate as aduit_time,ar.refuse_remark  from  gs_goods_info_temp gs \n" +
                    "left join gs_goods_audit_record ar on gs.id = ar.goods_id "+
                    "WHERE gs.flag =0 AND ${ew.sqlSegment} "
    )
    IPage<PCMerchGoodsInfoVO.SpuListVO> getMerchantGoodsInfoTemp(IPage<PCMerchGoodsInfoVO.SpuListVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchGoodsInfoVO.SpuListVO> qw);


    @Select(
            "SELECT DISTINCT gs.id,gs.merchant_id,gs.shop_id,gs.brand_id,gs.spec_info_id,gs.attribute_info_id," +
                    "gs.extend_params_id,gs.goods_name,gs.goods_title,gs.goods_state,gs.goods_no,gs.sale_price,gs.old_price," +
                    "gs.cost_price,gs.goods_weight,gs.goods_valid_days,gs.goods_image,gs.is_single,gs.point_price,gs.remarks,gs.is_point_good,gs.is_in_member_gift,gs.in_member_point_price,gs.sale_type,gs.third_product_id,gs.exchange_type,\n" +
                    "gs.is_show_old_price,gs.goods_price_unit,gs.use_platform,gs.publish_time,gs.cdate,gs.udate,gs.apply_type,ar.state as aduit_type from  gs_goods_info_temp gs \n" +
                    "left join gs_goods_audit_record ar on gs.id = ar.goods_id "+
                    "WHERE gs.flag =0 AND ${ew.sqlSegment} "
    )
    List<PCMerchGoodsInfoVO.SpuListVO> getCountByGoodsState(@Param(Constants.WRAPPER) QueryWrapper<PCMerchGoodsInfoVO.SpuListVO> qw);

    @Select("SELECT DISTINCT\n" +
            "gs.id,gs.merchant_id,gs.shop_id,gs.brand_id,gs.spec_info_id,gs.attribute_info_id,gs.extend_params_id,gs.goods_name,gs.goods_title,gs.goods_state,gs.goods_no,gs.sale_price,gs.old_price,gs.cost_price,gs.goods_weight,gs.goods_valid_days,gs.goods_image,gs.is_single,\n" +
            "gs.is_show_old_price,gs.goods_price_unit,gs.use_platform,gs.publish_time,gs.cdate,gs.udate,gs.point_price,gs.remarks,gs.is_point_good,gs.is_in_member_gift,gs.in_member_point_price,gs.sale_type,gs.third_product_id,gs.exchange_type," +
            "gc.gs_category_name categoryName,\n" +
            "gb.brand_name brandName\n" +
            "FROM\n" +
            "gs_goods_info_temp gs\n" +
            "LEFT JOIN gs_goods_category gc ON gs.category_id = gc.id\n" +
            "LEFT JOIN gs_goods_brand gb ON  gs.brand_id = gb.id\n" +
            "LEFT JOIN gs_goods_relation_label grl ON gs.id = grl.goods_id\n" +
            "WHERE gs.flag = 0 AND ${ew.sqlSegment}")
    IPage<GoodsInfoVO.SpuListVO> getGoodsInfo(IPage<GoodsInfoVO.SpuListVO> page, @Param(Constants.WRAPPER) QueryWrapper<GoodsInfoVO.SpuListVO> qw);
}
