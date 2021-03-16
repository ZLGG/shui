package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserIntegralService;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserIntegralDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsIntegralRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class BbbUserIntegralServiceImpl implements IBbbUserIntegralService {
    @Autowired
    private IUserIntegralRepository iUserIntegralRepository;

    @DubboReference
    private ISettingsIntegralRpc iSettingsIntegralRpc;

    @Override
    public void addUserTradeIntergral(PCBbbUserIntegralDTO.ETO eto) {
        UserIntegral userIntegral = new UserIntegral();
        userIntegral.setFromType(eto.getFromType());//积分来源[10=平台添加 20=订单 30=签到]
        userIntegral.setUserId(eto.getUserId());
        userIntegral.setFromId(eto.getFromId());
        //获取积分获取月份
        SettingsIntegralVO.DetailVO detailVO1 = iSettingsIntegralRpc.detailSettingsIntegral(eto);
        userIntegral.setQuantity(eto.getTradeAmount().multiply(new BigDecimal(detailVO1.getRate())).intValue());
        LocalDateTime now = LocalDateTime.now();//获取当前月份
        if (now.getMonthValue()<detailVO1.getMonthToExpire()){
            userIntegral.setEndDate(LocalDateTime.of(now.getYear(),detailVO1.getMonthToExpire(),1,0,0,0));
        }else {
            userIntegral.setEndDate(LocalDateTime.of(now.getYear()+1,detailVO1.getMonthToExpire(),1,0,0,0));
        }
        iUserIntegralRepository.save(userIntegral);
    }
}
