package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeMargin;
import com.gs.lshly.biz.support.trade.entity.TradeMarginDetail;
import com.gs.lshly.biz.support.trade.repository.ITradeMarginDetailRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeMarginRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeMarginService;
import com.gs.lshly.common.enums.MarginDetailEnum;
import com.gs.lshly.common.enums.MarginEnum;
import com.gs.lshly.common.enums.TradeMarginEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2020-12-09
*/
@Component
public class TradeMarginServiceImpl implements ITradeMarginService {

    @Autowired
    private ITradeMarginRepository iTradeMarginRepository;
    @Autowired
    private ITradeMarginDetailRepository iTradeMarginDetailRepository;

    @Override
    public PageData<TradeMarginVO.ListVO> pageData(TradeMarginQTO.marginQTO qto) {
        QueryWrapper<TradeMargin> wrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotEmpty(qto.getMarginType())){
            wrapper.eq("margin_type",qto.getMarginType());
        }
        if(ObjectUtils.isNotEmpty(qto.getShopName())){
            wrapper.eq("shop_name",qto.getShopName());
        }
        if(ObjectUtils.isNotEmpty(qto.getMarginScreen())){
            if(qto.getMarginScreen().equals(MarginEnum.大于.getCode())){
                wrapper.gt("margin_quota",qto.getMarginQuota());
            }
            if(qto.getMarginScreen().equals(MarginEnum.小于.getCode())){
                wrapper.lt("margin_quota",qto.getMarginQuota());
            }
            if(qto.getMarginScreen().equals(MarginEnum.等于.getCode())){
                wrapper.eq("margin_quota",qto.getMarginQuota());
            }
            if(qto.getMarginScreen().equals(MarginEnum.大于等于.getCode())){
                wrapper.ge("margin_quota",qto.getMarginQuota());
            }
            if(qto.getMarginScreen().equals(MarginEnum.小于等于.getCode())){
                wrapper.le("margin_quota",qto.getMarginQuota());
            }
            if(qto.getMarginScreen().equals(MarginEnum.介于.getCode())){
                if(ObjectUtils.isNotEmpty(qto.getBetweenGe()) && ObjectUtils.isNotEmpty(qto.getBetweenLe())){
                        wrapper.between("margin_quota",qto.getBetweenGe(),qto.getBetweenLe());
                }
            }
        }
        wrapper.orderByDesc("cdate");
        IPage<TradeMargin> page = MybatisPlusUtil.pager(qto);
        IPage<TradeMargin> pager = iTradeMarginRepository.page(page, wrapper);
        if(ObjectUtils.isEmpty(pager) && ObjectUtils.isEmpty(pager.getRecords())){
            new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, TradeMarginVO.ListVO.class, page);
    }

    @Override
    public void updateByQuota(TradeMarginDTO.QuotaDTO eto) {
        TradeMargin tradeMargin = iTradeMarginRepository.getById(eto.getId());
        tradeMargin.setUdate(LocalDateTime.now());
        BeanUtils.copyProperties(eto, tradeMargin);
        iTradeMarginRepository.updateById(tradeMargin);
        //保证金明细
        finQuota(tradeMargin ,eto);
    }

    private void finQuota(TradeMargin tradeMargin ,TradeMarginDTO.QuotaDTO eto){
        TradeMarginDetail tradeMarginDetail = new TradeMarginDetail();
        tradeMarginDetail.setMarginId(tradeMargin.getId());
        tradeMarginDetail.setShopId(tradeMargin.getShopId());
        tradeMarginDetail.setShopName(tradeMargin.getShopName());
        tradeMarginDetail.setPayAmount(eto.getMarginQuota());
        tradeMarginDetail.setPayType(MarginDetailEnum.额度调整.getCode());
        tradeMarginDetail.setUserId(eto.getJwtUserId());
        tradeMarginDetail.setUserName(eto.getJwtUserName());
        tradeMarginDetail.setFlag(false);
        iTradeMarginDetailRepository.save(tradeMarginDetail);
    }

    @Override
    public void updateByCharge(TradeMarginDTO.ChargeDTO eto) {
        TradeMargin tradeMargin = iTradeMarginRepository.getById(eto.getId());
        if(ObjectUtils.isEmpty(tradeMargin)){
            throw new BootstrapMethodError("没有数据！");
        }
        //充值后额度
        BigDecimal marginCharge = BigDecimal.ZERO;
        marginCharge = tradeMargin.getMarginBalance().add(eto.getMarginDeduction());
        tradeMargin.setMarginBalance(marginCharge);
        tradeMargin.setUdate(LocalDateTime.now());
        iTradeMarginRepository.updateById(tradeMargin);
        BeanUtils.copyProperties(eto, tradeMargin);
        //保证金明细
        finCharge(tradeMargin ,eto);
    }

    private void finCharge(TradeMargin tradeMargin ,TradeMarginDTO.ChargeDTO eto){
        TradeMarginDetail tradeMarginDetail = new TradeMarginDetail();
        tradeMarginDetail.setMarginId(tradeMargin.getId());
        tradeMarginDetail.setShopId(tradeMargin.getShopId());
        tradeMarginDetail.setShopName(tradeMargin.getShopName());
        tradeMarginDetail.setPayAmount(eto.getMarginDeduction());
        tradeMarginDetail.setPayType(MarginDetailEnum.充值.getCode());
        tradeMarginDetail.setBankSerialNum(eto.getBankSerialNum());
        tradeMarginDetail.setComment(eto.getComment());
        tradeMarginDetail.setUserId(eto.getJwtUserId());
        tradeMarginDetail.setUserName(eto.getJwtUserName());
        tradeMarginDetail.setFlag(false);
        iTradeMarginDetailRepository.save(tradeMarginDetail);
    }

    @Override
    public void updateByDeduction(TradeMarginDTO.DeductionDTO eto) {
        TradeMargin tradeMargin = iTradeMarginRepository.getById(eto.getId());
        if(ObjectUtils.isEmpty(tradeMargin)){
            throw new BootstrapMethodError("没有数据！");
        }
        //扣款后余额
        BigDecimal marginDeduction = BigDecimal.ZERO;
        marginDeduction = tradeMargin.getMarginBalance().subtract(eto.getMarginDeduction());
        if(marginDeduction.compareTo(tradeMargin.getMarginDown()) <= 1  ){
            tradeMargin.setMarginType(TradeMarginEnum.预警.getCode());
        }
        if(marginDeduction.compareTo(BigDecimal.ZERO) <= 0){
            tradeMargin.setMarginType(TradeMarginEnum.欠费.getCode());
        }
        tradeMargin.setMarginBalance(marginDeduction);
        tradeMargin.setUdate(LocalDateTime.now());
        iTradeMarginRepository.updateById(tradeMargin);
        BeanUtils.copyProperties(eto, tradeMargin);
        //保证金明细
        finDeduction(tradeMargin,eto);
    }


    private void finDeduction(TradeMargin tradeMargin ,TradeMarginDTO.DeductionDTO eto){
        TradeMarginDetail tradeMarginDetail = new TradeMarginDetail();
        tradeMarginDetail.setMarginId(tradeMargin.getId());
        tradeMarginDetail.setShopId(tradeMargin.getShopId());
        tradeMarginDetail.setShopName(tradeMargin.getShopName());
        tradeMarginDetail.setPayAmount(eto.getMarginDeduction());
        tradeMarginDetail.setPayType(MarginDetailEnum.扣款.getCode());
        tradeMarginDetail.setTradeCode(eto.getTradeCode());
        tradeMarginDetail.setPenaltyReason(eto.getPenaltyReason());
        tradeMarginDetail.setIllegalDescription(eto.getIllegalDescription());
        tradeMarginDetail.setComment(eto.getComment());
        tradeMarginDetail.setUserId(eto.getJwtUserId());
        tradeMarginDetail.setUserName(eto.getJwtUserName());
        tradeMarginDetail.setFlag(false);
        iTradeMarginDetailRepository.save(tradeMarginDetail);

    }


    @Override
    public TradeMarginVO.DetailVO detailTradeMargin(TradeMarginDTO.IdDTO dto) {
        TradeMargin tradeMargin = iTradeMarginRepository.getById(dto.getId());
        TradeMarginVO.DetailVO detailVo = new TradeMarginVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeMargin)){
            throw new BusinessException("没有数据！");
        }
        BeanUtils.copyProperties(tradeMargin, detailVo);
        return detailVo;
    }

    @Override
    public void InnerCreateShopMargin(TradeMarginDTO.InnerCreateMargin dto) {
        TradeMargin tradeMargin = new TradeMargin();
        tradeMargin.setMarginBalance(BigDecimal.ZERO);
        BeanUtils.copyProperties(dto,tradeMargin);
        iTradeMarginRepository.save(tradeMargin);
    }

}
