package com.gs.lshly.biz.support.foundation.rpc.common;
import com.gs.lshly.biz.support.foundation.service.common.IRemindPlatService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.dto.RemindPlatDTO;
import com.gs.lshly.common.struct.common.qto.RemindPlatQTO;
import com.gs.lshly.common.struct.common.vo.RemindPlatVO;
import com.gs.lshly.rpc.api.common.IRemindPlatRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2021-02-05
*/
@DubboService
public class RemindPlatRpc implements IRemindPlatRpc {

    @Autowired
    private IRemindPlatService remindPlatService;

    @Override
    public PageData<RemindPlatVO.ListVO> pageData(RemindPlatQTO.QTO qto){
        return remindPlatService.pageData(qto);
    }


    @Override
    public void deleteRemindPlat(RemindPlatDTO.IdDTO dto){
        remindPlatService.deleteRemindPlat(dto);
    }


    @Override
    public void editRemindPlat(String id,RemindPlatDTO.ETO eto){
        remindPlatService.editRemindPlat(id,eto);
    }

    @Override
    public RemindPlatVO.DetailVO detailRemindPlat(RemindPlatDTO.IdDTO dto){
        return  remindPlatService.detailRemindPlat(dto);
    }

    @Override
    public void addRemindPlatForUser2bApply(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindPlatForUser2bApply(dto);
    }

    @Override
    public void addRemindPlatForMerchantApply(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindPlatForMerchantApply(dto);
    }

    @Override
    public void addRemindPlatForGoodsUpApply(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindPlatForGoodsUpApply(dto);
    }

    @Override
    public void addRemindPlatForGoodsCategoryApply(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindPlatForGoodsCategoryApply(dto);
    }

    @Override
    public void addRemindPlatForMerchantArticleApply(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindPlatForMerchantArticleApply(dto);
    }

    @Override
    public void addRemindPlatForMarket(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindPlatForMarket(dto);
    }

    @Override
    public void addRemindForJoinActivity(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForJoinActivity(dto);
    }

    @Override
    public void addRemindForCommentApply(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForCommentApply(dto);
    }

    @Override
    public void addRemindForOrderComplain(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForOrderComplain(dto);
    }

    @Override
    public void addRemindForAfterService(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForAfterService(dto);
    }

    @Override
    public void addRemindForBackMoney(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForBackMoney(dto);
    }

    @Override
    public void addRemindForMerchantFackback(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForMerchantFackback(dto);
    }

    @Override
    public void addRemindForUserFackback(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForUserFackback(dto);
    }

    @Override
    public void addRemindForTransferMoney(RemindPlatDTO.JustDTO dto) {
        remindPlatService.addRemindForTransferMoney(dto);
    }

}