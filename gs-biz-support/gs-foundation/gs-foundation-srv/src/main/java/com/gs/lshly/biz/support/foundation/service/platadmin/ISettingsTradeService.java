package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsTradeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsTradeVO;

public interface ISettingsTradeService {

    void addSettingsTrade(SettingsTradeDTO.ETO eto);

    SettingsTradeVO.DetailVO detailSettingsTrade(BaseDTO dto);

}