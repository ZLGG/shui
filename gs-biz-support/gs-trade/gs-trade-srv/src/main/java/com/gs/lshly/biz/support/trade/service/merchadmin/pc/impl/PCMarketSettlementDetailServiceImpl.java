package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeSettlementDetail;
import com.gs.lshly.biz.support.trade.repository.impl.TradeSettlementDetailRepositoryImpl;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketSettlementDetailService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2020-12-01
*/
@Component
public class PCMarketSettlementDetailServiceImpl implements IPCMarketSettlementDetailService {

    @Autowired
    private TradeSettlementDetailRepositoryImpl tradeSettlementDetailRepositoryImpl;

    @Override
    public PageData<TradeSettlementDetailVO.ListVO> pageData(TradeSettlementDetailQTO.ListQTO qto) {
        QueryWrapper<TradeSettlementDetail> wrapper = new QueryWrapper<>();
        if (ObjectUtils.isEmpty(qto.getJwtShopId())) {
            throw new BusinessException("无店铺ID数据");
        }
        wrapper.eq("shop_id", qto.getJwtShopId());
        if(ObjectUtils.isNotEmpty(qto.getSettlementType())){
            wrapper.eq("settlement_type",qto.getSettlementType());
        }
        if(ObjectUtils.isNotEmpty(qto.getBillStartTime()) && ObjectUtils.isNotEmpty(qto.getBillEndTime())){
            wrapper.between("cdate",qto.getBillStartTime(),qto.getBillEndTime());
        }
        wrapper.orderByDesc("cdate");
        IPage<TradeSettlementDetail> page = MybatisPlusUtil.pager(qto);
        tradeSettlementDetailRepositoryImpl.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, TradeSettlementDetailVO.ListVO.class, page);
    }

    @Override
    public TradeSettlementDetailVO.DetailVO detailTradeSettlementDetail(TradeSettlementDetailDTO.IdDTO dto) {
        TradeSettlementDetail tradeSettlementDetail = tradeSettlementDetailRepositoryImpl.getById(dto.getId());
        TradeSettlementDetailVO.DetailVO detailVo = new TradeSettlementDetailVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeSettlementDetail)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeSettlementDetail, detailVo);
        return detailVo;
    }

}
