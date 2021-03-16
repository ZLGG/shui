package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsVO;

public interface ISettingsService {

    SettingsVO.ListVO detail(BaseDTO dto);

    void editorSettings(SettingsDTO.ETO eto);


    SettingsVO.GetPayVO detailGetPay(BaseDTO dto);

    void editorGetPay(SettingsDTO.GetPayETO eto);


    void rightsSettings(SettingsDTO.RightsSetting dto);

    SettingsVO.Rights rightsSettingsView();

    void activityStartRemind(SettingsDTO.ActivityStartRemind dto);
}