package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSku;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityGoodsSkuRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtActivityGoodsSkuService;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSkuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityGoodsSkuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityGoodsSkuVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-02
*/
@Component
public class PCMerchMarketPtActivityGoodsSkuServiceImpl implements IPCMerchMarketPtActivityGoodsSkuService {

    @Autowired
    private IMarketPtActivityGoodsSkuRepository repository;

    @Override
    public PageData<PCMerchMarketPtActivityGoodsSkuVO.ListVO> pageData(PCMerchMarketPtActivityGoodsSkuQTO.QTO qto) {
        QueryWrapper<MarketPtActivityGoodsSku> wrapper = new QueryWrapper<>();
        IPage<MarketPtActivityGoodsSku> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto,PCMerchMarketPtActivityGoodsSkuVO.ListVO.class, page);
    }

    @Override
    public void addMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto) {
        MarketPtActivityGoodsSku marketPtActivityGoodsSku = new MarketPtActivityGoodsSku();
        BeanUtils.copyProperties(eto, marketPtActivityGoodsSku);
        repository.save(marketPtActivityGoodsSku);
    }


    @Override
    public void deleteMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.ETO eto) {
        MarketPtActivityGoodsSku marketPtActivityGoodsSku = new MarketPtActivityGoodsSku();
        BeanUtils.copyProperties(eto, marketPtActivityGoodsSku);
        repository.updateById(marketPtActivityGoodsSku);
    }

    @Override
    public PCMerchMarketPtActivityGoodsSkuVO.DetailVO detailMarketPtActivityGoodsSku(PCMerchMarketPtActivityGoodsSkuDTO.IdDTO dto) {
        MarketPtActivityGoodsSku marketPtActivityGoodsSku = repository.getById(dto.getId());
        PCMerchMarketPtActivityGoodsSkuVO.DetailVO detailVo = new PCMerchMarketPtActivityGoodsSkuVO.DetailVO();
        if(ObjectUtils.isEmpty(marketPtActivityGoodsSku)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketPtActivityGoodsSku, detailVo);
        return detailVo;
    }

    @Override
    public CommonMarketDTO.MarketSku activePtActivitySku(List<CommonMarketDTO.SkuId> skuIds, ActivityTerminalEnum terminal) {
        //查询sku的平台活动最低价
        //商家活动报名已通过审核,且在活动时间内的所有sku
        List<String> ids = ListUtil.getIdList(CommonMarketDTO.SkuId.class, skuIds, "skuId");
        String idsStr = CollUtil.isNotEmpty(ids) ? CollUtil.join(ids, "','") : "-1";
        List<CommonMarketDTO.MarketSku> list = repository.baseMapper().activeSkuPrice(idsStr);
        return CommonMarketDTO.MarketSku.pickBest(list, skuIds);
    }

}
