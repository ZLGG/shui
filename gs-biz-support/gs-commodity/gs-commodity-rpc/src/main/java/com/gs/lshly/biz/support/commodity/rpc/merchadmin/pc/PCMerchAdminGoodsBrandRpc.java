package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsBrandService;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsBrandDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsBrandVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsBrandRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 20:17 2020/10/19
 */
@DubboService
public class PCMerchAdminGoodsBrandRpc implements IPCMerchAdminGoodsBrandRpc {
    @Autowired
    private IPCMerchGoodsBrandService brandService;

    @Override
    public List<PCMerchGoodsBrandVO.ListVO> listGoodsBrand(PCMerchGoodsCategoryDTO.IdDTO dto) {
        return brandService.listGoodsBrand(dto);
    }

    @Override
    public List<PCMerchGoodsBrandVO.ListVO> listBrandVO(BaseQTO qto) {
        return brandService.listBrandVO(qto);
    }

    @Override
    public List<PCMerchGoodsBrandVO.ListVO> listBrandVOByCategoryLevel1(PCMerchGoodsCategoryDTO.IdListDTO dto) {
        return brandService.listBrandVOByCategoryLevel1(dto);
    }

    @Override
    public ResponseData<Void> innerCheckMerchantApplyBrand(CommonShopDTO.CategoryETO categoryETO, String brandId, String brandName, Integer isNew) {
        return brandService.innerCheckMerchantApplyBrand(categoryETO,brandId,brandName,isNew);
    }

    @Override
    public boolean innerGetBrandVO(String brandName, String categoryId) {
        return brandService.innerGetBrandVO(brandName, categoryId);
    }


}
