package com.gs.lshly.biz.support.foundation.rpc.common;
import com.gs.lshly.biz.support.foundation.service.common.IRemindMerchantService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;
import com.gs.lshly.rpc.api.common.IRemindMerchantRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2021-02-05
*/
@DubboService
public class RemindMerchantRpc implements IRemindMerchantRpc {
    @Autowired
    private IRemindMerchantService RemindMerchantService;

    @Override
    public PageData<RemindMerchantVO.ListVO> pageData(RemindMerchantQTO.QTO qto){
        return RemindMerchantService.pageData(qto);
    }

    @Override
    public void addRemindMerchant(RemindMerchantDTO.ETO eto){
        RemindMerchantService.addRemindMerchant(eto);
    }

    @Override
    public void deleteRemindMerchant(RemindMerchantDTO.IdDTO dto){
        RemindMerchantService.deleteRemindMerchant(dto);
    }


    @Override
    public void editRemindMerchant(String id,RemindMerchantDTO.ETO eto){
        RemindMerchantService.editRemindMerchant(id,eto);
    }

    @Override
    public RemindMerchantVO.DetailVO detailRemindMerchant(RemindMerchantDTO.IdDTO dto){
        return  RemindMerchantService.detailRemindMerchant(dto);
    }

}