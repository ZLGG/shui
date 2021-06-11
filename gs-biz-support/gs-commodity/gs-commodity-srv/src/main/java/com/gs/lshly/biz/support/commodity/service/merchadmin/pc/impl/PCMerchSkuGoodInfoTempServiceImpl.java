package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfoTemp;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoTempRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchSkuGoodInfoTempService;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCMerchSkuGoodInfoTempServiceImpl implements IPCMerchSkuGoodInfoTempService {

    @Autowired
    private ISkuGoodInfoTempRepository repository;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @Override
    public List<PCMerchSkuGoodInfoVO.DetailVO> getByGoodsId(PCMerchSkuGoodInfoDTO.GoodIdDTO goodId) {
        QueryWrapper<SkuGoodInfoTemp> skuBoost = MybatisPlusUtil.query();
        skuBoost.eq("good_id", goodId.getGoodId());
        List<SkuGoodInfoTemp> skuGoodInfos = repository.list(skuBoost);
        if (ObjectUtils.isNotEmpty(skuGoodInfos)) {
            List<PCMerchSkuGoodInfoVO.DetailVO> detailVOS = new ArrayList<>();
            for (SkuGoodInfoTemp skuGoodInfo : skuGoodInfos) {
                PCMerchSkuGoodInfoVO.DetailVO detailVO = new PCMerchSkuGoodInfoVO.DetailVO();
                BeanUtils.copyProperties(skuGoodInfo, detailVO);
                //获取sku存数量
                detailVO.setSkuStock(getSkuStockNum(goodId.getJwtShopId(), skuGoodInfo.getId()));
                detailVOS.add(detailVO);
            }
            return detailVOS;
        }
        return null;
    }

    private Integer getSkuStockNum(String shopId, String skuId) {
        CommonStockVO.InnerStockVO stockVO = commonStockRpc.queryStockTemp(null, shopId, skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null) {
            return stockVO.getQuantity();
        }
        return 0;
    }
}
