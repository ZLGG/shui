package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsIntegralDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsIntegralRpc {

    void addSettingsIntegral(SettingsIntegralDTO.ETO eto);

    SettingsIntegralVO.DetailVO detailSettingsIntegral(BaseDTO dto);

}