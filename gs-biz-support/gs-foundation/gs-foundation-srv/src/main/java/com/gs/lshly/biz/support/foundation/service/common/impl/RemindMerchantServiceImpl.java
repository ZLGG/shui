package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.RemindMerchant;
import com.gs.lshly.biz.support.foundation.repository.IRemindMerchantRepository;
import com.gs.lshly.biz.support.foundation.service.common.IRemindMerchantService;
import com.gs.lshly.common.enums.RemindMerchantEnum;
import com.gs.lshly.common.enums.RemindReadEnum;
import com.gs.lshly.common.enums.RemindStyleTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2021-02-05
*/
@Component
public class RemindMerchantServiceImpl implements IRemindMerchantService {

    @Autowired
    private IRemindMerchantRepository repository;

    @Override
    public PageData<RemindMerchantVO.ListVO> merchantPageData(RemindMerchantQTO.QTO qto) {
        QueryWrapper<RemindMerchant> queryWrapper = new QueryWrapper<>();
        if(null != qto.getCType()){
            queryWrapper.eq("c_type",qto.getCType());
        }
        if(null !=qto.getState() || qto.getState() ==RemindReadEnum.全部.getCode()){
            queryWrapper.eq("state",qto.getState());
        }else{
            queryWrapper.eq("state",RemindReadEnum.待读.getCode());
        }
        queryWrapper.and(wrapper->{
            wrapper.eq("accet_id",qto.getJwtShopId());
            wrapper.or();
            wrapper.isNull("accet_id");
        });
        queryWrapper.orderByDesc("cdate");
        IPage<RemindMerchant> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, RemindMerchantVO.ListVO.class, page);
    }

    @Override
    public PageData<RemindMerchantVO.ListVO> platPageData(RemindMerchantQTO.QTO qto) {
        QueryWrapper<RemindMerchant> wrapper = new QueryWrapper<>();
        if(null != qto.getCType()){
            wrapper.eq("c_type",qto.getCType());
        }
        if(null !=qto.getState() || qto.getState() ==RemindReadEnum.全部.getCode()){
            wrapper.eq("state",qto.getState());
        }else{
            wrapper.eq("state",RemindReadEnum.待读.getCode());
        }
        IPage<RemindMerchant> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, RemindMerchantVO.ListVO.class, page);
    }

    @Override
    public void deleteRemindMerchant(RemindMerchantDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editRemindMerchant(String id,RemindMerchantDTO.ETO eto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setState(RemindReadEnum.已读.getCode());
        remindMerchant.setAccetId(eto.getJwtShopId());
        repository.updateById(remindMerchant);
    }


    @Override
    public RemindMerchantVO.DetailVO detailRemindMerchant(RemindMerchantDTO.IdDTO dto) {
        QueryWrapper<RemindMerchant> remindMerchantQueryWrapper = MybatisPlusUtil.query();
        remindMerchantQueryWrapper.eq("id",dto.getId());
        remindMerchantQueryWrapper.and(wrapper->{
            wrapper.eq("accet_id",dto.getJwtShopId());
            wrapper.or().isNull("accet_id");
        });
        RemindMerchant remindMerchant = repository.getOne(remindMerchantQueryWrapper);
        if(ObjectUtils.isEmpty(remindMerchant)){
            return null;
        }
        remindMerchant.setState(RemindReadEnum.已读.getCode());
        repository.updateById(remindMerchant);
        RemindMerchantVO.DetailVO detailVo = new RemindMerchantVO.DetailVO();
        BeanUtils.copyProperties(remindMerchant, detailVo);
        return detailVo;
    }

    @Override
    public void addRemindMerchantForSendGoods(RemindMerchantDTO.JustDTO dto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindMerchant.setCType(RemindMerchantEnum.待发货提醒.getCode());
        remindMerchant.setAccetId(dto.getAccetId());
        remindMerchant.setTriggerId(dto.getTriggerId());
        remindMerchant.setTriggerSid(dto.getTriggerSid());
        remindMerchant.setContent("您有新的订单，请及时处理");
        remindMerchant.setState(RemindReadEnum.待读.getCode());
        repository.save(remindMerchant);
    }

    @Override
    public void addRemindMerchantForAskOrder(RemindMerchantDTO.JustDTO dto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindMerchant.setCType(RemindMerchantEnum.用户催单提醒.getCode());
        remindMerchant.setAccetId(dto.getAccetId());
        remindMerchant.setTriggerId(dto.getTriggerId());
        remindMerchant.setTriggerSid(dto.getTriggerSid());
        remindMerchant.setContent("用户催单了，请及时处理");
        remindMerchant.setState(RemindReadEnum.待读.getCode());
        repository.save(remindMerchant);
    }

    @Override
    public void addRemindMerchantForCloseOrder(RemindMerchantDTO.JustDTO dto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindMerchant.setCType(RemindMerchantEnum.取消订单待审核.getCode());
        remindMerchant.setAccetId(dto.getAccetId());
        remindMerchant.setTriggerId(dto.getTriggerId());
        remindMerchant.setTriggerSid(dto.getTriggerSid());
        remindMerchant.setContent("用户订单已取消，请及时审核");
        remindMerchant.setState(RemindReadEnum.待读.getCode());
        repository.save(remindMerchant);
    }

    @Override
    public void addRemindMerchantForActivity(RemindMerchantDTO.JustDTO dto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindMerchant.setCType(RemindMerchantEnum.活动报名提醒.getCode());
        remindMerchant.setAccetId(dto.getAccetId());
        remindMerchant.setTriggerId(dto.getTriggerId());
        remindMerchant.setTriggerSid(dto.getTriggerSid());
        remindMerchant.setContent("有新的平台活动，快去报名吧");
        remindMerchant.setState(RemindReadEnum.待读.getCode());
        repository.save(remindMerchant);
    }

    @Override
    public void addRemindMerchantForBackChangeGoods(RemindMerchantDTO.JustDTO dto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindMerchant.setCType(RemindMerchantEnum.退换货待审核.getCode());
        remindMerchant.setAccetId(dto.getAccetId());
        remindMerchant.setTriggerId(dto.getTriggerId());
        remindMerchant.setTriggerSid(dto.getTriggerSid());
        remindMerchant.setContent("您有新的售后申请待审核，请及时处理");
        remindMerchant.setState(RemindReadEnum.待读.getCode());
        repository.save(remindMerchant);
    }

    @Override
    public void addRemindMerchantForAskTalk(RemindMerchantDTO.JustDTO dto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindMerchant.setCType(RemindMerchantEnum.咨询待回复提醒.getCode());
        remindMerchant.setAccetId(dto.getAccetId());
        remindMerchant.setTriggerId(dto.getTriggerId());
        remindMerchant.setTriggerSid(dto.getTriggerSid());
        remindMerchant.setContent("您有新的咨询，请及时回复");
        remindMerchant.setState(RemindReadEnum.待读.getCode());
        repository.save(remindMerchant);
    }

    @Override
    public void addRemindMerchantForPlatNotic(RemindMerchantDTO.NoticAcctAllDTO dto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
        remindMerchant.setCType(RemindMerchantEnum.平台通知.getCode());
        remindMerchant.setAccetId(dto.getAccetId());
        remindMerchant.setTriggerId(dto.getTriggerId());
        remindMerchant.setTriggerSid(dto.getTriggerSid());
        remindMerchant.setContent(dto.getContent());
        remindMerchant.setState(RemindReadEnum.待读.getCode());
        repository.save(remindMerchant);
    }

    @Override
    public void addRemindMerchantForPlatNotic(RemindMerchantDTO.NoticAcctDTO dto) {
        if(ObjectUtils.isEmpty(dto.getAccetIdList())){
            List<RemindMerchant> remindMerchantList = new ArrayList<>();
            for(String accetId:dto.getAccetIdList()){
                RemindMerchant remindMerchant = new RemindMerchant();
                remindMerchant.setCStyle(RemindStyleTypeEnum.站内信.getCode());
                remindMerchant.setCType(RemindMerchantEnum.平台通知.getCode());
                remindMerchant.setAccetId(accetId);
                remindMerchant.setTriggerId(dto.getTriggerId());
                remindMerchant.setTriggerSid(dto.getTriggerSid());
                remindMerchant.setContent(dto.getContent());
                remindMerchant.setState(RemindReadEnum.待读.getCode());
                remindMerchantList.add(remindMerchant);
            }
            repository.saveBatch(remindMerchantList);
        }

    }


}
