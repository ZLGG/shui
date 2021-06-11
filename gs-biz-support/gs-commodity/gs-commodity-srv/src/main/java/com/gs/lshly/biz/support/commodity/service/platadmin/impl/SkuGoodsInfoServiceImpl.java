package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
        queryWrapper.eq("good_id", dto.getGoodsId());
        List<SkuGoodInfo> skuGoodInfos = repository.list(queryWrapper);
        List<SkuGoodsInfoVO.DetailVO> listVOS = new ArrayList<>();
        if (skuGoodInfos != null && skuGoodInfos.size() > 0) {
            for (SkuGoodInfo sku : skuGoodInfos) {
                SkuGoodsInfoVO.DetailVO listVO = new SkuGoodsInfoVO.DetailVO();
                BeanUtils.copyProperties(sku, listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

    @Override
    public MarketPtSeckillVO.SkuGoodsInfo selectOne(String id) {
        SkuGoodInfo skuGoodInfo = repository.getById(id);
        MarketPtSeckillVO.SkuGoodsInfo goodsInfo = new MarketPtSeckillVO.SkuGoodsInfo();
        if (ObjectUtil.isNotEmpty(skuGoodInfo)) {
            if (StrUtil.isNotEmpty(skuGoodInfo.getSpecsValue())) {
                goodsInfo.setSpecsValue(skuGoodInfo.getSpecsValue());
            }
            if (ObjectUtil.isNotEmpty(skuGoodInfo.getSalePrice())) {
                goodsInfo.setSaleSkuPrice(skuGoodInfo.getSalePrice());
            } else if (ObjectUtil.isNotEmpty(skuGoodInfo.getPointPrice())) {
                goodsInfo.setSaleSkuPrice(skuGoodInfo.getPointPrice());
            }
        }
        return goodsInfo;
    }

    @Override
    public String selectSkuImg(String skuId) {
        if (StrUtil.isNotEmpty(skuId)) {
            throw new BusinessException("skuId为空");
        }
        SkuGoodInfo byId = repository.getById(skuId);
        if (ObjectUtil.isEmpty(byId)) {
            throw new BusinessException("未查询到sku商品数据");
        }
        if (StrUtil.isEmpty(byId.getImage())) {
            return null;
        }
        return byId.getImage();
    }
}
