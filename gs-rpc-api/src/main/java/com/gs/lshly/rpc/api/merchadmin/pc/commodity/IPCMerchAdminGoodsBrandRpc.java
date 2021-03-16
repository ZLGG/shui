package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsBrandDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsBrandVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 20:07 2020/10/19
 */
public interface IPCMerchAdminGoodsBrandRpc {
    /**
     * 查询类目关联的品牌信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsBrandVO.ListVO> listGoodsBrand(PCMerchGoodsCategoryDTO.IdDTO dto);

    /**
     * 查询所有的商品品牌信息
     * @param qto
     * @return
     */
    List<PCMerchGoodsBrandVO.ListVO> listBrandVO(BaseQTO qto);

    /**
     * 根据一级类目查询所关联的所有品牌信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsBrandVO.ListVO> listBrandVOByCategoryLevel1(PCMerchGoodsCategoryDTO.IdListDTO dto);


    /**
     * 检查商家入驻选择的品牌关联的类目是否正确
     * @param brandName
     * @return
     */
    ResponseData<Void> innerCheckMerchantApplyBrand(CommonShopDTO.CategoryETO categoryETO, String brandId, String brandName, Integer isNew);


    /**
     * check该三级类目下是否有该品牌名称
     * @param brandName
     * @param categoryId
     * @return
     */
    boolean innerGetBrandVO(String brandName,String categoryId);
}
