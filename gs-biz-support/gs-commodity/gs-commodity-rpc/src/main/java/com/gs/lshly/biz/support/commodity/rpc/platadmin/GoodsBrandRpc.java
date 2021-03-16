package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsBrandService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsBrandRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Hasee
 * @since  2020/9/17
 */

@DubboService
public class GoodsBrandRpc implements IGoodsBrandRpc {
    @Autowired
    private IGoodsBrandService iGoodsBrandService;

    @Override
    public void addGoodsBrand(GoodsBrandDTO.ETO dto) {
        iGoodsBrandService.save(dto);
    }

    @Override
    public void deleteGoodsBrand(GoodsBrandDTO.IdDTO dto) {
        iGoodsBrandService.delete(dto);
    }

    @Override
    public void updateGoodsBrand(GoodsBrandDTO.ETO dto) {
        iGoodsBrandService.update(dto);
    }

    @Override
    public PageData<GoodsBrandVO.ListVO> list(GoodsBrandQTO.QTO qoDTO) {
        return iGoodsBrandService.list(qoDTO);
    }

    @Override
    public GoodsBrandVO.DetailVO getGoodsBrand(GoodsBrandDTO.IdDTO idDTO) {
        return iGoodsBrandService.select(idDTO);
    }

    @Override
    public List<GoodsBrandVO.ListVO> listGoodsBrand(GoodsCategoryDTO.IdDTO dto) {
        return iGoodsBrandService.listGoodsBrand(dto);
    }

    @Override
    public  List<GoodsBrandVO.ListVO> innerCheckGoodsBrand(String brandName) {
        return iGoodsBrandService.innerCheckGoodsBrand(brandName);
    }

    @Override
    public  GoodsBrandVO.BrandIdVO saveBrand(GoodsBrandDTO.ETO eto) {
        return iGoodsBrandService.saveBrand(eto);
    }

    @Override
    public String innerAddGoodsBrand(GoodsBrandDTO.ETO dto) {
        return iGoodsBrandService.innerAddGoodsBrand(dto);
    }
}
