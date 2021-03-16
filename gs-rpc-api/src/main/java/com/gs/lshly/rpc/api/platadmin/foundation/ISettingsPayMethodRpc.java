package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsPayMethodDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsPayMethodQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsPayMethodVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ISettingsPayMethodRpc {

    PageData<SettingsPayMethodVO.ListVO> pageData(SettingsPayMethodQTO.QTO qto);

    void addSettingsPayMethod(SettingsPayMethodDTO.ETO eto);

    SettingsPayMethodVO.DetailVO detailSettingsPayMethod(SettingsPayMethodDTO.IdDTO dto);

    void editor(SettingsPayMethodDTO.ETO dto);

    void deleteBatch(SettingsPayMethodDTO.IdListDTO dto);

}