package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsCurrencyService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsCurrencyDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsCurrencyVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsCurrencyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsCurrencyRpc implements ISettingsCurrencyRpc{

    @Autowired
    private ISettingsCurrencyService settingsCurrencyService;

    @Override
    public void addSettingsCurrency(SettingsCurrencyDTO.ETO eto){
        settingsCurrencyService.addSettingsCurrency(eto);
    }

    @Override
    public SettingsCurrencyVO.DetailVO detailSettingsCurrency(BaseDTO dto){
        return settingsCurrencyService.detailSettingsCurrency(dto);
    }

    @Override
    public void editorStyle(SettingsCurrencyDTO.StyleETO dto) {
        settingsCurrencyService.editorStyle(dto);
    }

    @Override
    public SettingsCurrencyVO.StyleDetailVO detailStyle(BaseDTO dto) {
        return settingsCurrencyService.detailStyle(dto);
    }


}