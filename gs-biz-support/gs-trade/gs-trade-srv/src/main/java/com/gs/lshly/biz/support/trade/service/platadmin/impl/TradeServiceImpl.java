package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeService;
import com.gs.lshly.common.enums.TradeDeliveryTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonUserRpc;
import com.lakala.boss.api.common.Common;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
public class TradeServiceImpl implements ITradeService {

    private final ITradeRepository tradeRepository;
    private final ITradeGoodsRepository tradeGoodsRepository;
    private final ITradeDeliveryRepository tradeDeliveryRepository;

    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private ICommonUserRpc commonUserRpc;
    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;

    public TradeServiceImpl(ITradeRepository tradeRepository, ITradeGoodsRepository tradeGoodsRepository, ITradeDeliveryRepository tradeDeliveryRepository) {
        this.tradeRepository = tradeRepository;
        this.tradeGoodsRepository = tradeGoodsRepository;
        this.tradeDeliveryRepository = tradeDeliveryRepository;
    }

    @Override
    public PageData<TradeListVO.tradeVO> tradeListPageData(TradeQTO.TradeList qto) {
        QueryWrapper<TradeQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("1","1"));
        if(ObjectUtils.isNotEmpty(qto.getCreateTime())){
            wrapper.and(i -> i.eq("",qto.getCreateTime()));
        }
        if(StringUtils.isNotBlank(qto.getTradeCode())){
            wrapper.and(i -> i.eq("t.`trade_code`",qto.getTradeCode()));
        }
        if(StringUtils.isNotBlank(qto.getRecvPersonName())){
            wrapper.and(i -> i.eq("t.`recv_person_name`",qto.getRecvPersonName()));
        }
        if(StringUtils.isNotBlank(qto.getRecvPhone())){
            wrapper.and(i -> i.eq("t.`recv_phone`",qto.getRecvPhone()));
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        if (ObjectUtils.isNotEmpty(qto.getSourceType())){
            wrapper.and(i->i.eq("t.`source_type`",qto.getSourceType()));//2b2c来源
        }
        if(ObjectUtils.isNotEmpty(qto.getDeliveryType())){
            wrapper.and(i -> i.eq("t.`delivery_type`",qto.getDeliveryType()));
        }
        if(StringUtils.isNotBlank(qto.getUserName())){
            CommonUserVO.DetailVO userDetailVO = commonUserRpc.detailsByUserName(qto.getUserName());
            if(ObjectUtils.isNotEmpty(userDetailVO) && StringUtils.isNotBlank(userDetailVO.getId())){
                //查询用户id
                wrapper.and(i -> i.eq("t.`user_id`",userDetailVO.getId()));
            }
        }

        wrapper.orderByDesc("cdate");

        IPage<TradeListVO.tradeVO> page = MybatisPlusUtil.pager(qto);

        tradeRepository.selectTradePage(page,wrapper);

        List<TradeListVO.tradeVO> voList = new ArrayList<>();
        for(TradeListVO.tradeVO tradeVO : page.getRecords()){
            //查询店铺信息
//            CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(tradeVO.getShopId());
//            tradeVO.setShopName(simpleVO.getShopName());
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            if(tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())){
                tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
            }
            voList.add(tradeVO);
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public TradeListVO.tradeVO detail(TradeDTO.IdDTO dto) {
        TradeListVO.tradeVO tradeVO = new TradeListVO.tradeVO();
        Trade trade = tradeRepository.getById(dto.getId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单数据");
        }
        BeanUtils.copyProperties(trade, tradeVO);

        //填充商家信息
        fillShop(tradeVO);
        fillTradeVO(tradeVO);
        if(tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())){
            tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
        }
        //查询物流信息
        if(tradeVO.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode()) &&
                (tradeVO.getTradeState().equals(TradeStateEnum.待收货.getCode()) ||
                        tradeVO.getTradeState().equals(TradeStateEnum.已完成.getCode()))){//快递配送/已发货/已收货
            //填充物流信息
            fillLogisticsCompany(tradeVO);
        }
        //填充用户信息
        fillUserInfo(tradeVO);

        return tradeVO;
    }

    private void fillUserInfo(TradeListVO.tradeVO tradeVO) {
        CommonUserVO.DetailVO innerUserInfoVO = commonUserRpc.details(tradeVO.getUserId());
        if(ObjectUtils.isNotEmpty(innerUserInfoVO)){
            tradeVO.setUserName(innerUserInfoVO.getUserName());
        }
    }

    private void fillShop(TradeListVO.tradeVO tradeVO) {
        CommonShopVO.SimpleVO innerDetailVO = commonShopRpc.shopDetails(tradeVO.getShopId());
        if(ObjectUtils.isNotEmpty(innerDetailVO)){
            tradeVO.setShopName(innerDetailVO.getShopName());
        }
    }

    private void fillLogisticsCompany(TradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeDelivery> tradeDeliveryQueryWrapper = new QueryWrapper<>();
        tradeDeliveryQueryWrapper.eq("trade_id", tradeVO.getId());
        TradeDelivery tradeDelivery = tradeDeliveryRepository.getOne(tradeDeliveryQueryWrapper);
        if(ObjectUtils.isNotEmpty(tradeDelivery)){
            CommonLogisticsCompanyVO.DetailVO logisticsDetailVO = commonLogisticsCompanyRpc.getLogisticsCompany(tradeDelivery.getLogisticsId());
            if(ObjectUtils.isNotEmpty(logisticsDetailVO)){
                tradeVO.setLogisticsNumber(tradeDelivery.getLogisticsNumber());
                tradeVO.setLogisticsCompanyCode(logisticsDetailVO.getCode());
                tradeVO.setLogisticsCompanyName(logisticsDetailVO.getName());
            }
        }
    }

    /**
     * 组装TradeVO、tradeGoodsVO数据
     * @param tradeVO
     */
    private void fillTradeVO(TradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeVO.getId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<TradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeGoods tradeGoods : tradeGoodsList){
            TradeListVO.TradeGoodsVO tradeGoodsVO = new TradeListVO.TradeGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
            tradeGoodsVO.setShopName(tradeVO.getShopName());
            tradeGoodsVOS.add(tradeGoodsVO);
        }
        tradeVO.setTradeGoodsVOS(tradeGoodsVOS);
    }

    @Override
    public List<TradeVO.ListVOExport> export(TradeQTO.IdListQTO qo) {
        QueryWrapper<Trade> wrapper =MybatisPlusUtil.query();

        if (ObjectUtils.isNotEmpty(qo.getIdList())){
            wrapper.and(i->i.in("id",qo.getIdList()));
        }
        wrapper.orderByDesc("cdate");


        List<Trade> list = tradeRepository.list(wrapper);

        List<TradeVO.ListVOExport> voList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            for (Trade trade:list){
                TradeVO.ListVOExport listVOExport = new TradeVO.ListVOExport();
                BeanUtils.copyProperties(trade,listVOExport);
                voList.add(listVOExport);
            }
        }
       return voList;
    }
}
