package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsWmsInterfaceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsWmsInterfaceVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsWmsInterfaceRpc {

    void addSettingsWmsInterface(SettingsWmsInterfaceDTO.ETO eto);

    SettingsWmsInterfaceVO.DetailVO detailSettingsWmsInterface(BaseDTO dto);

}