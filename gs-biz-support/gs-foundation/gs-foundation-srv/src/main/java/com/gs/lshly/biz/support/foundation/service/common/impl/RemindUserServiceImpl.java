package com.gs.lshly.biz.support.foundation.service.common.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.RemindMerchant;
import com.gs.lshly.biz.support.foundation.entity.RemindPlat;
import com.gs.lshly.biz.support.foundation.entity.RemindUser;
import com.gs.lshly.biz.support.foundation.repository.IRemindUserRepository;
import com.gs.lshly.biz.support.foundation.service.common.IRemindUserService;
import com.gs.lshly.common.enums.RemindPlatEnum;
import com.gs.lshly.common.enums.RemindReadEnum;
import com.gs.lshly.common.enums.RemindStyleTypeEnum;
import com.gs.lshly.common.enums.RemindUserEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.dto.RemindUserDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.qto.RemindUserQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;
import com.gs.lshly.common.struct.common.vo.RemindUserVO;
import com.gs.lshly.middleware.wx.WxMaConfiguration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2021-02-05
*/
@Component
public class RemindUserServiceImpl implements IRemindUserService {

    @Autowired
    private IRemindUserRepository repository;

       @Override
    public PageData<RemindUserVO.ListVO> clientPageData(RemindUserQTO.QTO qto) {
        QueryWrapper<RemindUser> wrapper = new QueryWrapper<>();
        IPage<RemindUser> page = MybatisPlusUtil.pager(qto);
        wrapper.eq("accept_id",qto.getJwtUserId());
        wrapper.eq("state",RemindReadEnum.待读.getCode());
        wrapper.orderByDesc("cdate");
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, RemindUserVO.ListVO.class, page);
    }

    @Override
    public PageData<RemindUserVO.ListVO> platPageData(RemindUserQTO.QTO qto) {
        QueryWrapper<RemindUser> wrapper = new QueryWrapper<>();
        if(null != qto.getCType()){
            wrapper.eq("c_type",qto.getCType());
        }
        if(null !=qto.getState() || qto.getState() ==RemindReadEnum.全部.getCode()){
            wrapper.eq("state",qto.getState());
        }
        IPage<RemindUser> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, RemindUserVO.ListVO.class, page);
    }

    @Override
    public void deleteRemindUser(RemindUserDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editRemindUser(String id,RemindUserDTO.ETO eto) {
        RemindUser remindUser = new RemindUser();
        remindUser.setId(id);
        remindUser.setState(RemindReadEnum.已读.getCode());
        remindUser.setAccetId(eto.getJwtUserId());
        repository.updateById(remindUser);
    }

    @Override
    public void readRemindMerchant(String id, BaseDTO dto) {
        RemindUser remindUser = new RemindUser();
        remindUser.setId(id);
        remindUser.setState(RemindReadEnum.已读.getCode());
        remindUser.setAccetId(dto.getJwtUserId());
        repository.updateById(remindUser);
    }

    @Override
    public RemindUserVO.DetailVO detailRemindUser(RemindUserDTO.IdDTO dto) {
        RemindUser remindUser = repository.getById(dto.getId());
        RemindUserVO.DetailVO detailVo = new RemindUserVO.DetailVO();
        if(ObjectUtils.isEmpty(remindUser)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(remindUser, detailVo);
        return detailVo;
    }

    @Override
    public void addRemindUserForLogisticsStates2C(RemindUserDTO.LogisticsStatesExtDTO dto) {
        RemindUser remindUser = new RemindUser();
        remindUser.setCStyle(RemindStyleTypeEnum.微信消息.getCode());
        remindUser.setCType(RemindUserEnum.物流状态通知.getCode());
        remindUser.setContent(dto.getContent());
        remindUser.setTriggerId(dto.getTriggerId());
        remindUser.setTriggerSid(dto.getTriggerSid());
        remindUser.setState(RemindReadEnum.待读.getCode());
        repository.save(remindUser);
        //----------------------------------------微信推送---------------------------------------
        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        //跳转小程序页面路径
        //subscribeMessage.setPage("pages/index/index");
        //模板消息id
        subscribeMessage.setTemplateId("wP6_c90_f5efJUWS9zKGyf7D2M2tpkDZk5u4JcWlYwU");
        //给谁推送 用户的openid （可以调用根据code换openid接口)
        subscribeMessage.setToUser(dto.getOpenId());
        ArrayList<WxMaSubscribeMessage.Data> wxMaSubscribeData = new ArrayList<>();

        WxMaSubscribeMessage.Data data_field_1= new WxMaSubscribeMessage.Data();
        data_field_1.setName("time1");
        data_field_1.setValue(dto.getCdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        wxMaSubscribeData.add(data_field_1);

        WxMaSubscribeMessage.Data data_field_2= new WxMaSubscribeMessage.Data();
        data_field_2.setName("thing2");
        data_field_2.setValue(dto.getContent());
        wxMaSubscribeData.add(data_field_2);
        subscribeMessage.setData(wxMaSubscribeData);
        try {
            //获取微信小程序配置：
            final WxMaService wxService = WxMaConfiguration.getMaService(dto.getAppId());
            //进行推送
            wxService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRemindUserForLogisticsStates2B(RemindUserDTO.LogisticsStatesExtDTO dto) {
        RemindUser remindUser = new RemindUser();
        remindUser.setCStyle(RemindStyleTypeEnum.微信消息.getCode());
        remindUser.setCType(RemindUserEnum.物流状态通知.getCode());
        remindUser.setContent(dto.getContent());
        remindUser.setTriggerId(dto.getTriggerId());
        remindUser.setTriggerSid(dto.getTriggerSid());
        remindUser.setState(RemindReadEnum.待读.getCode());
        repository.save(remindUser);
        //----------------------------------------微信推送---------------------------------------
        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        //跳转小程序页面路径
        //subscribeMessage.setPage("pages/index/index");
        //模板消息id
        subscribeMessage.setTemplateId("wP6_c90_f5efJUWS9zKGyf7D2M2tpkDZk5u4JcWlYwU");
        //给谁推送 用户的openid （可以调用根据code换openid接口)
        subscribeMessage.setToUser(dto.getOpenId());
        ArrayList<WxMaSubscribeMessage.Data> wxMaSubscribeData = new ArrayList<>();

        WxMaSubscribeMessage.Data data_field_1= new WxMaSubscribeMessage.Data();
        data_field_1.setName("time1");
        data_field_1.setValue(dto.getCdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        wxMaSubscribeData.add(data_field_1);

        WxMaSubscribeMessage.Data data_field_2= new WxMaSubscribeMessage.Data();
        data_field_2.setName("thing2");
        data_field_2.setValue(dto.getContent());
        wxMaSubscribeData.add(data_field_2);
        subscribeMessage.setData(wxMaSubscribeData);
        try {
            //获取微信小程序配置：
            final WxMaService wxService = WxMaConfiguration.getMaService(dto.getAppId());
            //进行推送
            wxService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
