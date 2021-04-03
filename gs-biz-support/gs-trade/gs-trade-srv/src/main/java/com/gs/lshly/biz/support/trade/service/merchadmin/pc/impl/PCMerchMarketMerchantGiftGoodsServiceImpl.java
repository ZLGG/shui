package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGiftGoods;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGiftGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-09
*/
@Component
public class PCMerchMarketMerchantGiftGoodsServiceImpl implements IPCMerchMarketMerchantGiftGoodsService {

    @Autowired
    private IMarketMerchantGiftGoodsRepository repository;

    @Override
    public PageData<PCMerchMarketMerchantGiftGoodsVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsQTO.QTO qto) {
        QueryWrapper<MarketMerchantGiftGoods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<MarketMerchantGiftGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMarketMerchantGiftGoodsVO.ListVO.class, page);
    }

    @Override
    public void addMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto) {
        MarketMerchantGiftGoods marketMerchantGiftGoods = new MarketMerchantGiftGoods();
        BeanUtils.copyProperties(eto, marketMerchantGiftGoods);
        repository.save(marketMerchantGiftGoods);
    }


    @Override
    public void deleteMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.ETO eto) {
        MarketMerchantGiftGoods marketMerchantGiftGoods = new MarketMerchantGiftGoods();
        BeanUtils.copyProperties(eto, marketMerchantGiftGoods);
        repository.updateById(marketMerchantGiftGoods);
    }

    @Override
    public PCMerchMarketMerchantGiftGoodsVO.DetailVO detailMarketMerchantGiftGoods(PCMerchMarketMerchantGiftGoodsDTO.IdDTO dto) {
        MarketMerchantGiftGoods marketMerchantGiftGoods = repository.getById(dto.getId());
        PCMerchMarketMerchantGiftGoodsVO.DetailVO detailVo = new PCMerchMarketMerchantGiftGoodsVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantGiftGoods)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantGiftGoods, detailVo);
        return detailVo;
    }

}
