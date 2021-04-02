package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.merchant.entity.ShopVisits;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2021-03-08
 */
public interface ShopVisitsMapper extends BaseMapper<ShopVisits> {

    @Select("SELECT DISTINCT ip,shop_id,user_id FROM `gs_shop_visits` WHERE TO_DAYS( cdate )= TO_DAYS(NOW())-1 AND ${ew.sqlSegment}")
    List<PCMerchShopVO.ListUV> getUV(@Param(Constants.WRAPPER) QueryWrapper<ShopVisits> qw);
    @Select("SELECT COUNT(1) FROM `gs_shop_visits` WHERE TO_DAYS( cdate )= TO_DAYS(NOW())-1 AND ${ew.sqlSegment}")
    Integer getYesterDayPV(@Param(Constants.WRAPPER)QueryWrapper<ShopVisits> query);
}
