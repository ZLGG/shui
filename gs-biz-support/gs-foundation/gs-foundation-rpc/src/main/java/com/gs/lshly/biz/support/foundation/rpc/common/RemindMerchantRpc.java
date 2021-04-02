package com.gs.lshly.biz.support.foundation.rpc.common;
import com.gs.lshly.biz.support.foundation.service.common.IRemindMerchantService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
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
    private IRemindMerchantService remindMerchantService;

    @Override
    public PageData<RemindMerchantVO.ListVO> merchantPageData(RemindMerchantQTO.QTO qto) {
        return remindMerchantService.merchantPageData(qto);
    }

    @Override
    public PageData<RemindMerchantVO.ListVO> platPageData(RemindMerchantQTO.QTO qto) {
        return remindMerchantService.platPageData(qto);
    }

    @Override
    public void deleteRemindMerchant(RemindMerchantDTO.IdDTO dto){
        remindMerchantService.deleteRemindMerchant(dto);
    }


    @Override
    public void editRemindMerchant(String id,RemindMerchantDTO.ETO eto){
        remindMerchantService.editRemindMerchant(id,eto);
    }

    @Override
    public RemindMerchantVO.DetailVO detailRemindMerchant(RemindMerchantDTO.IdDTO dto){
        return  remindMerchantService.detailRemindMerchant(dto);
    }

    @Override
    public void addRemindMerchantForSendGoods(RemindMerchantDTO.JustDTO dto) {
        remindMerchantService.addRemindMerchantForSendGoods(dto);
    }

    @Override
    public void addRemindMerchantForAskOrder(RemindMerchantDTO.JustDTO dto) {
        remindMerchantService.addRemindMerchantForAskOrder(dto);
    }

    @Override
    public void addRemindMerchantForCloseOrder(RemindMerchantDTO.JustDTO dto) {
        remindMerchantService.addRemindMerchantForCloseOrder(dto);
    }

    @Override
    public void addRemindMerchantForActivity(RemindMerchantDTO.JustDTO dto) {
        remindMerchantService.addRemindMerchantForActivity(dto);
    }

    @Override
    public void addRemindMerchantForBackChangeGoods(RemindMerchantDTO.JustDTO dto) {
        remindMerchantService.addRemindMerchantForBackChangeGoods(dto);
    }

    @Override
    public void addRemindMerchantForAskTalk(RemindMerchantDTO.JustDTO dto) {
        remindMerchantService.addRemindMerchantForAskTalk(dto);
    }

    @Override
    public void addRemindMerchantForPlatNotic(RemindMerchantDTO.NoticAcctAllDTO dto) {
        remindMerchantService.addRemindMerchantForPlatNotic(dto);
    }

    @Override
    public void addRemindMerchantForPlatNotic(RemindMerchantDTO.NoticAcctDTO dto) {
        remindMerchantService.addRemindMerchantForPlatNotic(dto);
    }

}