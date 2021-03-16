package com.gs.lshly.biz.support.merchant.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.BbbShopDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.qto.BbbShopQTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopListVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;

import java.util.List;

public interface IBbbShopService {

    PageData<PCBbbShopListVO.ShopInfoVo> pageData(BbbShopQTO.SearchQTO qto);


    PCBbbShopHomeVO.ShopHomeVO index(BbbShopDTO.IdDTO dto);


    List<PCBbbShopHomeVO.FloorVO> floorFirstList(BbbShopDTO.IdDTO dto);

    List<PCBbbShopHomeVO.FloorListVO> floorList(BbbShopDTO.IdDTO dto);

    PCBbbShopHomeVO.FloorGoodsListVO floorGoodsList(BbbShopDTO.FloorIdDTO dto);

    PageData<PCBbbHomeVO.ShopSearchInfo> shopSearchList(PCBbbHomeQTO.ShopSearchQTO qto);

    PCBbbShopHomeVO.ShopServiceVO shopService(BbbShopDTO.IdDTO dto);

    /**
     * 获取店铺信息(基础信息 + 地址信息)
     * @return
     */
    BbbShopVO.ComplexDetailVO inneComplexDetailShop(String shopId,BaseDTO dto);

    /**
     * 带评分信息的店铺信息
     * @return
     */
    BbbShopVO.ShopScoreDetailVO innerShopScoreDetailVO(String shopId, BaseDTO dto);

    /**
     * 多个店铺的详情(基础信息 + 地址信息)
     * @return
     */
    List<BbbShopVO.ComplexDetailVO> innerListComplexDetailShop(List<String> shopIdList,BaseDTO dto);

    /**
     * 一个商家有哪些店铺
     * @param dto
     * @return
     */
    List<BbbShopVO.ShopIdName> innerListShopIdName(BbbShopDTO.MerchantIdDTO dto);

    /**
     * 一个店铺有哪些自定分类
     * @param dto
     * @return
     */
    List<BbbShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto);

}