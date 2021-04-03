package com.gs.lshly.biz.support.trade.service.merchadmin.h5.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeCancelService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeCancelVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-20
*/
@Component
public class H5MerchTradeCancelServiceImpl implements IH5MerchTradeCancelService {

    @Autowired
    private ITradeCancelRepository repository;

    @Autowired
    private ITradeGoodsRepository tradeGoodsRepository;

    @Override
    public PageData<H5MerchTradeCancelVO.ListVO> pageData(H5MerchTradeCancelQTO.QTO qto) {
        QueryWrapper<TradeCancel> wrapper = new QueryWrapper<>();
        wrapper.eq("td.`shop_id`",qto.getJwtShopId());
        if(ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.and(i -> i.eq("td.`trade_code`",qto.getTradeCode()));
        }
        if(ObjectUtils.isNotEmpty(qto.getCancelState())){
            wrapper.and(i -> i.eq("td.`cancel_state`",qto.getCancelState()));
        }
        if(ObjectUtils.isNotEmpty(qto.getSourceType())){
            wrapper.and(i -> i.eq("t.`source_type`",qto.getSourceType()));
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeId())){
            wrapper.and(i -> i.eq("t.`id`",qto.getTradeId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getStartTime()) || ObjectUtils.isNotEmpty(qto.getEndTime())){
            wrapper.and(i->i.ge("td.`cdate`",qto.getStartTime()).le("td.`cdate`",qto.getEndTime()));
        }
        wrapper.orderByDesc("td.cdate");
        IPage<TradeCancel> page = MybatisPlusUtil.pager(qto);
        repository.selectCancelListPage(page, wrapper);
        List<H5MerchTradeCancelVO.ListVO> listVOS = new ArrayList<>();
        for(TradeCancel tradeCancel : page.getRecords()){
            H5MerchTradeCancelVO.ListVO pcMerchTradeCancelVO = new H5MerchTradeCancelVO.ListVO();
            BeanUtils.copyProperties(tradeCancel,pcMerchTradeCancelVO);
            //填充商品信息

            QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
            tradeGoodsQueryWrapper.eq("trade_id",tradeCancel.getTradeId());
            List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
            List<H5MerchTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
            for(TradeGoods tradeGoods : tradeGoodsList){
                H5MerchTradeListVO.TradeGoodsVO tradeGoodsVO = new H5MerchTradeListVO.TradeGoodsVO();
                BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
                tradeGoodsVOS.add(tradeGoodsVO);
            }
            pcMerchTradeCancelVO.setTradeGoodsVOS(tradeGoodsVOS);


            listVOS.add(pcMerchTradeCancelVO);
        }
        return new PageData<>(listVOS, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public void addTradeCancel(H5MerchTradeCancelDTO.ETO eto) {
        TradeCancel tradeCancel = new TradeCancel();
        BeanUtils.copyProperties(eto, tradeCancel);
        repository.save(tradeCancel);
    }


    @Override
    public void deleteTradeCancel(H5MerchTradeCancelDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeCancel(H5MerchTradeCancelDTO.ETO eto) {
        TradeCancel tradeCancel = new TradeCancel();
        BeanUtils.copyProperties(eto, tradeCancel);
        repository.updateById(tradeCancel);
    }

    @Override
    public H5MerchTradeCancelVO.DetailVO detailTradeCancel(H5MerchTradeCancelDTO.IdDTO dto) {
        TradeCancel tradeCancel = repository.getById(dto.getId());
        H5MerchTradeCancelVO.DetailVO detailVo = new H5MerchTradeCancelVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeCancel)){
            return detailVo;
        }
        BeanUtils.copyProperties(tradeCancel, detailVo);
        return detailVo;
    }

}
