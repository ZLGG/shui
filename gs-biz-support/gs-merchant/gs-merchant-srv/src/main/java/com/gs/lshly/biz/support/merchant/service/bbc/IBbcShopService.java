package com.gs.lshly.biz.support.merchant.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.dto.BbcShopDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopNavigationVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;

import java.util.List;

public interface IBbcShopService {

    PageData<BbcShopVO.ListVO> pageData(BbcShopQTO.QTO qto);

    PageData<BbcShopVO.ScopeListVO> scopePageData(BbcShopQTO.ScopeQTO qto);

    List<BbcShopVO.ScopeListVO> nearShopList(BbcShopQTO.ScopeQTO qto);

    BbcShopVO.DetailVO detailShop(BbcShopDTO.IdDTO dto);

    BbcShopVO.ComplexVO shopComplex(BbcShopDTO.IdDTO dto);

    BbcStockDeliveryVO.SupportDeliveryTypeVO supportDeliveryStyle(BbcStockDeliveryDTO.SupportDeliveryTypeDTO dto);


    List<BbcShopVO.ShopNavVO> navigationTree(BbcShopDTO.IdDTO dto);

    /**
     * 店铺详情
     * @param qto
     * @return
     */
    BbcShopVO.InnerDetailVO innerDetailShop(BbcShopQTO.InnerShopQTO qto);

    /**
     * 多个店铺的详情
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


    CommonDeliveryCostCalcParam getPickupDeliveryCostCalcParam(String shopId);

    BbcShopVO.isCity isCity(BbcShopDTO.isCity dto);

    /**
     * 根据店铺类目id获取店铺id+name
     * @param dto
     * @return
     */
    BbcShopVO.ShopIdName getShopIdName(BbcShopDTO.ShopNavigationIdDTO dto);

    /**
     * 2c店铺分类列表
     * @param dto
     * @return
     */
    List<BbcShopNavigationVO.NavigationListVO> listNavigationListVO(BbcShopDTO.IdDTO dto);


    /**
     * 内部服务
     * @param shopNavigationId
     * @return
     */
    List<String> innerGetNavigationList(String shopNavigationId);

    List<String> innerShopDelivery(String shopId);

}