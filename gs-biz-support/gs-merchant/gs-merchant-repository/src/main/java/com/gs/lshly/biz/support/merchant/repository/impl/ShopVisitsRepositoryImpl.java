package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.entity.ShopVisits;
import com.gs.lshly.biz.support.merchant.mapper.ShopVisitsMapper;
import com.gs.lshly.biz.support.merchant.repository.IShopVisitsRepository;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2021-03-08
*/
@Service
public class ShopVisitsRepositoryImpl extends ServiceImpl<ShopVisitsMapper, ShopVisits> implements IShopVisitsRepository {
    @Autowired
    private ShopVisitsMapper shopVisitsMapper;

    @Override
    public List<PCMerchShopVO.ListUV> getUV(QueryWrapper<ShopVisits> qw) {
        return shopVisitsMapper.getUV(qw);
    }

    @Override
    public Integer getYesterDayPV(QueryWrapper<ShopVisits> query) {
        return shopVisitsMapper.getYesterDayPV(query);
    }
}