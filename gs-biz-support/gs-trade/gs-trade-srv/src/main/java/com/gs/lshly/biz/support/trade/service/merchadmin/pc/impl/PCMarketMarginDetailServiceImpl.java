package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeMargin;
import com.gs.lshly.biz.support.trade.entity.TradeMarginDetail;
import com.gs.lshly.biz.support.trade.repository.impl.TradeMarginDetailRepositoryImpl;
import com.gs.lshly.biz.support.trade.repository.impl.TradeMarginRepositoryImpl;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketMarginDetailService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

@Component
public class PCMarketMarginDetailServiceImpl implements IPCMarketMarginDetailService {

    @Autowired
    private TradeMarginDetailRepositoryImpl tradeMarginDetailRepository;

    @Autowired
    private TradeMarginRepositoryImpl tradeMarginRepository;

    @Override
    public PageData<TradeMarginDetailVO.ListVO> PageData(TradeMarginDetailQTO.typeQTO qto) {
        QueryWrapper<TradeMarginDetail> wrapper = new QueryWrapper<>();
        if(ObjectUtils.isEmpty(qto.getJwtShopId())){
            throw new BusinessException("没有该店铺数据");
        }
        wrapper.eq("shop_id",qto.getJwtShopId());
        if(ObjectUtils.isNotEmpty(qto.getBillStartTime())){
            wrapper.ge("cdate",qto.getBillStartTime());
        }
        if(ObjectUtils.isNotEmpty(qto.getBillEndTime())){
            wrapper.le("cdate",qto.getBillEndTime());
        }
        if(ObjectUtils.isNotEmpty(qto.getPayType())){
            wrapper.eq("pay_type",qto.getPayType());
        }
        wrapper.orderByDesc("cdate");
        IPage<TradeMarginDetail> page = MybatisPlusUtil.pager(qto);
        IPage<TradeMarginDetail> detailIPage =  tradeMarginDetailRepository.page(page, wrapper);
        if(ObjectUtils.isEmpty(detailIPage) && ObjectUtils.isEmpty(detailIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, TradeMarginDetailVO.ListVO.class, page);
    }

    @Override
    public TradeMarginDetailVO.ListVO detailTradeMargin(TradeMarginDetailDTO.IdDTO dto) {
        TradeMarginDetail tradeMarginDetail = tradeMarginDetailRepository.getById(dto.getId());
        TradeMarginDetailVO.ListVO detailVo = new TradeMarginDetailVO.ListVO();
        if(ObjectUtils.isEmpty(tradeMarginDetail)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeMarginDetail, detailVo);
        return detailVo;
    }

    @Override
    public TradeMarginVO.ListVO view(TradeMarginDetailQTO.viewQTO qto) {
        QueryWrapper<TradeMargin> queryWrapper = new QueryWrapper<>();
        if(ObjectUtils.isEmpty(qto.getJwtShopId())){
            throw new BusinessException("没有该店铺数据");
        }
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        TradeMargin tradeMargin=tradeMarginRepository.getOne(queryWrapper);
        if(ObjectUtils.isEmpty(tradeMargin)){
            return null;
        }
        TradeMarginVO.ListVO detailVo = new TradeMarginVO.ListVO();
        BeanUtils.copyProperties(tradeMargin, detailVo);
        return detailVo;
    }

}
