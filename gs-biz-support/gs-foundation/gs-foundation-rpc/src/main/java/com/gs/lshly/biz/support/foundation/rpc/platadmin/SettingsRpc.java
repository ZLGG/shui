package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsRpc implements ISettingsRpc{

    @Autowired
    private ISettingsService settingsService;


    @Override
    public SettingsVO.ListVO detail(BaseDTO dto) {
        return settingsService.detail(dto);
    }

    @Override
    public void editorSettings(SettingsDTO.ETO eto){
        settingsService.editorSettings(eto);
    }

    @Override
    public SettingsVO.GetPayVO detailGetPay(BaseDTO dto) {
        return settingsService.detailGetPay(dto);
    }

    @Override
    public void editorGetPay(SettingsDTO.GetPayETO eto) {
        settingsService.editorGetPay(eto);
    }

    @Override
    public void rightsSettings(SettingsDTO.RightsSetting dto) {
        settingsService.rightsSettings(dto);
    }

    @Override
    public SettingsVO.Rights rightsSettingsView() {
        return settingsService.rightsSettingsView();
    }

    @Override
    public void activityStartRemind(SettingsDTO.ActivityStartRemind dto) {
        settingsService.activityStartRemind(dto);
    }


}