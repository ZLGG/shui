package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReceiptDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReceiptVO;

public interface ISettingsReceiptService {

    void addSettingsReceipt(SettingsReceiptDTO.ETO eto);

    SettingsReceiptVO.DetailVO detailSettingsReceipt(BaseDTO dto);

}