package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PictureGroupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PictureGroupVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-06
*/
public interface IPictureGroupRpc {

    List<PictureGroupVO.ListVO> pageData(PictureGroupQTO.QTO qto);

    void addPictureGroup(PictureGroupDTO.ETO eto);

    void deletePictureGroup(PictureGroupDTO.IdDTO dto);

    void editPictureGroup(PictureGroupDTO.ETO eto);

    PictureGroupVO.DetailVO detailPictureGroup(PictureGroupDTO.IdDTO dto);

}