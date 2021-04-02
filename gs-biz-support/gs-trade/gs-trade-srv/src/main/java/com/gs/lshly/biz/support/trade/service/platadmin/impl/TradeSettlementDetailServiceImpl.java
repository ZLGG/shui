package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeSettlementDetail;
import com.gs.lshly.biz.support.trade.repository.ITradeSettlementDetailRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeSettlementDetailService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2020-12-01
*/
@Component
public class TradeSettlementDetailServiceImpl implements ITradeSettlementDetailService {

    @Autowired
    private ITradeSettlementDetailRepository repository;

    @Override
    public PageData<TradeSettlementDetailVO.ListVO> pageData(TradeSettlementDetailQTO.ListQTO qto) {
        QueryWrapper<TradeSettlementDetail> wrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotEmpty(qto.getBillStartTime()) && ObjectUtils.isNotEmpty(qto.getBillEndTime())){
            wrapper.between("cdate",qto.getBillStartTime(),qto.getBillEndTime());
        }
        if (ObjectUtils.isNotEmpty(qto.getShopId()) ){
            wrapper.eq("shop_id",qto.getShopId());
        }
        if (ObjectUtils.isNotEmpty(qto.getSettlementType())){
            wrapper.eq("settlement_type", qto.getSettlementType());
        }
        if (ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.eq("trade_code", qto.getTradeCode());
        }
        wrapper.orderByDesc("cdate");
        IPage<TradeSettlementDetail> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, TradeSettlementDetailVO.ListVO.class, page);
    }

    @Override
    public void addTradeSettlementDetail(TradeSettlementDetailDTO.ETO eto) {
        TradeSettlementDetail tradeSettlementDetail = new TradeSettlementDetail();
        BeanUtils.copyProperties(eto, tradeSettlementDetail);
        repository.save(tradeSettlementDetail);
    }

    @Override
    public TradeSettlementDetailVO.DetailVO detailTradeSettlementDetail(TradeSettlementDetailDTO.IdDTO dto) {
        TradeSettlementDetail tradeSettlementDetail = repository.getById(dto.getId());
        TradeSettlementDetailVO.DetailVO detailVo = new TradeSettlementDetailVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeSettlementDetail)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeSettlementDetail, detailVo);
        return detailVo;
    }

}
