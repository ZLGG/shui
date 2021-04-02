package com.gs.lshly.biz.support.foundation.rpc.common;
import com.gs.lshly.biz.support.foundation.service.common.IRemindUserService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.dto.RemindUserDTO;
import com.gs.lshly.common.struct.common.qto.RemindUserQTO;
import com.gs.lshly.common.struct.common.vo.RemindUserVO;
import com.gs.lshly.rpc.api.common.IRemindUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2021-02-05
*/
@DubboService
public class RemindUserRpc implements IRemindUserRpc {

    @Autowired
    private IRemindUserService remindUserService;

    @Override
    public PageData<RemindUserVO.ListVO> clientPageData(RemindUserQTO.QTO qto){
        return remindUserService.clientPageData(qto);
    }

    @Override
    public PageData<RemindUserVO.ListVO> platPageData(RemindUserQTO.QTO qto) {
        return remindUserService.platPageData(qto);
    }


    @Override
    public void deleteRemindUser(RemindUserDTO.IdDTO dto){
        remindUserService.deleteRemindUser(dto);
    }


    @Override
    public void editRemindUser(String id,RemindUserDTO.ETO eto){
        remindUserService.editRemindUser(id,eto);
    }

    @Override
    public void readRemindMerchant(String id, BaseDTO dto) {
        remindUserService.readRemindMerchant(id,dto);
    }

    @Override
    public RemindUserVO.DetailVO detailRemindUser(RemindUserDTO.IdDTO dto){
        return  remindUserService.detailRemindUser(dto);
    }

    @Override
    public void addRemindUserForLogisticsStates2C(RemindUserDTO.LogisticsStatesExtDTO dto) {
        remindUserService.addRemindUserForLogisticsStates2C(dto);
    }

    @Override
    public void addRemindUserForLogisticsStates2B(RemindUserDTO.LogisticsStatesExtDTO dto) {
        remindUserService.addRemindUserForLogisticsStates2B(dto);
    }



}