package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReportDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsReportQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReportVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsReportService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsReportRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SettingsReportRpc implements ISettingsReportRpc{

    @Autowired
    private ISettingsReportService settingsReportService;

    @Override
    public void addSettingsReport(SettingsReportDTO.ETO eto){
        settingsReportService.addSettingsReport(eto);
    }

    @Override
    public SettingsReportVO.DetailVO detailSettingsReport(BaseDTO dto){
        return settingsReportService.detailSettingsReport(dto);
    }

}