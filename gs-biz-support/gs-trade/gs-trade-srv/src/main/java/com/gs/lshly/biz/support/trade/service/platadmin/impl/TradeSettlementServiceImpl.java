package com.gs.lshly.biz.support.trade.service.platadmin.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.mapper.TradeSettlementMapper;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeSettlementService;
import com.gs.lshly.common.enums.SettlementEnum;
import com.gs.lshly.common.enums.TradePayTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.SettleNoUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.lakala.boss.api.config.MerchantBaseEnum;
import com.lakala.boss.api.entity.request.WithdrawalCommitRequest;
import com.lakala.boss.api.entity.response.WithdrawalCommitResponse;
import com.lakala.boss.api.utils.BossClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2020-11-30
*/
@Slf4j
@Component
public class TradeSettlementServiceImpl implements ITradeSettlementService {

    @Autowired
    private ITradeSettlementRepository iTradeSettlementRepository;

    @Autowired
    private ITradeSettlementDetailRepository iTradeSettlementDetailRepository;

    @Autowired
    private ITradeRepository iTradeRepository;

    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;

    @Autowired
    private ITradeRightsGoodsRepository iTradeRightsGoodsRepository;

    @Autowired
    private ILklInteractionLogRepository iLklInteractionLogRepository;

    @Autowired
    private TradeSettlementMapper tradeSettlementMapper;

