package com.gs.lshly.biz.support.merchant.service.common;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopGoodsCategoryVO;

import java.util.List;

public interface ICommonShopService {

    CommonShopVO.SimpleVO shopDetails(String shopId);

    List<CommonShopVO.ShopIdNameVO> moreDetailShop(List<String> shopIdList);

    CommonShopVO.ShopIdNameVO oneDetailShop(String shopId);
    /**
     * 模糊搜索 shopFeatures  可以是店铺名 或 店铺类型
     * @param shopFeatures
     * @return
     */
    List<CommonShopVO.SimpleVO> searchDetailShop(String shopFeatures);


    CommonShopVO.NavigationVO shopNavigation(String id2);

    CommonShopVO.NavigationVO getNavigationVO(CommonShopDTO.NavigationByDTO dto);

    List<CommonShopVO.NavigationListVO> shopNavigation(CommonShopDTO.NavigationDTO dto);

    CommonShopVO.MerchantVO merchantByShopId(String shopId);

    List<CommonShopVO.SimpleDetailVO> listSimpleByShopIdList(List<String> shopIdList);

    CommonShopVO.MerchantVO merchantWithCategoryByShopId(String shopId);

    String lakalaNoByShopId(String shopId);

    List<CommonShopVO.MerchantListVO> merchantList(BaseDTO dto);

    /**
     * 获取带店铺申请的一级类目
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> listLevelCategories(BaseDTO dto);

    //-----------------内部服务------------

    /**
     * 查询店铺拥有的类目信息
     * @param shopId
     * @param categoryName
     * @return
     */
    CommonShopVO.ShopGoodsCategoryVO innerGetGoodsCategoryVO(String shopId, String categoryName);

    int checkShopNavigation(String shopId,String shopNavigationName,Integer usefiled);
    /**
     * 查询店铺的客服信息
     * @param dto
     * @return
     */
    CommonShopVO.ShopServiceVO getShopServiceVO(BaseDTO dto);


    CommonShopVO.ShopServiceOutVO getShopService(String shopId);

    /**
     * 通过邀请码查询店铺信息
     * @param shareCode
     * @return
     */
    CommonShopVO.SimpleVO innerServiceByShareCode(String shareCode);


    /**
     * 通过店铺自定义类目id查询所有下面的子级类目列表
     * @param navigationId
     * @return
     */
    List<String> shopNavigationIdList(String navigationId);

    /**
     * 获取店铺信息
     * @param shopId
     * @return
     */
    CommonShopVO.ListVO innerShopInfo(String shopId);

    /**
     * 获取店铺申请类目信息
     * @param shopId
     * @return
     */
    CommonShopVO.ShopCategoryInfoVO innerShopCategoryInfoVO(String shopId);

    void visits(CommonShopDTO.VisitsDTO shopId);
}