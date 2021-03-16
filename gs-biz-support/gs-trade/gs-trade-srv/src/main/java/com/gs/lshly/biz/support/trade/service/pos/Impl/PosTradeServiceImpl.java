package com.gs.lshly.biz.support.trade.service.pos.Impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradePosLog;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.entity.TradeRightsPosLog;
import com.gs.lshly.biz.support.trade.repository.ITradePosLogRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsPosLogRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeDeliveryService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl.PCMerchTradeDeliveryServiceImpl;
import com.gs.lshly.biz.support.trade.service.pos.IPosTradeService;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.TradeRightsStateEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.pos.dto.*;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.HttpsUtil;
import com.gs.lshly.common.utils.MapToolsUtil;
import com.gs.lshly.common.utils.RSAUtil;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.lakala.boss.api.utils.BossClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
@Slf4j
public class PosTradeServiceImpl implements IPosTradeService {

    private String pubKey = "";

    private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALZrfh8iVLkjzM40rdSngIRqdxLxYBCeB32SL1r0/Ee6rNvWyBnltY/P3LxL0plfzKBt9EmdQPjR0lanOG6SC+BlBNVcL6wmRWMIsMd9G1RsL+pKurXbN0IF9899Ucvnme+SptuS1iBIbT6Z5wogu8gupLSPO9jefsyMDZrZBSpXAgMBAAECf002t8+pFJns/gEru8S4F49RL1MdAUToqWG674OvTakjZTrHPlIeT5LDF0VpTZx/THo1Q6JgFpsnAeuC3H7SNAsPzEiZnas7oyMR6G6/pE29cStZ8gE4KNqiHH5ZdzYUhDKcogJdaO4Q4vyZ+wFXvCcD4lwkwnzUnVSuMxY2XCECQQDqkbk33/JLeEtwZodBVmMOZyQrrcH6ICR+NCuSMWhoh7XK5t7X9CXIAc7sA7Ych4hrXrChf6r0IXVHBpo+8YJxAkEAxxYP9LtjLW0+7+E1U7DhP/S90iPs4ApfmkYIs1+beUnkJNrGfhL9KE59RJkhQzAOLHWzuiOVOhHtTMB9B65NRwJAILBQHH5D+Mp50N8o6C0OvtoWy1N6nc3O6BuDGutxvmdzKSSFV5j5jubZnEBkJ5OnqjbW7JqrCzyCaHR+GFl1sQJBAIdgBTGRSntVUfqj1TM3j7OBpKIEM8qUwzWQcdInLP1otW4qJdcM6oBB/wRqbCfXwYuR7cig8ZQvJIGQUnSqnXkCQQCY8V1cP/pjtn0e0NbpiUhBTrqX0u841D1DHYkgn3Git50DqF0cBp6/DPUaf1U2oyuOdfEjaENr9zrdTOi26jAx";

    private String url="https://openapi-int.gshldj.com";

    private  String accessKeyId="longliangdj";

    private  String accessKeySecret="123456";

    @Autowired
    private ITradePosLogRepository iTradePosLogRepository;
    @Autowired
    private ITradeRightsPosLogRepository iTradeRightsPosLogRepository;
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeRightsRepository iTradeRightsRepository;
    @Autowired
    private IPCMerchTradeDeliveryService ipcMerchTradeDeliveryService;
    @DubboReference
    private ICommonLogisticsCompanyRpc iCommonLogisticsCompanyRpc;

