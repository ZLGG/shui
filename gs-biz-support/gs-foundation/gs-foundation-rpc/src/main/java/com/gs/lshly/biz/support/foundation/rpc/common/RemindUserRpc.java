package com.gs.lshly.biz.support.foundation.rpc.common;
import com.gs.lshly.biz.support.foundation.service.common.IRemindUserService;
import com.gs.lshly.common.response.PageData;
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
    private IRemindUserService RemindUserService;

    @Override
    public PageData<RemindUserVO.ListVO> pageData(RemindUserQTO.QTO qto){
        return RemindUserService.pageData(qto);
    }

    @Override
    public void addRemindUser(RemindUserDTO.ETO eto){
        RemindUserService.addRemindUser(eto);
    }

    @Override
    public void deleteRemindUser(RemindUserDTO.IdDTO dto){
        RemindUserService.deleteRemindUser(dto);
    }


    @Override
    public void editRemindUser(String id,RemindUserDTO.ETO eto){
        RemindUserService.editRemindUser(id,eto);
    }

    @Override
    public RemindUserVO.DetailVO detailRemindUser(RemindUserDTO.IdDTO dto){
        return  RemindUserService.detailRemindUser(dto);
    }

}