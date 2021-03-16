package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsPayMethodDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsPayMethodQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsPayMethodVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsPayMethodService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsPayMethodRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsPayMethodRpc implements ISettingsPayMethodRpc{

    @Autowired
    private ISettingsPayMethodService settingsPayMethodService;

    @Override
    public PageData<SettingsPayMethodVO.ListVO> pageData(SettingsPayMethodQTO.QTO qto){
        return settingsPayMethodService.pageData(qto);
    }

    @Override
    public void addSettingsPayMethod(SettingsPayMethodDTO.ETO eto){
        settingsPayMethodService.addSettingsPayMethod(eto);
    }

    @Override
    public SettingsPayMethodVO.DetailVO detailSettingsPayMethod(SettingsPayMethodDTO.IdDTO dto){
        return settingsPayMethodService.detailSettingsPayMethod(dto);
    }

    @Override
    public void editor(SettingsPayMethodDTO.ETO dto) {
        settingsPayMethodService.editor(dto);
    }

    @Override
    public void deleteBatch(SettingsPayMethodDTO.IdListDTO dto) {
        settingsPayMethodService.deleteBatch(dto);
    }

}