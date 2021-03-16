package com.gs.lshly.biz.support.foundation.rpc.common;
import com.gs.lshly.biz.support.foundation.service.common.IRemindPlatService;
import com.gs.lshly.common.response.PageData;
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
    public void addRemindPlat(RemindPlatDTO.ETO eto){
        remindPlatService.addRemindPlat(eto);
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
    public void addRemindPlatForUser2bApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForMerchantApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForGoodsUpApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForGoodsCategoryApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForMerchantArticleApply(RemindPlatDTO.ETO eto) {

    }

}