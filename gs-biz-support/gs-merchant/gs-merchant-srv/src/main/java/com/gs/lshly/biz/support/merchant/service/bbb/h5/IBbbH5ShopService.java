package com.gs.lshly.biz.support.merchant.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.dto.BbbH5ShopDTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockDeliveryVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import java.util.List;

public interface IBbbH5ShopService {

    PageData<BbbH5ShopVO.ListVO> pageData(BbbH5ShopQTO.QTO qto);

    BbbH5ShopVO.DetailVO detailShop(BbbH5ShopDTO.IdDTO dto);

    BbbH5ShopVO.ComplexVO shopComplex(BbbH5ShopDTO.IdDTO dto);

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> shopFloorGoods(BbbH5ShopQTO.FloorGoodsQTO qto);

    /**
     * 店铺详情
     * @param qto
     * @return
     */
    BbbH5ShopVO.InnerDetailVO innerDetailShop(BbbH5ShopQTO.InnerShopQTO qto);

    /**
     * 多个店铺的详情
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

    BbcShopVO.isCity SisCity(BbbH5ShopDTO.isCity dto);

    /**
     * 根据店铺名称获取店铺id+name
     * @param shopName
     * @return
     */
    List<String> innerGetShopIdList(String shopName);
}