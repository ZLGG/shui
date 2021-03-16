package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsShopNavigationVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-11-09
*/
public interface IPCMerchGoodsShopNavigationRpc {

    /**
     * 关联商品
     * @param dto
     */
    void bindGoods(PCMerchGoodsShopNavigationDTO.BindGoodsDTO dto);



    /**
     * 根据店铺id列表查询数据
     * @param qto
     * @return
     */
    List<PCMerchGoodsShopNavigationVO.ListVO> listInnerService(PCMerchGoodsShopNavigationQTO.QTO qto);


    /**
     * 删除商品店铺分类关系
     * @param dto
     */
    void deleteInnerService(PCMerchGoodsShopNavigationDTO.IdListDTO dto);

}