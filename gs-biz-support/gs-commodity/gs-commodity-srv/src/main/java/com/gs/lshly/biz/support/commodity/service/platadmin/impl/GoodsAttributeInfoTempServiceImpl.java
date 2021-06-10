package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeInfoTemp;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeInfoTempRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeInfoTempService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author chenyang
 */
@Component
public class GoodsAttributeInfoTempServiceImpl implements IGoodsAttributeInfoTempService {

    @Autowired
    private IGoodsAttributeInfoTempRepository repository;

    @Override
    public String addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO dto) {
        GoodsAttributeInfoTemp goodsAttributeInfo = new GoodsAttributeInfoTemp();
        BeanUtils.copyProperties(dto, goodsAttributeInfo);
        repository.save(goodsAttributeInfo);
        return goodsAttributeInfo.getId();
    }
}
