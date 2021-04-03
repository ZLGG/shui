package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.trade.enums.MerchantMarketActivityTypeEnum;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketActivityEnlistService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtActivityMerchantRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtActivityRpc;


@Component
public class PCMarketActivityEnlistServiceImpl implements IPCMarketActivityEnlistService {
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IMarketPtActivityRpc iMarketPtActivityRpc;
    @DubboReference
    private IPCMerchMarketPtActivityMerchantRpc ipcMerchMarketPtActivityMerchantRpc;
    @Override
    public PageData<MarketPtActivityVO.MerchantActivity> activityPageData(MarketPtActivityQTO.QTO qto) {
        List<MarketPtActivityVO.ListVO> list = iMarketPtActivityRpc.list();
        List<MarketPtActivityVO.MerchantActivity> merchantActivities = new ArrayList<>();
        for (MarketPtActivityVO.ListVO marketPtActivityVO:list){
            MarketPtActivityVO.MerchantActivity merchantActivity = new MarketPtActivityVO.MerchantActivity();
            BeanUtils.copyProperties(marketPtActivityVO,merchantActivity);
            merchantActivity.setActivityTime(marketPtActivityVO.getActivityStartTime().toString()+"~"+marketPtActivityVO.getActivityEndTime().toString());
            merchantActivity.setSignTime(marketPtActivityVO.getSignStartTime().toString()+"~"+marketPtActivityVO.getSignEndTime().toString());
            //查询店铺类型：
            Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Long signStartTime = marketPtActivityVO.getSignStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Long signEndTime = marketPtActivityVO.getSignEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            PCMerchMarketPtActivityMerchantDTO.isRecordDTO isRecordDTO = new PCMerchMarketPtActivityMerchantDTO.isRecordDTO(marketPtActivityVO.getId(), qto.getJwtMerchantId(), qto.getJwtShopId());
            //获取该活动在此商家此店铺有没有报名
            List<PCMerchMarketPtActivityMerchantVO.ListVO> listVOS = ipcMerchMarketPtActivityMerchantRpc.queryCountRecord(isRecordDTO);
            if (now>=signEndTime){
                //报名结束
                //是否报名
                if (listVOS.size()>0){
                    merchantActivity.setParticipation(MerchantMarketActivityTypeEnum.报名已结束已报名.getCode().toString());
                }else {
                    merchantActivity.setParticipation(MerchantMarketActivityTypeEnum.报名已结束未报名.getCode().toString());
                }
            }else {
                //参与
                //是否报名
                if (listVOS.size()>0){
                    merchantActivity.setParticipation(MerchantMarketActivityTypeEnum.可参与已报名.getCode().toString());
                }else {
                    merchantActivity.setParticipation(MerchantMarketActivityTypeEnum.可参与未报名.getCode().toString());
                }
            }
            //判断能不能参加
            if (signStartTime>now){
                merchantActivity.setParticipation(MerchantMarketActivityTypeEnum.未开始报名.getCode().toString());
            }
            ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(qto.getJwtShopId()));
            String shopType = String.valueOf(detailVO.getShopType());
            String tyeoCode = marketPtActivityVO.getTypeCode();
            String[] type = tyeoCode.split(",");
            List<String> typeList = Arrays.asList(type);
            if (!typeList.contains(shopType)){
                merchantActivity.setParticipation(MerchantMarketActivityTypeEnum.不可参与.getCode().toString());
            }
            merchantActivities.add(merchantActivity);
        }
        return new PageData<MarketPtActivityVO.MerchantActivity>(merchantActivities,qto.getPageNum(),qto.getPageSize(),merchantActivities.size());
    }

}
