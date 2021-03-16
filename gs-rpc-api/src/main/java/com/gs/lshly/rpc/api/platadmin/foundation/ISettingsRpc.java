package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsRpc {

    SettingsVO.ListVO detail(BaseDTO dto);

    void editorSettings(SettingsDTO.ETO eto);


    SettingsVO.GetPayVO detailGetPay(BaseDTO dto);

    void editorGetPay(SettingsDTO.GetPayETO eto);


    void rightsSettings(SettingsDTO.RightsSetting dto);

    SettingsVO.Rights rightsSettingsView();

    void activityStartRemind(SettingsDTO.ActivityStartRemind dto);
}