package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.mapper.UserIntegralMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserIntegralView;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserIntegralService;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserIntegralService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserIntegralDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserIntegralDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsIntegralRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class BbcUserIntegralServiceImpl implements IBbcUserIntegralService {
    @Autowired
    private IUserIntegralRepository iUserIntegralRepository;

    @Autowired
    private UserIntegralMapper userIntegralMapper;

    @DubboReference
    private ISettingsIntegralRpc iSettingsIntegralRpc;

    @Override
    @Transactional
    public void addUserTradeIntergral(BbcUserIntegralDTO.ETO eto) {
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

    @Override
    public BbcUserVO.UserIntegralVO integral(BaseDTO dto) {

        QueryWrapper<UserIntegral> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",dto.getJwtUserId());
        queryWrapper.groupBy("user_id");
        UserIntegralView sumCountView = userIntegralMapper.sumCount(queryWrapper);
        UserIntegralView sumCountPassView =  userIntegralMapper.sumCountPass(10,queryWrapper);
        BbcUserVO.UserIntegralVO userIntegralVO  = new BbcUserVO.UserIntegralVO();
        if(null != sumCountView){
            userIntegralVO.setOkIntegral(sumCountView.getQuantity());
        }
        if(null != sumCountPassView){
            userIntegralVO.setJpassIntegral(sumCountPassView.getQuantity());
        }
        return userIntegralVO;

    }
}
