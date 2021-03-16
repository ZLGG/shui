package com.gs.lshly.biz.support.stock.rpc.common;

import com.gs.lshly.biz.support.stock.service.common.ICommonRegionService;
import com.gs.lshly.common.struct.common.CommonRegionVO;
import com.gs.lshly.common.struct.common.dto.CommonRegionDTO;
import com.gs.lshly.rpc.api.common.ICommonRegionRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zzg
* @since 2020-10-24
*/
@DubboService
public class CommonRegionRpc implements ICommonRegionRpc {

    @Autowired
    private ICommonRegionService regionService;

    @Override
    public List<CommonRegionVO.ProvinceVO> listToCounty() {
        return regionService.listToCounty();
    }

    @Override
    public List<CommonRegionVO.ProvinceShortVO> listToCity() {
        return regionService.listToCity();
    }


    @Override
    public void addRegion(CommonRegionDTO.ETO dto) {
        regionService.addRegion(dto);
    }

    @Override
    public void editRegion(CommonRegionDTO.EditRegion dto) {
        regionService.editRegion(dto);
    }

    @Override
    public void deteleRegion(CommonRegionDTO.IdDTO dto) {
        regionService.deteleRegion(dto);
    }
}