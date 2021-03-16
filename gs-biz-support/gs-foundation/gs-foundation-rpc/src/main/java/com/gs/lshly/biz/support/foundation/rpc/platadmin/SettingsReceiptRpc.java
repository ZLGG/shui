package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsReceiptService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReceiptDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReceiptVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsReceiptRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsReceiptRpc implements ISettingsReceiptRpc{

    @Autowired
    private ISettingsReceiptService settingsReceiptService;

    @Override
    public void addSettingsReceipt(SettingsReceiptDTO.ETO eto){
        settingsReceiptService.addSettingsReceipt(eto);
    }

    @Override
    public SettingsReceiptVO.DetailVO detailSettingsReceipt(BaseDTO dto){
        return settingsReceiptService.detailSettingsReceipt(dto);
    }

}