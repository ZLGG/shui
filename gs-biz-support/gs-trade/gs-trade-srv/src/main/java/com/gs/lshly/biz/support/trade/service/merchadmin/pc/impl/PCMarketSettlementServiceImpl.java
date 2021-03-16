package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeSettlement;
import com.gs.lshly.biz.support.trade.entity.TradeSettlementDetail;
import com.gs.lshly.biz.support.trade.mapper.TradeSettlementDetailMapper;
import com.gs.lshly.biz.support.trade.repository.impl.TradeSettlementDetailRepositoryImpl;
import com.gs.lshly.biz.support.trade.repository.impl.TradeSettlementRepositoryImpl;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketSettlementService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCMarketSettlementServiceImpl implements IPCMarketSettlementService {

    @Autowired
    private TradeSettlementRepositoryImpl tradeSettlementRepositoryImpl;
    @Autowired
    private TradeSettlementDetailRepositoryImpl tradeSettlementDetailRepository;
    @Autowired
    private TradeSettlementDetailMapper tradeSettlementDetailMapper;

    @Override
    public PageData<TradeSettlementVO.ListVO> settlementPageData(TradeSettlementQTO.PcQTO qto) {
        QueryWrapper<TradeSettlement> wrapper = new QueryWrapper<>();
        if (ObjectUtils.isEmpty(qto.getJwtShopId())) {
            throw new BusinessException("无店铺ID数据");
        }
        wrapper.eq("shop_id", qto.getJwtShopId());
        if(ObjectUtils.isNotEmpty(qto.getSettlementState())){
            wrapper.eq("settlement_state",qto.getSettlementState());
        }
        if(ObjectUtils.isNotEmpty(qto.getBillStartTime()) && ObjectUtils.isNotEmpty(qto.getBillEndTime())){
            wrapper.between("cdate",qto.getBillStartTime(),qto.getBillEndTime());
        }
        IPage<TradeSettlement> page = MybatisPlusUtil.pager(qto);
        tradeSettlementRepositoryImpl.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, TradeSettlementVO.ListVO.class, page);
    }

    @Override
    public TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto) {
        TradeSettlement tradeSettlement = tradeSettlementRepositoryImpl.getById(dto.getId());
        TradeSettlementVO.DetailVO detailVo = new TradeSettlementVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeSettlement)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeSettlement, detailVo);
        return detailVo;
    }


    @Override
    public List<TradeSettlementDetailVO.ListVO> DownloadExport(TradeSettlementQTO.DownloadExportQTO qto) {
        if (ObjectUtils.isEmpty(qto)){
            throw new BusinessException("参数不能为空");
        }
        QueryWrapper<TradeSettlementDetail> wrapper = MybatisPlusUtil.query();
        if(ObjectUtils.isEmpty(qto.getTradeCode())){
            throw new BootstrapMethodError("无账单编号数据");
        }
        wrapper.eq("trade_code",qto.getTradeCode());
        if(ObjectUtils.isEmpty(qto.getStartTime()) && ObjectUtils.isEmpty(qto.getEndTime())){
            throw new BootstrapMethodError("账单时间错误");
        }
        wrapper.between("cdate",qto.getStartTime(),qto.getEndTime());
        List<TradeSettlementDetailVO.ListVO> listVOArrayList = new ArrayList<>();

        List<TradeSettlementDetail> listVOS= tradeSettlementDetailMapper.listVOS(wrapper);
        for (TradeSettlementDetail tradeSettlementDetail:listVOS) {
            TradeSettlementDetailVO.ListVO tradeSettlementDetailVO = new TradeSettlementDetailVO.ListVO();
            BeanUtils.copyProperties(tradeSettlementDetail,tradeSettlementDetailVO);
            if(ObjectUtils.isEmpty(tradeSettlementDetail.getSettlementId())){
                throw new BusinessException("数据异常");
            }
            packSettlementDetailDate(tradeSettlementDetail);
        }
        return listVOArrayList;
    }

    private void packSettlementDetailDate(TradeSettlementDetail tradeSettlementDetail){
        TradeSettlementDetailVO.ListVO tradeSettlementDetailVO = new TradeSettlementDetailVO.ListVO();
        tradeSettlementDetailVO.setTradeCode(tradeSettlementDetail.getTradeCode());
        tradeSettlementDetailVO.setSettlementId(tradeSettlementDetail.getSettlementId());
        tradeSettlementDetailVO.setShopName(tradeSettlementDetail.getShopName());
        tradeSettlementDetailVO.setGoodsName(tradeSettlementDetail.getGoodsName());
        tradeSettlementDetailVO.setGoodsTitle(tradeSettlementDetail.getGoodsTitle());
        tradeSettlementDetailVO.setSalePrice(tradeSettlementDetail.getSalePrice());
        tradeSettlementDetailVO.setQuantity(tradeSettlementDetail.getQuantity());
        tradeSettlementDetailVO.setDeliveryAmount(tradeSettlementDetail.getDeliveryAmount());
        tradeSettlementDetailVO.setRefundWay(tradeSettlementDetail.getRefundWay());
        tradeSettlementDetailVO.setSettlementAmount(tradeSettlementDetail.getSettlementAmount());
        tradeSettlementDetailVO.setSettlementType(tradeSettlementDetail.getSettlementType());
        tradeSettlementDetailVO.setSettleTime(tradeSettlementDetail.getSettleTime());
        tradeSettlementDetailVO.setPayTime(tradeSettlementDetail.getPayTime());
        tradeSettlementDetailVO.setPayType(tradeSettlementDetail.getPayType());

    }


}