    @Override
    public ResponseData<Void> pullTrade(PosRSPurchaseSyncRequestDTO dto, String param) {

        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("传值错误");
        }
        String requestUri = "/dpos/v3/master/purchase/sync";
        String timestamp = String.valueOf(System.currentTimeMillis());
        dto.setShop(param);
        String body = JSONObject.toJSONString(dto);
        System.out.println(body);
        String sign = requestUri + body + timestamp + accessKeySecret;
        String response = null;
        try {
            String signature = MD5(sign, "UTF-8").toLowerCase();
            String ur = url + requestUri + "?accessKeyId=" + accessKeyId + "&timestamp=" + timestamp + "&signature=" + signature + "&signMethod=MD5";
            System.out.println("url=~~~~~~~~~_____" + ur);
            response = HttpsUtil.doPost(ur, body);
            System.out.println("response："+response);
            log.info("doPost response:" + response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (ObjectUtils.isEmpty(response)) {
            throw new BusinessException("接口调用失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (null != jsonObject) {
            if (jsonObject.getObject("retCode", Integer.class) != 0) {
                throw new BusinessException("接口调用失败");
            }
        }
        return ResponseData.success(MsgConst.SUBMIT_SUCCESS);

    }

    @Override
    public void orderState(PosTradeStateRequestDTO.DTO posTradeStateRequestDTO) {
        check(posTradeStateRequestDTO);
        TradePosLog tradePosLog = new TradePosLog();
        BeanUtils.copyProperties(posTradeStateRequestDTO,tradePosLog);
        tradePosLog.setTimestamp(posTradeStateRequestDTO.getTimestamp().toString());
        iTradePosLogRepository.save(tradePosLog);
        Trade trade = iTradeRepository.getById(posTradeStateRequestDTO.getPosOrderNo());
        if (ObjectUtils.isEmpty(trade)){
            throw new BusinessException("没有查询到订单");
        }
        if (posTradeStateRequestDTO.getPosOrderState().equals("已取消")) {
            trade.setTradeState(TradeStateEnum.已取消.getCode());
        }else {
            trade.setTradeState(TradeStateEnum.待收货.getCode());
            CommonLogisticsCompanyVO.DetailVO logisticsName = iCommonLogisticsCompanyRpc.getLogisticsName(posTradeStateRequestDTO.getDeliveryCompany());
            PCMerchTradeDeliveryDTO.deliveryDTO deliveryDTO = new PCMerchTradeDeliveryDTO.deliveryDTO();
            deliveryDTO.setTradeId(trade.getId());
            if (ObjectUtils.isNotEmpty(logisticsName)){
                deliveryDTO.setLogisticsId(logisticsName.getId());
            }
            deliveryDTO.setLogisticsNumber(posTradeStateRequestDTO.getDeliveryCode());
            ipcMerchTradeDeliveryService.addTradeDelivery(deliveryDTO);
            iTradeRepository.updateById(trade);
        }

    }

    @Override
    public void orderRight(PosTradeRightRequestDTO.DTO posTradeRightRequestDTO) {
        checkRights(posTradeRightRequestDTO);
        TradeRightsPosLog tradePosLog = new TradeRightsPosLog();
        BeanUtils.copyProperties(posTradeRightRequestDTO,tradePosLog);
        tradePosLog.setTimestamp(posTradeRightRequestDTO.getTimestamp().toString());
        iTradeRightsPosLogRepository.save(tradePosLog);
        TradeRights byId = iTradeRightsRepository.getById(posTradeRightRequestDTO.getPosOrderRefundNo());
        if (ObjectUtils.isEmpty(byId)){
            throw new BusinessException("没有查询到售后订单");
        }
        if (posTradeRightRequestDTO.getPosProcessResult()==10){
            //同意退货
            byId.setState(TradeRightsStateEnum.通过.getCode());
        }else if (posTradeRightRequestDTO.getPosProcessResult()==20){
            byId.setState(TradeRightsStateEnum.驳回.getCode());
        }else if (posTradeRightRequestDTO.getPosProcessResult()==30){
            if (byId.getRightsType()==TradeRightsTypeEnum.仅退款.getCode()||byId.getRightsType()==TradeRightsTypeEnum.取消订单.getCode()){
                byId.setState(TradeRightsStateEnum.等待退款.getCode());
            }
            if (byId.getRightsType()==TradeRightsTypeEnum.换货.getCode()||byId.getRightsType()==TradeRightsTypeEnum.退货退款.getCode()){
                byId.setState(TradeRightsStateEnum.收到退货.getCode());
            }
        }
        byId.setRefundRemarks(new StringBuffer(byId.getRefundRemarks()!=null?byId.getRefundRemarks():"").append(",POS店处理意见:").append(posTradeRightRequestDTO.getPosOpinion()!=null?posTradeRightRequestDTO.getPosOpinion():"").toString());
        iTradeRightsRepository.updateById(byId);
    }

    @Override
    public void addOrderRight(PosTradeOOnlineOrderRequestDTO.DTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("传值错误");
        }
        String requestUri = "/dpos/v3/onlineorderr/saveNew";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String body = JSONObject.toJSONString(dto);
        System.out.println(body);
        String sign = requestUri + body + timestamp + accessKeySecret;
        String response = null;
        try {
            String signature = MD5(sign, "UTF-8").toLowerCase();
            String ur = url + requestUri + "?accessKeyId=" + accessKeyId + "&timestamp=" + timestamp + "&signature=" + signature + "&signMethod=MD5" + "&shop=" + dto.getShop() + "&platformId=" + dto.getPlatformId();
            System.out.println("url=~~~~~~~~~_____" + ur);
            response = HttpsUtil.doPost(ur, body);
            System.out.println("response："+response);
            log.info("doPost response:" + response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (ObjectUtils.isEmpty(response)) {
            throw new BusinessException("接口调用失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (null != jsonObject) {
            if (jsonObject.getObject("retCode", Integer.class) != 0) {
                throw new BusinessException("接口调用失败");
            }
        }
    }

    @Override
    public void FinishOrderRight(PosFinishAndCancelRequestDTO.DTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("传值错误");
        }
        String requestUri = "/dpos/v3/onlineorderr/finish";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String body = JSONObject.toJSONString(dto);
        System.out.println(body);
        String sign = requestUri + body + timestamp + accessKeySecret;
        String response = null;
        try {
            String signature = MD5(sign, "UTF-8").toLowerCase();
            String ur = url + requestUri + "?accessKeyId=" + accessKeyId + "&timestamp=" + timestamp + "&signature=" + signature + "&signMethod=MD5" + "&shop=" + dto.getShop() + "&platformId=" + dto.getPlatformId() + "&number=" + dto.getNumber();
            System.out.println("url=~~~~~~~~~_____" + ur);
            response = HttpsUtil.doPost(ur, body);
            System.out.println("response："+response);
            log.info("doPost response:" + response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (ObjectUtils.isEmpty(response)) {
            throw new BusinessException("接口调用失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (null != jsonObject) {
            if (jsonObject.getObject("retCode", Integer.class) != 0) {
                throw new BusinessException("接口调用失败");
            }
        }
    }

    @Override
    public void CancelOrderRight(PosFinishAndCancelRequestDTO.DTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("传值错误");
        }
        String requestUri = "/dpos/v3/onlineorderr/cancel";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String body = JSONObject.toJSONString(dto);
        System.out.println(body);
        String sign = requestUri + body + timestamp + accessKeySecret;
        String response = null;
        try {
            String signature = MD5(sign, "UTF-8").toLowerCase();
            String ur = url + requestUri + "?accessKeyId=" + accessKeyId + "&timestamp=" + timestamp + "&signature=" + signature + "&signMethod=MD5" + "&shop=" + dto.getShop() + "&platformId=" + dto.getPlatformId() + "&number=" + dto.getNumber();
            System.out.println("url=~~~~~~~~~_____" + ur);
            response = HttpsUtil.doPost(ur, body);
            System.out.println("response："+response);
            log.info("doPost response:" + response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (ObjectUtils.isEmpty(response)) {
            throw new BusinessException("接口调用失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (null != jsonObject) {
            if (jsonObject.getObject("retCode", Integer.class) != 0) {
                throw new BusinessException("接口调用失败");
            }
        }
    }

    @Override
    public void addTrade(PosTradeODeliverOrderRequestDTO.DTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("传值错误");
        }
        String requestUri = "/dpos/v3/onlineorder/saveNew";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String body = JSONObject.toJSONString(dto);
        System.out.println(body);
        String sign = requestUri + body + timestamp + accessKeySecret;
        String response = null;
        try {
            String signature = MD5(sign, "UTF-8").toLowerCase();
            String ur = url + requestUri + "?accessKeyId=" + accessKeyId + "&timestamp=" + timestamp + "&signature=" + signature + "&signMethod=MD5" + "&shop=" + dto.getShop() + "&platformId=" + dto.getPlatformId();
            System.out.println("url=~~~~~~~~~_____" + ur);
            response = HttpsUtil.doPost(ur, body);
            System.out.println("response："+response);
            log.info("doPost response:" + response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (ObjectUtils.isEmpty(response)) {
            throw new BusinessException("接口调用失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (null != jsonObject) {
            if (jsonObject.getObject("retCode", Integer.class) != 0) {
                throw new BusinessException("接口调用失败");
            }
        }
    }

    @Override
    public void finishTrade(PosFinishAndCancelTradeRequestDTO.DTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("传值错误");
        }
        String requestUri = "/dpos/v3/onlineorder/finish";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String body = JSONObject.toJSONString(dto);
        System.out.println(body);
        String sign = requestUri + body + timestamp + accessKeySecret;
        String response = null;
        try {
            String signature = MD5(sign, "UTF-8").toLowerCase();
            String ur = url + requestUri + "?accessKeyId=" + accessKeyId + "&timestamp=" + timestamp + "&signature=" + signature + "&signMethod=MD5" + "&shop=" + dto.getShop() + "&platformId=" + dto.getPlatformId()+ "&number=" + dto.getNumber()+ "&orderType=" + dto.getOrderType();
            System.out.println("url=~~~~~~~~~_____" + ur);
            response = HttpsUtil.doPost(ur, body);
            System.out.println("response："+response);
            log.info("doPost response:" + response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (ObjectUtils.isEmpty(response)) {
            throw new BusinessException("接口调用失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (null != jsonObject) {
            if (jsonObject.getObject("retCode", Integer.class) != 0) {
                throw new BusinessException("接口调用失败");
            }
        }
    }

    @Override
    public void cancelTrade(PosFinishAndCancelTradeRequestDTO.DTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("传值错误");
        }
        String requestUri = "/dpos/v3/onlineorder/cancel";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String param = JSONObject.toJSONString(dto);
        System.out.println(param);
        String sign = requestUri+param+timestamp + accessKeySecret;
        String response = null;
        try {
            String signature = MD5(sign, "UTF-8").toLowerCase();
            String ur = url + requestUri + "?accessKeyId=" + accessKeyId + "&timestamp=" + timestamp + "&signature=" + signature + "&signMethod=MD5" + "&shop=" + dto.getShop() + "&platformId=" + dto.getPlatformId()+ "&number=" + dto.getNumber()+ "&orderType=" + dto.getOrderType();
            System.out.println("url=~~~~~~~~~_____" + ur);
            response = HttpsUtil.doPost(ur,param);
            System.out.println("response："+response);
            log.info("doPost response:" + response);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (ObjectUtils.isEmpty(response)) {
            throw new BusinessException("接口调用失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (null != jsonObject) {
            if (jsonObject.getObject("retCode", Integer.class) != 0) {
                throw new BusinessException("接口调用失败");
            }
        }
    }

    private void check(PosTradeStateRequestDTO.DTO posTradeStateRequestDTO) {
        if (ObjectUtils.isEmpty(posTradeStateRequestDTO)){
            throw new BusinessException("参数为空");
        }
        if (ObjectUtils.isEmpty(posTradeStateRequestDTO.getTimestamp())){
            throw new BusinessException("时间戳错误");
        }
        if (ObjectUtils.isEmpty(posTradeStateRequestDTO.getNonce()) && posTradeStateRequestDTO.getNonce().length()!=6){
            throw new BusinessException("随机数错误");
        }
        if (ObjectUtils.isEmpty(posTradeStateRequestDTO.getPosCode())){
            throw new BusinessException("POS店编号为空");
        }
        if (ObjectUtils.isEmpty(posTradeStateRequestDTO.getPosOrderNo())){
            throw new BusinessException("订单编号为空");
        }
        if (ObjectUtils.isEmpty(posTradeStateRequestDTO.getPosOrderState())){
            throw new BusinessException("订单状态为空");
        }
    }
    private void checkRights(PosTradeRightRequestDTO.DTO  dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空");
        }
        if (ObjectUtils.isEmpty(dto.getTimestamp())){
            throw new BusinessException("时间戳错误");
        }
        if (ObjectUtils.isEmpty(dto.getNonce()) && dto.getNonce().length()!=6){
            throw new BusinessException("随机数错误");
        }
        if (ObjectUtils.isEmpty(dto.getPosCode())){
            throw new BusinessException("POS店编号为空");
        }
        if (ObjectUtils.isEmpty(dto.getPosOrderNo())){
            throw new BusinessException("订单编号为空");
        }
        if (ObjectUtils.isEmpty(dto.getPosOrderRefundNo())){
            throw new BusinessException("订单退货申请单号为空");
        }
        if (ObjectUtils.isEmpty(dto.getPosProcessResult())){
            throw new BusinessException("POS店处理结果为空");
        }

    }

    public static void main(String[] args) {
        PosTradeServiceImpl service = new PosTradeServiceImpl();
        service.pullTrade(new PosRSPurchaseSyncRequestDTO(), "dpos1021");
    }

    /**
     * MD5加密
     * @param str 内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }
}
