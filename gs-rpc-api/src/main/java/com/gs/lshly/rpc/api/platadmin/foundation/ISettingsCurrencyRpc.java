package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsCurrencyDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsCurrencyVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsCurrencyRpc {

    void addSettingsCurrency(SettingsCurrencyDTO.ETO eto);

    SettingsCurrencyVO.DetailVO detailSettingsCurrency(BaseDTO dto);


    void editorStyle(SettingsCurrencyDTO.StyleETO eto);

    SettingsCurrencyVO.StyleDetailVO detailStyle(BaseDTO dto);


}