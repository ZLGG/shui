package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.ISkuGoodInfoService;
import com.gs.lshly.common.struct.platadmin.commodity.dto.SkuGoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;

/**
 * @Author Starry
 * @Date 16:01 2020/10/14
 */
@Service
public class SkuGoodsInfoServiceImpl implements ISkuGoodInfoService {
    @Autowired
    private ISkuGoodInfoRepository repository;

    @Override
    public List<SkuGoodsInfoVO.DetailVO> listSku(SkuGoodsInfoDTO.GoodsIdDTO dto) {
        QueryWrapper<SkuGoodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("good_id",dto.getGoodsId());
        List<SkuGoodInfo> skuGoodInfos = repository.list(queryWrapper);
        List<SkuGoodsInfoVO.DetailVO> listVOS = new ArrayList<>();
        if (skuGoodInfos != null && skuGoodInfos.size() >0){
            for (SkuGoodInfo sku: skuGoodInfos) {
                SkuGoodsInfoVO.DetailVO listVO = new SkuGoodsInfoVO.DetailVO();
                BeanUtils.copyProperties(sku,listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }
}
