package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardGoods;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCardGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;
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
* @since 2020-12-04
*/
@Component
public class PCMerchMarketMerchantCardGoodsServiceImpl implements IPCMerchMarketMerchantCardGoodsService {

    @Autowired
    private IMarketMerchantCardGoodsRepository repository;

    @Override
    public PageData<PCMerchMarketMerchantCardGoodsVO.ListVO> pageData(PCMerchMarketMerchantCardGoodsQTO.QTO qto) {
        QueryWrapper<MarketMerchantCardGoods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<MarketMerchantCardGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto,PCMerchMarketMerchantCardGoodsVO.ListVO.class, page);
    }

    @Override
    public void addMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto) {
        MarketMerchantCardGoods marketMerchantCardGoods = new MarketMerchantCardGoods();
        BeanUtils.copyProperties(eto, marketMerchantCardGoods);
        repository.save(marketMerchantCardGoods);
    }


    @Override
    public void deleteMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.ETO eto) {
        MarketMerchantCardGoods marketMerchantCardGoods = new MarketMerchantCardGoods();
        BeanUtils.copyProperties(eto, marketMerchantCardGoods);
        repository.updateById(marketMerchantCardGoods);
    }

    @Override
    public PCMerchMarketMerchantCardGoodsVO.DetailVO detailMarketMerchantCardGoods(PCMerchMarketMerchantCardGoodsDTO.IdDTO dto) {
        MarketMerchantCardGoods marketMerchantCardGoods = repository.getById(dto.getId());
        PCMerchMarketMerchantCardGoodsVO.DetailVO detailVo = new PCMerchMarketMerchantCardGoodsVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantCardGoods)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantCardGoods, detailVo);
        return detailVo;
    }

}
