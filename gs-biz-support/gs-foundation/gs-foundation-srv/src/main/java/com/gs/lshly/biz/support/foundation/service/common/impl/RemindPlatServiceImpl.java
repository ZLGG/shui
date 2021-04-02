package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.RemindPlat;
import com.gs.lshly.biz.support.foundation.repository.IRemindPlatRepository;
import com.gs.lshly.biz.support.foundation.service.common.IRemindPlatService;
import com.gs.lshly.common.enums.RemindPlatEnum;
import com.gs.lshly.common.enums.RemindReadEnum;
import com.gs.lshly.common.enums.RemindStyleTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.dto.RemindPlatDTO;
import com.gs.lshly.common.struct.common.qto.RemindPlatQTO;
import com.gs.lshly.common.struct.common.vo.RemindPlatVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2021-02-05
*/
@Component
public class RemindPlatServiceImpl implements IRemindPlatService {

    @Autowired
    private IRemindPlatRepository repository;

    @Override
    public PageData<RemindPlatVO.ListVO> pageData(RemindPlatQTO.QTO qto) {
        QueryWrapper<RemindPlat> wrapper = new QueryWrapper<>();
        if(null != qto.getCType()){
            wrapper.eq("c_type",qto.getCType());
        }
        if(null !=qto.getState() || qto.getState() ==RemindReadEnum.全部.getCode()){
            wrapper.eq("state",qto.getState());
        }
        wrapper.orderByDesc("cdate");
        IPage<RemindPlat> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, RemindPlatVO.ListVO.class, page);
    }

    @Override
    public void deleteRemindPlat(RemindPlatDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editRemindPlat(String id,RemindPlatDTO.ETO eto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setId(id);
        remindPlat.setState(RemindReadEnum.已读.getCode());
        repository.updateById(remindPlat);
    }

    @Override
    public RemindPlatVO.DetailVO detailRemindPlat(RemindPlatDTO.IdDTO dto) {
        RemindPlat remindPlat = repository.getById(dto.getId());
        RemindPlatVO.DetailVO detailVo = new RemindPlatVO.DetailVO();
        if(ObjectUtils.isEmpty(remindPlat)){
            throw new BusinessException("没有数据");
        }
        remindPlat.setState(RemindReadEnum.已读.getCode());
        repository.updateById(remindPlat);
        BeanUtils.copyProperties(remindPlat, detailVo);
        return detailVo;
    }

    @Override
    public void addRemindPlatForUser2bApply(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.企业会员待审核.getCode());
        remindPlat.setContent("您有企业会员待审,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindPlatForMerchantApply(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.商家入驻待审核.getCode());
        remindPlat.setContent("您有商家入驻待审,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindPlatForGoodsUpApply(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCType(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.商品上架待审核.getCode());
        remindPlat.setContent("您有商品上架待审,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindPlatForGoodsCategoryApply(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.申请类目待审核.getCode());
        remindPlat.setContent("您有申请类目待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindPlatForMerchantArticleApply(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.商家文章待审核.getCode());
        remindPlat.setContent("您有商家文章待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindPlatForMarket(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.商家营销活动待审核.getCode());
        remindPlat.setContent("您有商家营销活动待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForJoinActivity(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.商家报名活动待审核.getCode());
        remindPlat.setContent("您有商家报名活动待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForCommentApply(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.评论申诉待审核.getCode());
        remindPlat.setContent("您有评论申诉待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForOrderComplain(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.订单投诉待审核.getCode());
        remindPlat.setContent("您有订单投诉待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForAfterService(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.售后申请待审核.getCode());
        remindPlat.setContent("您有售后申请待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForBackMoney(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.退款待审核.getCode());
        remindPlat.setContent("您有退款待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForMerchantFackback(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.商家意见反馈待审核.getCode());
        remindPlat.setContent("您有商家意见反馈待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForUserFackback(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.会员意见反馈提醒.getCode());
        remindPlat.setContent("您有会员意见反馈待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }

    @Override
    public void addRemindForTransferMoney(RemindPlatDTO.JustDTO dto) {
        RemindPlat remindPlat = new RemindPlat();
        remindPlat.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindPlat.setCType(RemindPlatEnum.转账待审核.getCode());
        remindPlat.setContent("您有转账待审核,请及时处理");
        remindPlat.setTriggerId(dto.getTriggerId());
        remindPlat.setTriggerSid(dto.getTriggerSid());
        remindPlat.setState(RemindReadEnum.待读.getCode());
        repository.save(remindPlat);
    }


}
