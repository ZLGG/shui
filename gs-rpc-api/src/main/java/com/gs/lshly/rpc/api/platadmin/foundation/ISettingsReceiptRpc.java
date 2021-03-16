package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReceiptDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReceiptVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsReceiptRpc {

    void addSettingsReceipt(SettingsReceiptDTO.ETO eto);

    SettingsReceiptVO.DetailVO detailSettingsReceipt(BaseDTO dto);

}