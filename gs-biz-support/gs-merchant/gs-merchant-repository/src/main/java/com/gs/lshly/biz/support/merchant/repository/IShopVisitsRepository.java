package com.gs.lshly.biz.support.merchant.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.merchant.entity.ShopVisits;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdf
 * @since 2021-03-08
 */
public interface IShopVisitsRepository extends IService<ShopVisits> {

    List<PCMerchShopVO.ListUV> getUV(@Param(Constants.WRAPPER) QueryWrapper<ShopVisits> qw);

    Integer getYesterDayPV(@Param(Constants.WRAPPER) QueryWrapper<ShopVisits> query);
}