    @Override
    public PageData<TradeSettlementVO.ListVO> pageData(TradeSettlementQTO.settlementList qto) {
        QueryWrapper<TradeSettlement> wrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotEmpty(qto.getBillStartTime()) && ObjectUtils.isNotEmpty(qto.getBillEndTime())){
            wrapper.between("cdate",qto.getBillStartTime(),qto.getBillEndTime());
        }
        if (ObjectUtils.isNotEmpty(qto.getShopId()) ){
            wrapper.eq("shop_id",qto.getShopId());
        }
        if (ObjectUtils.isNotEmpty(qto.getSettlementState()) ){
            wrapper.eq("settlement_state", qto.getSettlementState());
        }
        wrapper.orderByDesc("cdate");
        IPage<TradeSettlement> page = MybatisPlusUtil.pager(qto);
        iTradeSettlementRepository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, TradeSettlementVO.ListVO.class, page);
    }

    @Override
    public void addTradeSettlement(TradeSettlementDTO.ETO eto) {
        TradeSettlement tradeSettlement = new TradeSettlement();
        BeanUtils.copyProperties(eto, tradeSettlement);
        iTradeSettlementRepository.save(tradeSettlement);
    }


    @Override
    public TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto) {
        TradeSettlement tradeSettlement = iTradeSettlementRepository.getById(dto.getId());
        TradeSettlementVO.DetailVO detailVo = new TradeSettlementVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeSettlement)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeSettlement, detailVo);
        return detailVo;
    }

    @Override
    public boolean summaryOrderData() {
        try {
            //获取所有生效的店铺
            List<Map<String, String>> shopList = iTradeSettlementRepository.queryEffectiveShop();
            Date lastMonthDate = DateUtils.addMonth(new Date(), -1);
            LocalDateTime startDate = DateUtils.firstDayOfMonth(lastMonthDate);
            LocalDateTime endDate = DateUtils.firstDayOfMonth(new Date());
            for (Map<String, String> shopMap : shopList) {
                String shopId = shopMap.get("id");
                String shopName = shopMap.get("shop_name");
                //将该商铺上月结算汇总数据置为无效，重新进行结算汇总
                updateSettleData(shopId,startDate,endDate);
                //普通结算明细list
                List<TradeSettlementDetail> regularDetailList=new ArrayList<>();
                //售后结算明细list
                List<TradeSettlementDetail> afterSaleDetailList=new ArrayList<>();
                //结算明细list(所有结算明细记录)
                List<TradeSettlementDetail> detailList;
                //佣金
                BigDecimal commission = BigDecimal.ZERO;
                //获取店铺上月交易数据汇总的各项金额
                Map<String, Object> tabulateMap = iTradeSettlementRepository.queryTabulateData(shopId, startDate, endDate);
                //获取店铺上月退款数据汇总的退款金额
                BigDecimal discountAmount  = iTradeSettlementRepository.querySumDiscountAmount(shopId, startDate, endDate);
                //封装店铺上月汇总的结算主数据
                TradeSettlement tradeSettlement = packSettleData(shopId, shopName, startDate, endDate,discountAmount,tabulateMap);
                //获取店铺上月的交易数据(明细)
                QueryWrapper<Trade> tradeWrapper = new QueryWrapper<>();
                tradeWrapper.eq("shop_id", shopId)
                        .eq("trade_state", TradeStateEnum.已完成.getCode())
                        .ge("recv_time", startDate)
                        .lt("recv_time", endDate);
                List<Trade> tradeList = iTradeRepository.list(tradeWrapper);
                //对店铺结算期内产生的交易进行结算(普通结算)
                for (Trade trade : tradeList) {
                    QueryWrapper<TradeGoods> iTradeGoodsWrapper = new QueryWrapper<>();
                    iTradeGoodsWrapper.eq("trade_id", trade.getId());
                    //获取交易商品明细
                    List<TradeGoods> tradeGoodsList = iTradeGoodsRepository.list(iTradeGoodsWrapper);
                    for (TradeGoods tradeGoods : tradeGoodsList) {
                        TradeSettlementDetail tradeSettlementDetail;
                        BigDecimal goodsCommission=BigDecimal.ZERO;
                        //这里通过调用其他职能中心的RPC服务获取到费率，参数为商品id和店铺id，返回值为商品佣金费率
                        //现在暂时没有RPC服务，设置默认佣金费率为0.1
                        BigDecimal rate = new BigDecimal("0.1");
                        //查询商品在结算期内有无退款记录，如果无，计算佣金.
                        BigDecimal refundAmount=iTradeSettlementRepository.queryGoodsRefundAmount(shopId,trade.getId(),tradeGoods.getId());
                        if(BigDecimal.ZERO.compareTo(refundAmount)==0){
                            //计算单件交易商品的佣金
                            goodsCommission = tradeGoods.getPayAmount().multiply(rate);
                            //总佣金加上单件商品佣金
                            commission = commission.add(goodsCommission);
                        }
                        tradeSettlementDetail=packRegularSettleDetail(shopName,goodsCommission,refundAmount,trade,tradeGoods);
                        regularDetailList.add(tradeSettlementDetail);
                    }
                }
                //对店铺结算期外产生的交易，结算期内产生的退款进行结算(售后结算)
                List<TradeRights> tradeRightsList = iTradeSettlementRepository.queryRightsData(shopId, startDate, endDate);
                for(TradeRights tradeRights:tradeRightsList){
                    TradeSettlementDetail tradeSettlementDetail;
                    QueryWrapper<TradeRightsGoods> rightsGoodsWrapper = new QueryWrapper<>();
                    rightsGoodsWrapper.eq("shop_id", shopId)
                            .eq("trade_id", tradeRights.getTradeId())
                            .eq("rights_id", tradeRights.getId());
                    List<TradeRightsGoods> rightsGoodsList = iTradeRightsGoodsRepository.list(rightsGoodsWrapper);

                    for(TradeRightsGoods tradeRightsGoods:rightsGoodsList){
                        BigDecimal rate = new BigDecimal("0.1");
                        BigDecimal goodsCommission=tradeRightsGoods.getRefundAmount().multiply(rate);
                        commission=commission.subtract(goodsCommission);
                        BigDecimal refundAmount=tradeRightsGoods.getRefundAmount();
                        tradeSettlementDetail=packAfterSaleSettleDetail(shopName,goodsCommission,refundAmount,tradeRights,tradeRightsGoods);
                        afterSaleDetailList.add(tradeSettlementDetail);
                    }
                }

                tradeSettlement.setCommission(commission);
                iTradeSettlementRepository.save(tradeSettlement);
                detailList=Stream.of(regularDetailList,afterSaleDetailList).flatMap(x -> x.stream()).collect(Collectors.toList());
                for(TradeSettlementDetail detail:detailList){
                    detail.setSettlementId(tradeSettlement.getId());
                }
                iTradeSettlementDetailRepository.saveBatch(detailList);
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String settleConfirmation(String trade_code) {
        QueryWrapper<TradeSettlement> wrapper=new QueryWrapper<>();
        wrapper.eq("trade_code",trade_code);
        List<TradeSettlement> tradeSettlementList=iTradeSettlementRepository.list(wrapper);
        if(tradeSettlementList.size()!=1){
            return "账单编号数据不为一条,请联系管理员检查数据正确性";
        }
        TradeSettlement tradeSettlement=tradeSettlementList.get(0);
        String wdcAplAmt=tradeSettlement.getSettlementAmount().toString();
        if(BigDecimal.ZERO.compareTo(new BigDecimal(wdcAplAmt))==0){
            //结算金额为0，直接改结算表状态，无需调用拉卡拉接口进行提现操作
            tradeSettlement.setSettlementState(SettlementEnum.已结算.getCode());
            iTradeSettlementRepository.updateById(tradeSettlement);
            return "结算清分业务已完成";
        }else if(BigDecimal.ZERO.compareTo(new BigDecimal(wdcAplAmt)) > 0){
            return "结算金额小于0,不可进行结算";
        }
        WithdrawalCommitRequest request = new WithdrawalCommitRequest();
        //平台维护在拉卡拉的唯一标识
        request.setMerchantId(MerchantBaseEnum.merchant_hly_Id.getValue());
        //入驻商家维护在拉卡拉的唯一标识，此处到时候根据店铺id调用商家RPC服务获取subMerchantId
        request.setSubMerchantId(MerchantBaseEnum.merchant_hly_Id.getValue());
        //提现金额
        request.setWdcAplAmt(wdcAplAmt);
        //记录请求日志
        String entityId=saveLog(request,null,1);
        request.setRequestId(entityId);
        try{
            BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(), MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());
            WithdrawalCommitResponse response = client.execute(request);
            saveLog(request,response,2);
            if (response.isSuccess()) {
                tradeSettlement.setSettlementState(SettlementEnum.已结算.getCode());
                iTradeSettlementRepository.updateById(tradeSettlement);
                log.info("Settle confirmation success");
                return "结算清分业务已受理成功";
            } else {
                log.info("Settle confirmation fail:"+response.getReturnMessage());
                return response.getReturnMessage();

            }
        }catch(Exception e){
            e.printStackTrace();
            return "未知错误,请联系系统管理员";
        }
    }

    private TradeSettlement packSettleData(String shopId,String shopName,LocalDateTime startTime,LocalDateTime endTime,BigDecimal discountAmount,Map<String,Object> tabulateMap){
        TradeSettlement tradeSettlement=new TradeSettlement();
        tradeSettlement.setShopId(shopId);
        tradeSettlement.setShopName(shopName);
        tradeSettlement.setTradeCode(SettleNoUtil.getSettleNo());
        //订单数量
        tradeSettlement.setQuantity(((Long)tabulateMap.get("count")).intValue());
        //商品总金额
        tradeSettlement.setSalePrice(new BigDecimal(tabulateMap.get("sumGoodsAmount").toString()));
        //运费总金额
        tradeSettlement.setDeliveryAmount(new BigDecimal(tabulateMap.get("sumDeliveryAmount").toString()));
        //退款总金额
        tradeSettlement.setTradeAmount(discountAmount);
        //交易总金额
        tradeSettlement.setSettlementAmount(new BigDecimal(tabulateMap.get("sumTradeAmount").toString()));
        tradeSettlement.setBillStartTime(startTime);
        tradeSettlement.setBillEndTime(endTime);
        tradeSettlement.setSettlementState(SettlementEnum.未结算.getCode());
        tradeSettlement.setFlag(false);
        return tradeSettlement;
    }



    private TradeSettlementDetail packRegularSettleDetail(String shopName,BigDecimal goodsCommission,BigDecimal refundAmount,Trade trade,TradeGoods tradeGoods){
        TradeSettlementDetail settlementDetail=new TradeSettlementDetail();
        settlementDetail.setTradeCode(trade.getId());
        settlementDetail.setShopId(tradeGoods.getShopId());
        settlementDetail.setShopName(shopName);
        settlementDetail.setGoodsId(tradeGoods.getGoodsId());
        settlementDetail.setGoodsName(tradeGoods.getGoodsName());
        settlementDetail.setGoodsNo(tradeGoods.getGoodsNo());
        //商品标题
        settlementDetail.setGoodsTitle(tradeGoods.getGoodsTitle());
        //商品价格
        settlementDetail.setSalePrice(tradeGoods.getSalePrice());
        //商品数量
        settlementDetail.setQuantity(tradeGoods.getQuantity());
        //商品货款
        settlementDetail.setCommodityMoney(tradeGoods.getSalePrice().multiply(new BigDecimal(tradeGoods.getQuantity())));
        //运费从交易表取运费金额
        settlementDetail.setDeliveryAmount(trade.getDeliveryAmount());
        //退款金额从退款表取退款金额
        settlementDetail.setTradeAmount(refundAmount);
        //佣金
        settlementDetail.setCommission(goodsCommission);
        //结算金额
        settlementDetail.setSettlementAmount(tradeGoods.getPayAmount());
        settlementDetail.setPayType(trade.getPayType());
        settlementDetail.setSettlementType(SettlementEnum.普通结算.getCode());
        settlementDetail.setSettleTime(trade.getRecvTime());
        settlementDetail.setPayTime(trade.getPayTime());
        settlementDetail.setFlag(false);
        return settlementDetail;
    }


    private TradeSettlementDetail packAfterSaleSettleDetail(String shopName,BigDecimal goodsCommission,BigDecimal refundAmount,TradeRights tradeRights,TradeRightsGoods tradeRightsGoods){
        TradeSettlementDetail settlementDetail=new TradeSettlementDetail();
        settlementDetail.setTradeCode(tradeRights.getTradeId());
        settlementDetail.setShopId(tradeRights.getShopId());
        settlementDetail.setShopName(shopName);
        settlementDetail.setGoodsId(tradeRightsGoods.getTradeGoodsId());
        TradeGoods tradeGoods = iTradeGoodsRepository.getById(tradeRightsGoods.getTradeGoodsId());
        settlementDetail.setGoodsNo(tradeGoods.getGoodsNo());
        settlementDetail.setGoodsName(tradeGoods.getGoodsName());
        //商品标题
        settlementDetail.setGoodsTitle(tradeGoods.getGoodsTitle());
        //商品价格
        settlementDetail.setSalePrice(tradeGoods.getSalePrice());
        //商品数量
        settlementDetail.setQuantity(tradeGoods.getQuantity());
        //商品货款
        settlementDetail.setCommodityMoney(tradeGoods.getSalePrice().multiply(new BigDecimal(tradeGoods.getQuantity())));
        //运费从交易表取运费金额
        settlementDetail.setDeliveryAmount(BigDecimal.ZERO);
        //退款金额从退款表取退款金额
        settlementDetail.setTradeAmount(refundAmount);
        //佣金
        settlementDetail.setCommission(goodsCommission);
        //结算金额
        settlementDetail.setSettlementAmount(tradeGoods.getPayAmount());
        settlementDetail.setPayType(TradePayTypeEnum.线下支付.getCode());
        settlementDetail.setSettlementType(SettlementEnum.售后结算.getCode());
        settlementDetail.setSettleTime(tradeRights.getCompleteTime());
        settlementDetail.setPayTime(tradeRights.getCompleteTime());
        settlementDetail.setFlag(false);
        return settlementDetail;
    }

    private String saveLog(WithdrawalCommitRequest withdrawalCommitRequest,WithdrawalCommitResponse withdrawalCommitResponse,int type){
        LklInteractionLog lklInteractionLog=new LklInteractionLog();
        lklInteractionLog.setInterfaceType(10);
        if(type==1){
            //请求日志记录
            lklInteractionLog.setLogType(10);
            lklInteractionLog.setContent(JSONObject.toJSONString(withdrawalCommitRequest));
            lklInteractionLog.setStatus(10);
            iLklInteractionLogRepository.save(lklInteractionLog);
        }else{
            //响应日志记录
            lklInteractionLog.setLogType(20);
            lklInteractionLog.setContent(JSONObject.toJSONString(withdrawalCommitResponse));
            lklInteractionLog.setRequestId(withdrawalCommitRequest.getRequestId());
            if(withdrawalCommitResponse.isSuccess()){
                lklInteractionLog.setStatus(10);
            }else{
                lklInteractionLog.setStatus(20);
            }
            iLklInteractionLogRepository.save(lklInteractionLog);
        }
        return lklInteractionLog.getId();
    }


    private void updateSettleData(String shopId,LocalDateTime startDate,LocalDateTime endDate){
        QueryWrapper<TradeSettlement> wrapper=new QueryWrapper<>();
        wrapper.eq("shop_id",shopId);
        wrapper.eq("bill_start_time",startDate);
        wrapper.eq("bill_end_time",endDate);
        List<TradeSettlement> tradeSettlementList=iTradeSettlementRepository.list(wrapper);
        for(TradeSettlement tradeSettlement:tradeSettlementList){
            TradeSettlementDetail tradeSettlementDetail=new TradeSettlementDetail();
            tradeSettlementDetail.setFlag(true);
            UpdateWrapper<TradeSettlementDetail> detailWrapper=new UpdateWrapper<>();
            detailWrapper.eq("settlement_id",tradeSettlement.getId()).set("flag",1);
            iTradeSettlementDetailRepository.update(detailWrapper);
            tradeSettlement.setFlag(true);
            iTradeSettlementRepository.updateById(tradeSettlement);
        }
    }

}
