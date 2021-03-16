package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsIntegralDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;

public interface ISettingsIntegralService {

    void addSettingsIntegral(SettingsIntegralDTO.ETO eto);

    SettingsIntegralVO.DetailVO detailSettingsIntegral(BaseDTO dto);

}