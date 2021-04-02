package com.gs.lshly.rpc.api.bbb.h5.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.dto.BbbH5ShopDTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopNavigationVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockDeliveryVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.BbbShopDTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
public interface IBbbH5ShopRpc {

    PageData<BbbH5ShopVO.ListVO> pageData(BbbH5ShopQTO.QTO qto);

    BbbH5ShopVO.DetailVO detailShop(BbbH5ShopDTO.IdDTO dto);

    /**
     * 店铺组合信息
     * @param
     * @return
     */
    BbbH5ShopVO.ComplexVO shopComplex(BbbH5ShopDTO.IdDTO dto);

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> shopFloorGoods(BbbH5ShopQTO.FloorGoodsQTO qto);

    /**
     * 2B店铺分类列表
     * @param dto
     * @return
     */
    List<BbbH5ShopNavigationVO.NavigationListVO> listNavigationListVO(BbbH5ShopDTO.IdDTO dto);


    /**
     * 获取店铺id+name
     * @param dto
     * @return
     */
    BbbH5ShopVO.ShopIdName getShopIdName(BbbH5ShopDTO.ShopNavigationIdDTO dto);

    //--------------------内部服务接口-----------------------------

    /**
     * 获取店铺信息(基础信息 + 地址信息)
     * @param qto
     * @return
     */
    BbbH5ShopVO.InnerDetailVO innerDetailShop(BbbH5ShopQTO.InnerShopQTO qto);

    /**
     * 多个店铺的详情(基础信息 + 地址信息)
     * @param qto
     * @return
     */
    List<BbbH5ShopVO.InnerDetailVO> innerListDetailShop(BbbH5ShopQTO.InnerListShopQTO qto);

    /**
     * 一个商家有哪些店铺
     * @param dto
     * @return
     */
    List<BbbH5ShopVO.ShopIdName> innerListShopIdName(BbbH5ShopDTO.MerchantIdDTO dto);

    /**
     * 一个店铺有哪些自定分类
     * @param dto
     * @return
     */
    List<BbbH5ShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto);

    BbcShopVO.isCity isCity(BbbH5ShopDTO.isCity dto);

    BbbH5ShopVO.ShopServiceVO shopService(BbbH5ShopDTO.IdDTO idDTO);

    /**
     * 根据店铺名称获取店铺id+name
     * @param shopName
     * @return
     */
    List<String> innerGetShopIdList(String shopName);

    /**
     * 内部服务
     * @param shopNavigationId
     * @return
     */
    List<String> innerGetNavigationList(String shopNavigationId);
}