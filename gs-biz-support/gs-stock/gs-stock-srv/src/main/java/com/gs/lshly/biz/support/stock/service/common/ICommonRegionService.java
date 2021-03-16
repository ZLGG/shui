package com.gs.lshly.biz.support.stock.service.common;
import com.gs.lshly.common.struct.common.CommonRegionVO;
import com.gs.lshly.common.struct.common.dto.CommonRegionDTO;

import java.util.List;

public interface ICommonRegionService {

    List<CommonRegionVO.ProvinceVO> listToCounty();

    List<CommonRegionVO.ProvinceShortVO> listToCity();

    void addRegion(CommonRegionDTO.ETO dto);

    void editRegion(CommonRegionDTO.EditRegion dto);

    void deteleRegion(CommonRegionDTO.IdDTO dto);
}