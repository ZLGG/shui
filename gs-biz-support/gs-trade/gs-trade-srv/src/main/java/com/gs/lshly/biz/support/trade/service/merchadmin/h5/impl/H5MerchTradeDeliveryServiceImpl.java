package com.gs.lshly.biz.support.trade.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeDeliveryService;
import com.gs.lshly.common.enums.TradeDeliveryTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeDeliveryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

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
public class H5MerchTradeDeliveryServiceImpl implements IH5MerchTradeDeliveryService {

    @Autowired
    private ITradeDeliveryRepository repository;
    @Autowired
    private ITradeRepository tradeRepository;
    @Autowired
    private ITradeDeliveryRepository tradeDeliveryRepository;
    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;


    @Override
    public PageData<H5MerchTradeDeliveryVO.ListVO> pageData(H5MerchTradeDeliveryQTO.QTO qto) {
        QueryWrapper<H5MerchTradeDeliveryQTO.QTO> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("td.`shop_id`",qto.getJwtShopId()));
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.and(i -> i.eq("t.`trade_code`",qto.getTradeCode()));
        }
        wrapper.orderByDesc("td.cdate");
        IPage<H5MerchTradeDeliveryVO.ListVO> page = MybatisPlusUtil.pager(qto);
        repository.selectH5ListPage(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, H5MerchTradeDeliveryVO.ListVO.class, page);
    }

    @Override
    public H5MerchTradeDeliveryVO.DetailVO detailTradeDelivery(H5MerchTradeDeliveryDTO.IdDTO idDTO) {
        TradeDelivery tradeDelivery = repository.getById(idDTO.getId());
        H5MerchTradeDeliveryVO.DetailVO detailVo = new H5MerchTradeDeliveryVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeDelivery)){
            return detailVo;
        }
        BeanUtils.copyProperties(tradeDelivery, detailVo);
        return detailVo;
    }

    @Override
    public void addTradeDelivery(H5MerchTradeDeliveryDTO.deliveryDTO dto) {
        //查询订单,判断订单状态
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if(ObjectUtils.isEmpty(trade) ){
            throw new BusinessException("无订单信息");
        }else if(!trade.getTradeState().equals(TradeStateEnum.待发货.getCode())){
            throw new BusinessException("不是待发货状态");
        }
        //新增发货记录
        if(trade.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode())){
            TradeDelivery tradeDelivery = new TradeDelivery();
            BeanUtils.copyProperties(dto, tradeDelivery);
            tradeDelivery.setUserId(trade.getUserId());
            tradeDelivery.setShopId(trade.getShopId());
            tradeDelivery.setOperatorId(dto.getJwtUserId());
            tradeDelivery.setOperatorName(dto.getJwtUserName());
            tradeDelivery.setDeliveryTime(LocalDateTime.now());
            tradeDeliveryRepository.save(tradeDelivery);
        }
        //修改订单状态
        trade.setTradeState(TradeStateEnum.待收货.getCode());
        tradeRepository.saveOrUpdate(trade);
    }

    @Override
    public void addTakeGoodsCodeCheck(H5MerchTradeDeliveryDTO.takeGoodsCodeCheckDTO dto) {
        //查询订单,判断订单状态
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if(ObjectUtils.isEmpty(trade) ){
            throw new BusinessException("无订单信息");
        }else if(!trade.getTradeState().equals(TradeStateEnum.待收货.getCode())){
            throw new BusinessException("不是待取货状态");
        }
        //判断自提码
        if(!dto.getTakeGoodsCode().equals(trade.getTakeGoodsCode())){
            throw new BusinessException("自提码不正确");
        }

        trade.setTradeState(TradeStateEnum.已完成.getCode());
        trade.setRecvTime(LocalDateTime.now());
        tradeRepository.saveOrUpdate(trade);
    }

    @Override
    public List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(H5MerchTradeDeliveryDTO.IdDTO dto) {
       return commonLogisticsCompanyRpc.listShopLogisticsCompany(dto.getJwtShopId());
    }
}
