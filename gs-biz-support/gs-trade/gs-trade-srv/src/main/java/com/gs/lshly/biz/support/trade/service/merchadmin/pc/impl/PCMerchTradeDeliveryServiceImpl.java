package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeDeliveryService;
import com.gs.lshly.common.enums.TradeDeliveryTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.sms.ISMSService;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
@Slf4j
public class PCMerchTradeDeliveryServiceImpl implements IPCMerchTradeDeliveryService {

    private final ITradeDeliveryRepository tradeDeliveryRepository;

    private final ITradeRepository tradeRepository;

    @DubboReference
    private IPCMerchUserRpc ipcMerchUserRpc;

    @Autowired
    private  ISMSService ismsService ;

    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;

    public PCMerchTradeDeliveryServiceImpl(ITradeDeliveryRepository tradeDeliveryRepository,ITradeRepository tradeRepository) {
        this.tradeDeliveryRepository = tradeDeliveryRepository;
        this.tradeRepository = tradeRepository;
    }

    @Override
    public PageData<PCMerchTradeDeliveryVO.ListVO> pageData(PCMerchTradeDeliveryQTO.QTO qto) {
        QueryWrapper<PCMerchTradeDeliveryQTO.QTO> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("td.`shop_id`",qto.getJwtShopId()));
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.and(i -> i.eq("t.`trade_code`",qto.getTradeCode()));
        }
        IPage<PCMerchTradeDeliveryVO.ListVO> page = MybatisPlusUtil.pager(qto);
        tradeDeliveryRepository.selectListPage(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeDeliveryVO.ListVO.class, page);
    }

    @Override
    public void addTradeDelivery(PCMerchTradeDeliveryDTO.deliveryDTO eto) {
        //查询订单,判断订单状态
        Trade trade = tradeRepository.getById(eto.getTradeId());
        if(ObjectUtils.isEmpty(trade) ){
            throw new BusinessException("无订单信息");
        }else if(!trade.getTradeState().equals(TradeStateEnum.待发货.getCode())){
            throw new BusinessException("不是待发货状态");
        }
        if (trade.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())){
            if (ObjectUtils.isNotEmpty(trade.getUserId())){
                PCMerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(trade.getUserId());
                if (ObjectUtils.isNotEmpty(userSimpleVO)){
                    if (ObjectUtils.isNotEmpty(userSimpleVO.getUserName())){
                        String s = ismsService.sendPickUpSMSCode(trade.getRecvPhone(), trade.getTakeGoodsCode(),userSimpleVO.getUserName());
                        log.info("发送自提短信成功{}",s);
                    }
                }
            }
        }

        //新增发货记录
        if(trade.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode())){
            TradeDelivery tradeDelivery = new TradeDelivery();
            BeanUtils.copyProperties(eto, tradeDelivery);
            tradeDelivery.setUserId(trade.getUserId());
            tradeDelivery.setShopId(trade.getShopId());
            tradeDelivery.setOperatorId(eto.getJwtUserId());
            tradeDelivery.setOperatorName(eto.getJwtUserName());
            tradeDelivery.setDeliveryTime(LocalDateTime.now());
            tradeDeliveryRepository.save(tradeDelivery);
        }
        //修改订单状态
        trade.setTradeState(TradeStateEnum.待收货.getCode());
        tradeRepository.saveOrUpdate(trade);

    }

    @Override
    public void editTradeDelivery(PCMerchTradeDeliveryDTO.ETO eto) {
        TradeDelivery tradeDelivery = new TradeDelivery();
        BeanUtils.copyProperties(eto, tradeDelivery);
        tradeDeliveryRepository.updateById(tradeDelivery);
    }

    @Override
    public PCMerchTradeDeliveryVO.DetailVO detailTradeDelivery(PCMerchTradeDeliveryDTO.IdDTO dto) {
        TradeDelivery tradeDelivery = tradeDeliveryRepository.getById(dto.getId());
        PCMerchTradeDeliveryVO.DetailVO detailVo = new PCMerchTradeDeliveryVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeDelivery)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeDelivery, detailVo);
        return detailVo;
    }

    @Override
    public List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(PCMerchTradeDeliveryDTO.IdDTO dto) {
        return commonLogisticsCompanyRpc.listShopLogisticsCompany(dto.getJwtShopId());
    }

    @Override
    public void addTakeGoodsCodeCheck(PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO eto) {
        //查询订单,判断订单状态
        Trade trade = tradeRepository.getById(eto.getTradeId());
        if(ObjectUtils.isEmpty(trade) ){
            throw new BusinessException("无订单信息");
        }else if(!trade.getTradeState().equals(TradeStateEnum.待收货.getCode())){
            throw new BusinessException("不是待取货状态");
        }
        //判断自提码
        if(!eto.getTakeGoodsCode().equals(trade.getTakeGoodsCode())){
            throw new BusinessException("自提码不正确");
        }

        trade.setTradeState(TradeStateEnum.已完成.getCode());
        trade.setRecvTime(LocalDateTime.now());
        tradeRepository.saveOrUpdate(trade);
    }

}
