package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.gs.lshly.biz.support.commodity.entity.GoodsExtendParams;
import com.gs.lshly.biz.support.commodity.entity.GoodsExtendParamsTemp;
import com.gs.lshly.biz.support.commodity.repository.IGoodsExtendParamsRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsExtendParamsTempRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsExtendParamsTempService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author chenyang
 */
@Component
public class GoodsExtendParamsTempServiceImpl implements IGoodsExtendParamsTempService {


    @Autowired
    private IGoodsExtendParamsTempRepository repository;

    @Override
    public String addGoodsExtendParams(PCMerchGoodsExtendParamsDTO.ETO eto) {
        GoodsExtendParamsTemp goodsExtendParams = new GoodsExtendParamsTemp();
        BeanUtils.copyProperties(eto, goodsExtendParams);
        repository.save(goodsExtendParams);
        return goodsExtendParams.getId();
    }
}
