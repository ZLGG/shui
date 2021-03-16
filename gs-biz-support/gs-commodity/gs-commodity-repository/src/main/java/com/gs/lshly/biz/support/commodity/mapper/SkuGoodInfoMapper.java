package com.gs.lshly.biz.support.commodity.mapper;

import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Starry
 * @since 2020-10-08
 */
public interface SkuGoodInfoMapper extends BaseMapper<SkuGoodInfo> {

    //todo 垮业务中心，需要改造
    @Select("SELECT s.specs_value spec_value,IFNULL(s.sale_price,g.`sale_price`) sku_price," +
            "IFNULL(s.image,g.`goods_image`) sku_image, s.good_id goodsId, " +
            "g.goods_name, g.goods_title,  g.shop_id, p.shop_name  " +
            "FROM gs_sku_good_info s  " +
            "INNER JOIN gs_goods_info g ON g.id=s.good_id  " +
            "INNER JOIN gs_shop p ON p.id=g.shop_id" +
            " where s.id=#{skuId}")
    BbcUserShoppingCarVO.InnerSkuInfoVO getSkuDetail(@Param("skuId") String skuId);

    @Select("SELECT s.specs_value spec_value,IFNULL(s.sale_price,g.`sale_price`) sku_price," +
            "IFNULL(s.image,g.`goods_image`) sku_image, s.good_id goodsId, " +
            "g.goods_name, g.goods_title,  g.shop_id, p.shop_name  " +
            "FROM gs_sku_good_info s  " +
            "INNER JOIN gs_goods_info g ON g.id=s.good_id  " +
            "INNER JOIN gs_shop p ON p.id=g.shop_id" +
            " where s.id=#{skuId}")
    BbbH5UserShoppingCarVO.InnerSkuInfoVO getSkuDetailForB2b(@Param("skuId") String skuId);
}
