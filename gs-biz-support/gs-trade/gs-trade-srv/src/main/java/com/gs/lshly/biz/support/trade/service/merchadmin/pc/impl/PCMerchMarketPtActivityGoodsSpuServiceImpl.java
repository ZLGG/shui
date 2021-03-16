package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSpu;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityGoodsSpuRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtActivityGoodsSpuService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityGoodsSpuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityGoodsSpuVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-01
*/
@Component
public class PCMerchMarketPtActivityGoodsSpuServiceImpl implements IPCMerchMarketPtActivityGoodsSpuService {

    @Autowired
    private IMarketPtActivityGoodsSpuRepository repository;

    @Override
    public PageData<PCMerchMarketPtActivityGoodsSpuVO.ListVO> pageData(PCMerchMarketPtActivityGoodsSpuQTO.QTO qto) {
        QueryWrapper<MarketPtActivityGoodsSpu> wrapper = new QueryWrapper<>();
        IPage<MarketPtActivityGoodsSpu> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto,PCMerchMarketPtActivityGoodsSpuVO.ListVO.class, page);
    }

    @Override
    public void addMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.ETO eto) {
        MarketPtActivityGoodsSpu marketPtActivityGoodsSpu = new MarketPtActivityGoodsSpu();
        BeanUtils.copyProperties(eto, marketPtActivityGoodsSpu);
        repository.save(marketPtActivityGoodsSpu);
    }


    @Override
    public void deleteMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.ETO eto) {
        MarketPtActivityGoodsSpu marketPtActivityGoodsSpu = new MarketPtActivityGoodsSpu();
        BeanUtils.copyProperties(eto, marketPtActivityGoodsSpu);
        repository.updateById(marketPtActivityGoodsSpu);
    }

    @Override
    public PCMerchMarketPtActivityGoodsSpuVO.DetailVO detailMarketPtActivityGoodsSpu(PCMerchMarketPtActivityGoodsSpuDTO.IdDTO dto) {
        MarketPtActivityGoodsSpu marketPtActivityGoodsSpu = repository.getById(dto.getId());
        PCMerchMarketPtActivityGoodsSpuVO.DetailVO detailVo = new PCMerchMarketPtActivityGoodsSpuVO.DetailVO();
        if(ObjectUtils.isEmpty(marketPtActivityGoodsSpu)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketPtActivityGoodsSpu, detailVo);
        return detailVo;
    }

}
