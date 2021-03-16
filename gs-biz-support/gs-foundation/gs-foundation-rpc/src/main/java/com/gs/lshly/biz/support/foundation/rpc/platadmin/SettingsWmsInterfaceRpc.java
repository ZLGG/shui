package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsWmsInterfaceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsWmsInterfaceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsWmsInterfaceVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsWmsInterfaceService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsWmsInterfaceRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsWmsInterfaceRpc implements ISettingsWmsInterfaceRpc{

    @Autowired
    private ISettingsWmsInterfaceService settingsWmsInterfaceService;

    @Override
    public void addSettingsWmsInterface(SettingsWmsInterfaceDTO.ETO eto){
        settingsWmsInterfaceService.addSettingsWmsInterface(eto);
    }


    @Override
    public SettingsWmsInterfaceVO.DetailVO detailSettingsWmsInterface(BaseDTO dto){
        return settingsWmsInterfaceService.detailSettingsWmsInterface(dto);
    }

}