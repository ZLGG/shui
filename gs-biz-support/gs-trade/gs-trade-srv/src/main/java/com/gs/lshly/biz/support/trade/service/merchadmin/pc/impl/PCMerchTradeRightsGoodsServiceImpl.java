package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeRightsGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsGoodsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-17
*/
@Component
public class PCMerchTradeRightsGoodsServiceImpl implements IPCMerchTradeRightsGoodsService {

    @Autowired
    private ITradeRightsGoodsRepository repository;

    @Override
    public PageData<PCMerchTradeRightsGoodsVO.ListVO> pageData(PCMerchTradeRightsGoodsQTO.QTO qto) {
        QueryWrapper<TradeRightsGoods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradeRightsGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeRightsGoodsVO.ListVO.class, page);
    }

    @Override
    public void addTradeRightsGoods(PCMerchTradeRightsGoodsDTO.ETO eto) {
        TradeRightsGoods tradeRightsGoods = new TradeRightsGoods();
        BeanUtils.copyProperties(eto, tradeRightsGoods);
        repository.save(tradeRightsGoods);
    }


    @Override
    public void deleteTradeRightsGoods(PCMerchTradeRightsGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeRightsGoods(PCMerchTradeRightsGoodsDTO.ETO eto) {
        TradeRightsGoods tradeRightsGoods = new TradeRightsGoods();
        BeanUtils.copyProperties(eto, tradeRightsGoods);
        repository.updateById(tradeRightsGoods);
    }

    @Override
    public PCMerchTradeRightsGoodsVO.DetailVO detailTradeRightsGoods(PCMerchTradeRightsGoodsDTO.IdDTO dto) {
        TradeRightsGoods tradeRightsGoods = repository.getById(dto.getId());
        PCMerchTradeRightsGoodsVO.DetailVO detailVo = new PCMerchTradeRightsGoodsVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeRightsGoods)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRightsGoods, detailVo);
        return detailVo;
    }

}
