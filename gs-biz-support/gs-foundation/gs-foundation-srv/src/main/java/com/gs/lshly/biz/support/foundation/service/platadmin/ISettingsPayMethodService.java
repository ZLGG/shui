package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsPayMethodDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsPayMethodQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsPayMethodVO;

public interface ISettingsPayMethodService {

    PageData<SettingsPayMethodVO.ListVO> pageData(SettingsPayMethodQTO.QTO qto);

    void addSettingsPayMethod(SettingsPayMethodDTO.ETO eto);

    SettingsPayMethodVO.DetailVO detailSettingsPayMethod(SettingsPayMethodDTO.IdDTO dto);

    void editor(SettingsPayMethodDTO.ETO dto);

    void deleteBatch(SettingsPayMethodDTO.IdListDTO dto);

}