package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsIntegralDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsIntegralQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsIntegralService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsIntegralRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsIntegralRpc implements ISettingsIntegralRpc{

    @Autowired
    private ISettingsIntegralService settingsIntegralService;

    @Override
    public void addSettingsIntegral(SettingsIntegralDTO.ETO eto){
        settingsIntegralService.addSettingsIntegral(eto);
    }

    @Override
    public SettingsIntegralVO.DetailVO detailSettingsIntegral(BaseDTO dto){
        return settingsIntegralService.detailSettingsIntegral(dto);
    }

}