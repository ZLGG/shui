package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.GoodsExcelImportDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;


public interface IPCMerchGoodsInfoImportRpc {

    void saveOrUpdateData(GoodsExcelImportDTO data);

    /**
     * 获取店铺id
     * @param dto
     * @return
     */
    String getShopId(BaseDTO dto);


    CommonShopVO.ShopGoodsCategoryVO categoryLevelVO(String categoryName,String shopId);

    PCMerchGoodsCategoryVO.innerCategoryVO categoryVo(String categoryName,String parentId);

    boolean countBrand(String brandName,String categoryId);

    int checkShopNavigation(String shopId,String shopNavigationName,Integer useFiled);

    int checkTemplate(String shopId,String templateName);

}
