package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCancelService;
import com.gs.lshly.common.enums.TradeCancelRefundStateEnum;
import com.gs.lshly.common.enums.TradeCancelStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCancelVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-20
*/
@Component
public class PCMerchTradeCancelServiceImpl implements IPCMerchTradeCancelService {

    private final ITradeCancelRepository repository;
    private final ITradeGoodsRepository tradeGoodsRepository;
    @Autowired
    private ITradeRepository tradeRepository;
    @DubboReference
    private IPCMerchUserRpc ipcMerchUserRpc;

    public PCMerchTradeCancelServiceImpl(ITradeCancelRepository repository, ITradeGoodsRepository tradeGoodsRepository) {
        this.repository = repository;
        this.tradeGoodsRepository = tradeGoodsRepository;
    }

    @Override
    public PageData<PCMerchTradeCancelVO.ListVO> pageData(PCMerchTradeCancelQTO.QTO qto) {
        QueryWrapper<TradeCancel> wrapper = new QueryWrapper<>();
        wrapper.eq("td.`shop_id`",qto.getJwtShopId());
        if(ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.and(i -> i.like("td.`trade_code`",qto.getTradeCode()));
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
        if(ObjectUtils.isNotEmpty(qto.getPhone())){
            wrapper.and(i -> i.eq("u.`phone`",qto.getPhone()));
        }
        if(ObjectUtils.isNotEmpty(qto.getAddress())){
            wrapper.and(i -> i.like("t.`recv_full_addres`",qto.getAddress()));
        }
        if(ObjectUtils.isNotEmpty(qto.getApplyType())){
            wrapper.and(i -> i.eq("td.`apply_type`",qto.getApplyType()));
        }
        wrapper.orderByDesc("td.`cdate`");
        IPage<TradeCancel> page = MybatisPlusUtil.pager(qto);
        repository.selectCancelListPage(page, wrapper);
        List<PCMerchTradeCancelVO.ListVO> listVOS = new ArrayList<>();
        for(TradeCancel tradeCancel : page.getRecords()){
            PCMerchTradeCancelVO.ListVO pcMerchTradeCancelVO = new PCMerchTradeCancelVO.ListVO();
            BeanUtils.copyProperties(tradeCancel,pcMerchTradeCancelVO);
            //填充商品信息
            if (ObjectUtils.isNotEmpty(pcMerchTradeCancelVO.getRefundState())) {
                if (tradeCancel.getRefundState() == TradeCancelRefundStateEnum.无需退款.getCode()) {
                    pcMerchTradeCancelVO.setTradeAmount(BigDecimal.ZERO);
                }
            }
            QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
            tradeGoodsQueryWrapper.eq("trade_id",tradeCancel.getTradeId());
            List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
            List<PCMerchTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
            for(TradeGoods tradeGoods : tradeGoodsList){
                PCMerchTradeListVO.TradeGoodsVO tradeGoodsVO = new PCMerchTradeListVO.TradeGoodsVO();
                BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
                tradeGoodsVOS.add(tradeGoodsVO);
            }
            pcMerchTradeCancelVO.setTradeGoodsVOS(tradeGoodsVOS);


            listVOS.add(pcMerchTradeCancelVO);
        }
        return new PageData<>(listVOS, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public ResponseData<Void> examineTradeCancel(PCMerchTradeCancelDTO.ExamineDTO eto) {
        TradeCancel tradeCancel = repository.getById(eto.getId());
        if(ObjectUtils.isNull(tradeCancel)){
            throw new BusinessException("无数据");
        }
        if(!tradeCancel.getCancelState().equals(TradeCancelStateEnum.提交申请.getCode())){
            throw new BusinessException("当前状态不允许该操作");
        }
        if(eto.getCancelState().equals(TradeCancelStateEnum.商家驳回.getCode())){
            tradeCancel.setCancelState(eto.getCancelState());
            tradeCancel.setRejectReason(eto.getRejectReason());
            tradeCancel.setRejectTime(LocalDateTime.now());
        }else if(eto.getCancelState().equals(TradeCancelStateEnum.商家确认.getCode())){
            tradeCancel.setCancelState(eto.getCancelState());
            tradeCancel.setRefundState(TradeCancelRefundStateEnum.等待退款.getCode());
            tradeCancel.setPassTime(LocalDateTime.now());
        }else{
            throw new BusinessException("审核状态异常");
        }
        repository.saveOrUpdate(tradeCancel);

        return ResponseData.success();
    }

    @Override
    public PCMerchTradeCancelVO.DetailVO detailTradeCancel(PCMerchTradeCancelDTO.IdDTO dto) {
        TradeCancel tradeCancel = repository.getById(dto.getId());
        PCMerchTradeCancelVO.DetailVO detailVo = new PCMerchTradeCancelVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeCancel)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeCancel, detailVo);
        //填充商品信息
        Trade trade = tradeRepository.getById(tradeCancel.getTradeId());
        if (ObjectUtils.isNotEmpty(trade)){
            detailVo.setDeliveryAmount(trade.getDeliveryAmount()).
                    setRecvFullAddres(trade.getRecvFullAddres()).
                    setRecvPersonName(trade.getRecvPersonName()).
                    setRecvPhone(trade.getRecvPhone()).
                    setTradeAmount(trade.getTradeAmount()).
                    setBuyerRemark(trade.getBuyerRemark());
        }
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeCancel.getTradeId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<PCMerchTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeGoods tradeGoods : tradeGoodsList){
            PCMerchTradeListVO.TradeGoodsVO tradeGoodsVO = new PCMerchTradeListVO.TradeGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
            tradeGoodsVOS.add(tradeGoodsVO);
        }
        detailVo.setTradeGoodsVOS(tradeGoodsVOS);
        PCMerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(tradeCancel.getUserId());
        if (ObjectUtils.isNotEmpty(userSimpleVO)){
            detailVo.setUserName(userSimpleVO.getUserName()).setUserPhone(userSimpleVO.getPhone());
        }
        return detailVo;
    }

}
