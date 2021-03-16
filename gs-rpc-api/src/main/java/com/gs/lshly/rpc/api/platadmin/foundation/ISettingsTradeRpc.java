package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsTradeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsTradeVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsTradeRpc {

    void addSettingsTrade(SettingsTradeDTO.ETO eto);

    SettingsTradeVO.DetailVO detailSettingsTrade(BaseDTO dto);

}