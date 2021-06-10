package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfoTemp;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoTempRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecInfoTempService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author chenyang
 */
@Component
public class GoodsSpecInfoTempServiceImpl implements IGoodsSpecInfoTempService {

    @Autowired
    private IGoodsSpecInfoTempRepository repository;

    @Override
    public String addGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto) {
        GoodsSpecInfoTemp goodsSpecInfo = new GoodsSpecInfoTemp();
        BeanUtils.copyProperties(eto, goodsSpecInfo);
        repository.save(goodsSpecInfo);
        return goodsSpecInfo.getId();
    }
}
