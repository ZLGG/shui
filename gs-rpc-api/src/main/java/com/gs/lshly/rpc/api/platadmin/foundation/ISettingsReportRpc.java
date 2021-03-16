package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReportDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReportVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsReportRpc {

    void addSettingsReport(SettingsReportDTO.ETO eto);

    SettingsReportVO.DetailVO detailSettingsReport(BaseDTO dto);

}