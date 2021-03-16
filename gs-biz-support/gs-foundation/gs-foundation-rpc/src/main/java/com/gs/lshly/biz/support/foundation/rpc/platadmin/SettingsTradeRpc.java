package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsTradeService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsTradeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsTradeVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsTradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsTradeRpc implements ISettingsTradeRpc{

    @Autowired
    private ISettingsTradeService settingsTradeService;

    @Override
    public void addSettingsTrade(SettingsTradeDTO.ETO eto){
        settingsTradeService.addSettingsTrade(eto);
    }

    @Override
    public SettingsTradeVO.DetailVO detailSettingsTrade(BaseDTO dto){
        return settingsTradeService.detailSettingsTrade(dto);
    }

}