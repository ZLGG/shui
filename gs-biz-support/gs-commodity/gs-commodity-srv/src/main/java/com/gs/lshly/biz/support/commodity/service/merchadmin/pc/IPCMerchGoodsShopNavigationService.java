package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.entity.GoodsShopNavigation;
import com.gs.lshly.biz.support.commodity.entity.GoodsTempalte;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsShopNavigationVO;

import java.util.List;

public interface IPCMerchGoodsShopNavigationService {

    /**
     * 关联商品
     * @param dto
     */
    void bindGoods(PCMerchGoodsShopNavigationDTO.BindGoodsDTO dto);



    //------内部服务---------------------

    /**
     * 根据店铺id列表查询数据
     * @param qto
     * @return
     */
    List<PCMerchGoodsShopNavigationVO.ListVO> ListInnerService(PCMerchGoodsShopNavigationQTO.QTO qto);


    /**
     * 删除商品店铺分类关系
     * @param dto
     */
    void deleteInnerService(PCMerchGoodsShopNavigationDTO.IdListDTO dto);

    void saveInnerService(GoodsShopNavigation shopNavigation);

    void saveTempalteInnerService(GoodsTempalte tempalte);

}