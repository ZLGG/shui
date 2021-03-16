package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserIntegralService;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserIntegralService;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserIntegralDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserIntegralDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsIntegralRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class BbbH5UserIntegralServiceImpl implements IBbbH5UserIntegralService {
    @Autowired
    private IUserIntegralRepository iUserIntegralRepository;

    @DubboReference
    private ISettingsIntegralRpc iSettingsIntegralRpc;

    @Override
    @Transactional
    public void addUserTradeIntergral(BbbH5UserIntegralDTO.ETO eto) {
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
