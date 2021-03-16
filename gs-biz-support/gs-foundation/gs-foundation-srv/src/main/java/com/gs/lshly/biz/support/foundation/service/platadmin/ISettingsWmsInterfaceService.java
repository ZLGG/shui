package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsWmsInterfaceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsWmsInterfaceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsWmsInterfaceVO;

public interface ISettingsWmsInterfaceService {

    void addSettingsWmsInterface(SettingsWmsInterfaceDTO.ETO eto);

    SettingsWmsInterfaceVO.DetailVO detailSettingsWmsInterface(BaseDTO dto);

}