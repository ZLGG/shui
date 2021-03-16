package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsCurrencyDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsCurrencyVO;

public interface ISettingsCurrencyService {

    void addSettingsCurrency(SettingsCurrencyDTO.ETO eto);

    SettingsCurrencyVO.DetailVO detailSettingsCurrency(BaseDTO dto);


    void editorStyle(SettingsCurrencyDTO.StyleETO eto);

    SettingsCurrencyVO.StyleDetailVO detailStyle(BaseDTO dto);
}