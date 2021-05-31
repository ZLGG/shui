package com.gs.lshly.biz.support.trade.mapper;

import com.gs.lshly.biz.support.trade.entity.CouponZoneGoodsRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券专区黑名单商品关联表 Mapper 接口
 * </p>
 *
 * @author chenyang
 * @since 2021-05-28
 */
public interface CouponZoneGoodsRelationMapper extends BaseMapper<CouponZoneGoodsRelation> {

    @Delete("<script>" +
            "delete from gs_coupon_zone_goods_relation where coupon_id in " +
            "<foreach collection='list' open='(' item='id' separator=',' close=')'> #{id}" +
            "</foreach>" +
            "</script>")
    boolean deleteByCouponIds(@Param("list") List<String> list);
}
