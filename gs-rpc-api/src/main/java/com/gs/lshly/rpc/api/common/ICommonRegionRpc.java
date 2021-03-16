package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.struct.common.CommonRegionVO;
import com.gs.lshly.common.struct.common.dto.CommonRegionDTO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-24
*/
public interface ICommonRegionRpc {

    List<CommonRegionVO.ProvinceVO> listToCounty();

    List<CommonRegionVO.ProvinceShortVO> listToCity();

    void addRegion(CommonRegionDTO.ETO dto);

    void editRegion(CommonRegionDTO.EditRegion dto);

    void deteleRegion(CommonRegionDTO.IdDTO dto);
}