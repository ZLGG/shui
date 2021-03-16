package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReportDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReportVO;

public interface ISettingsReportService {

    void addSettingsReport(SettingsReportDTO.ETO eto);

    SettingsReportVO.DetailVO detailSettingsReport(BaseDTO dto);

}