package com.gs.lshly.rpc.api.bbc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.dto.BbcShopDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
public interface IBbcShopRpc {

    PageData<BbcShopVO.ListVO> pageData(BbcShopQTO.QTO qto);

    PageData<BbcShopVO.ScopeListVO> nearShopListPageData(BbcShopQTO.ScopeQTO qto);

    BbcShopVO.DetailVO detailShop(BbcShopDTO.IdDTO dto);

    BbcStockDeliveryVO.SupportDeliveryTypeVO supportDeliveryStyle(BbcStockDeliveryDTO.SupportDeliveryTypeDTO dto);

    CommonDeliveryCostCalcParam getPickupDeliveryCostCalcParam(String shopId);

    /**
     * 店铺组合信息
     * @param
     * @return
     */
    BbcShopVO.ComplexVO shopComplex(BbcShopDTO.IdDTO dto);

    List<BbcShopVO.ShopNavVO> navigationTree(BbcShopDTO.IdDTO dto);



    //--------------------内部服务接口-----------------------------

    /**
     * 获取店铺信息(基础信息 + 地址信息)
     * @param qto
     * @return
     */
    BbcShopVO.InnerDetailVO innerDetailShop(BbcShopQTO.InnerShopQTO qto);

    /**
     * 多个店铺的详情(基础信息 + 地址信息)
     * @param qto
     * @return
     */
    List<BbcShopVO.InnerDetailVO> innerListDetailShop(BbcShopQTO.InnerListShopQTO qto);

    /**
     * 一个商家有哪些店铺
     * @param dto
     * @return
     */
    List<BbcShopVO.ShopIdName> innerListShopIdName(BbcShopDTO.MerchantIdDTO dto);

    /**
     * 一个店铺有哪些自定分类
     * @param dto
     * @return
     */
    List<BbcShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto);

    BbcShopVO.isCity isCity(BbcShopDTO.isCity dto);
}