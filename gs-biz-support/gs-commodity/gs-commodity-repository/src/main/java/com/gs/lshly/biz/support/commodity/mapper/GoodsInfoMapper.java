package com.gs.lshly.biz.support.commodity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodSkuInfoView;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Starry
 * @since 2020-10-08
 */
@Repository
public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {

    /**
     * 查询平台商品信息
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "gs.id,gs.merchant_id,gs.shop_id,gs.brand_id,gs.spec_info_id,gs.attribute_info_id,gs.extend_params_id,gs.goods_name,gs.goods_title,gs.goods_state,gs.goods_no,gs.sale_price,gs.old_price,gs.cost_price,gs.goods_weight,gs.goods_valid_days,gs.goods_image,gs.is_single,\n" +
            "gs.is_show_old_price,gs.goods_price_unit,gs.use_platform,gs.publish_time,gs.cdate,gs.udate,gs.point_price,gs.remarks,gs.is_point_good,gs.is_in_member_gift,gs.in_member_point_price,gs.sale_type,gs.third_product_id,gs.exchange_type," +
            "gc.gs_category_name categoryName,\n" +
            "gb.brand_name brandName\n" +
            "FROM\n" +
            "gs_goods_info gs\n" +
            "LEFT JOIN gs_goods_category gc ON gs.category_id = gc.id\n" +
            "LEFT JOIN gs_goods_brand gb ON  gs.brand_id = gb.id\n" +
            "LEFT JOIN gs_goods_relation_label grl ON gs.id = grl.goods_id\n" +
            "WHERE gs.flag = 0 AND ${ew.sqlSegment}")
    IPage<GoodsInfoVO.SpuListVO> getGoodsInfo(IPage<GoodsInfoVO.SpuListVO> page,@Param(Constants.WRAPPER) QueryWrapper<GoodsInfoVO.SpuListVO> qw);


    /**
     * 查询商家商品信息
     * @param page
     * @param qw
     * @return
     */
    @Select(
            "SELECT DISTINCT gs.id,gs.merchant_id,gs.shop_id,gs.brand_id,gs.spec_info_id,gs.attribute_info_id," +
                    "gs.extend_params_id,gs.goods_name,gs.goods_title,gs.goods_state,gs.goods_no,gs.sale_price,gs.old_price," +
                    "gs.cost_price,gs.goods_weight,gs.goods_valid_days,gs.goods_image,gs.is_single,gs.point_price,gs.remarks,gs.is_point_good,gs.is_in_member_gift,gs.in_member_point_price,gs.sale_type,gs.third_product_id,gs.exchange_type,\n" +
                    "gs.is_show_old_price,gs.goods_price_unit,gs.use_platform,gs.publish_time,gs.cdate,gs.udate from  gs_goods_info gs \n" +
                    "LEFT JOIN gs_goods_tempalte gt ON gs.id = gt.goods_id\n" +
                    "LEFT JOIN gs_goods_shop_navigation gsn ON gs.id = gsn.goods_id\n" +
                    "WHERE gs.flag =0 AND ${ew.sqlSegment} "
    )
    IPage<PCMerchGoodsInfoVO.SpuListVO> getMerchantGoodsInfo(IPage<PCMerchGoodsInfoVO.SpuListVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchGoodsInfoVO.SpuListVO> qw);


    /**
     * 查询店铺自定义分类下面的商品
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT gs.id,gs.merchant_id,gs.shop_id,gs.brand_id,gs.spec_info_id,gs.attribute_info_id,gs.extend_params_id,gs.goods_name,gs.goods_title,gs.goods_state,gs.goods_no,gs.sale_price,gs.old_price,gs.cost_price,gs.goods_weight,gs.goods_valid_days,gs.goods_image,gs.is_single,\n" +
            "gs.is_show_old_price,gs.goods_price_unit,gs.use_platform,gs.publish_time,gs.cdate,gs.udate,gs.point_price,gs.remarks,gs.is_point_good,gs.is_in_member_gift,gs.in_member_point_price,gs.sale_type,gs.third_product_id,gs.exchange_type,gs.sale_quantity FROM gs_goods_info gs\n" +
            "LEFT JOIN gs_goods_shop_navigation gsn ON gs.id = gsn.goods_id\n" +
            "where gs.flag = 0 AND gs.goods_state = 20 AND ${ew.sqlSegment}")
    IPage<GoodsInfo> getGoodsPageInfo(IPage<GoodsInfo> page, @Param(Constants.WRAPPER) QueryWrapper<GoodsInfo> qw);


    /**
     * 查询与店铺类目关联的商品信息（商家端）
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT gs.id, gs.goods_name,gs.goods_no,gs.sale_price FROM gs_goods_info gs \n" +
            "LEFT  JOIN gs_goods_shop_navigation gsn ON gs.id = gsn.goods_id\n" +
            "WHERE gs.flag = 0  AND ${ew.sqlSegment} ")
    IPage<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> getShopNavigationCommodityVO(IPage<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> qw);


    /**
     * 为活动提供商品列表
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT gs.id,gs.goods_name,gs.goods_image,gs.sale_price FROM gs_goods_info gs\n" +
            "LEFT JOIN gs_goods_category gc ON gs.category_id =gc.id\n" +
            "LEFT JOIN gs_goods_brand gb ON gs.brand_id = gb.id\n" +
            "WHERE gs.flag = 0 AND ${ew.sqlSegment}")
    IPage<PCMerchGoodsInfoVO.GoodsActiveVO> getGoodsActiveVO(IPage<PCMerchGoodsInfoVO.GoodsActiveVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchGoodsInfoVO.GoodsActiveVO> qw);

    /**
     * 为活动提供该店铺所有sku列表
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "\tgs.id goodId,\n" +
            "\tgsk.id skuId,\n" +
            "\tgs.goods_name goodsName,\n" +
            "\tgsk.image skuImg,\n" +
            "\tIFNULL( gsk.sale_price, gs.sale_price ) skuSalePrice,\n" +
            "\tgsk.specs_key,\n" +
            "\tgsk.specs_value,\n" +
            "\tgsk.state\n" +
            "FROM\n" +
            "\tgs_goods_info gs\n" +
            "\tLEFT JOIN gs_sku_good_info gsk ON gs.id = gsk.good_id\n" +
            "\tLEFT JOIN gs_goods_category gc ON gs.category_id = gc.id\n" +
            "\tLEFT JOIN gs_goods_brand gb ON gs.brand_id = gb.id \n" +
            "WHERE\n" +
            "\tgs.flag = 0 AND gsk.flag = 0 AND ${ew.sqlSegment}")
    IPage<PCMerchGoodsInfoVO.SkuActivePageVO> getSkuActivePageVO(IPage<PCMerchGoodsInfoVO.SkuActivePageVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchGoodsInfoVO.SkuActivePageVO> qw);


    /**
     * 获取sku商品信息
     *
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT sk.id skuId,sk.good_id goodsId,gs.goods_name goodsName,IFNULL(sk.sale_price,gs.sale_price) salePrice FROM gs_sku_good_info sk\n" +
            "LEFT JOIN gs_goods_info gs ON sk.good_id = gs.id\n" +
            "where gs.flag = 0 AND sk.flag = 0 AND ${ew.sqlSegment} ")
    List<GoodSkuInfoView> getGoodSkuInfoView(@Param(Constants.WRAPPER) QueryWrapper<GoodSkuInfoView> qw);


    /**
     * 统计店铺首页商品相关数量
     *
     * @param qw
     * @return
     */
    @Select("SELECT\n" +
            "\tCOUNT(\n" +
            "\tIF\n" +
            "\t( gs.goods_state != 20, gs.id, NULL )) AS underGoodsNum,\n" +
            "\tCOUNT(\n" +
            "\tIF\n" +
            "\t( gs.goods_state = 40, gs.id, NULL )) AS checkFaildNum,\n" +
            "\tCOUNT( DISTINCT qa.good_id ) qaGoodsNum \n" +
            "FROM\n" +
            "\tgs_goods_info gs\n" +
            "\tLEFT JOIN gs_goods_qa qa ON gs.id = qa.good_id \n" +
            "WHERE\n" +
            "\tgs.flag = 0 AND ${ew.sqlSegment}")
    PCMerchGoodsInfoVO.HomeCountGoodsVO getHomeCountGoodsVO(@Param(Constants.WRAPPER) QueryWrapper<GoodsInfo> qw);

    /**
     * 查询in会员专区商品
     * @param page
     * @param wrapper
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "gs.id,gs.brand_id,gs.spec_info_id,gs.goods_image,gs.goods_name,gs.goods_title,gs.sale_price,gs.old_price,\n" +
            "gs.goods_h5_desc,gs.in_coupon_type,gs.in_member_point_price,gi.spec_name,gi.spec_value,gb.brand_name\n" +
            "FROM\n" +
            "gs_goods_info gs\n" +
            "LEFT JOIN gs_goods_spec_info gi ON gs.spec_info_id = gi.id\n" +
            "LEFT JOIN gs_goods_brand gb ON  gs.brand_id = gb.id\n" +
            "WHERE ${ew.sqlSegment}")
    IPage<BbcGoodsInfoVO.InVIPSpecialAreaVO> queryInVIPSpecialAreaList(IPage<BbcGoodsInfoVO.InVIPSpecialAreaVO> page, @Param(Constants.WRAPPER) QueryWrapper<GoodsInfo> wrapper);

    /**
     * 查询积分商品列表
     * @param page
     * @param wrapper
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "gs.id,gs.goods_image,gs.goods_name,gs.goods_title,gs.goods_h5_desc,gb.brand_name,gm.shop_name,gs.sale_quantity,gs.point_price\n" +
            "from\n" +
            "gs_goods_info gs\n" +
            "left join gs_goods_brand gb on gs.brand_id = gb.id\n" +
            "left join gs_trade_margin gm on gs.shop_id = gm.shop_id\n" +
            "where ${ew.sqlSegment}")
    IPage<BbcGoodsInfoVO.IntegralGoodsInfo> queryIntegralGoodsInfo(IPage<BbcGoodsInfoVO.IntegralGoodsInfo> page, @Param(Constants.WRAPPER) QueryWrapper<GoodsInfo> wrapper);

    /**
     * 我能兑换积分商品列表
     * @return
     * @param wrapper
     */
    @Select("SELECT DISTINCT\n" +
            "gs.id,gs.goods_image,gs.goods_name,gs.goods_title,gb.brand_name,gs.point_price\n" +
            "from\n" +
            "gs_goods_info gs\n" +
            "left join gs_goods_brand gb on gs.brand_id = gb.id\n" +
            "where ${ew.sqlSegment}")
    List<BbcGoodsInfoVO.MyIntegrationExchangeVO> myIntegrationExchange(@Param(Constants.WRAPPER) QueryWrapper<GoodsInfo> wrapper);

    @Select("select gs.id from gs_goods_info gs where ${ew.sqlSegment}")
    List<String> getAllIds(@Param(Constants.WRAPPER) QueryWrapper<GoodsInfo> wrapper);
}
