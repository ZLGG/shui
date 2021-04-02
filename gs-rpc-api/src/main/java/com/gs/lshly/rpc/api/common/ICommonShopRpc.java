package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
public interface ICommonShopRpc {

    CommonShopVO.SimpleVO shopDetails(String shopId);

    List<CommonShopVO.ShopIdNameVO> moreDetailShop(List<String> shopIdList);

    CommonShopVO.ShopIdNameVO oneDetailShop(String shopId);

    List<CommonShopVO.SimpleVO> searchDetailShop(String shopFeatures);

    /**
     * 店铺二级分类，找 1-2级分类
     * @param id2
     * @return
     */
    CommonShopVO.NavigationVO shopNavigation(String id2);

    CommonShopVO.NavigationVO getNavigationVO(CommonShopDTO.NavigationByDTO dto);

    /**
     * 店铺二级分类，找 1-2级分类，区分2b,2c
     * @param dto
     * @return
     */
    List<CommonShopVO.NavigationListVO> shopNavigation(CommonShopDTO.NavigationDTO dto);

    /**
     * 店铺ID找商家信息
     * @param shopId
     * @return
     */
    CommonShopVO.MerchantVO merchantByShopId(String shopId);

    /**
     * 店铺ID找商家信息(如果是非自店铺，会同时返回店铺申请的所有一级商品分类)
     * @param shopId
     * @return
     */
    CommonShopVO.MerchantVO merchantWithCategoryByShopId(String shopId);


    /**
     * 简单店铺信息数组
     * @param shopIdList
     * @return
     */
    List<CommonShopVO.SimpleDetailVO> listSimpleByShopIdList(List<String> shopIdList);


    /**
     * 简单店铺信息数组
     * @param shopIdList
     * @return
     */
    List<CommonShopVO.SimpleDetailVO> createQrCode(List<String> shopIdList);


    /**
     * 获取商家的拉卡拉商户号
     * @param shopId
     * @return
     */
    String lakalaNoByShopId(String shopId);

    /**
     * 商家列表
     * @return
     */
    List<CommonShopVO.MerchantListVO> merchantList(BaseDTO dto);
    /**
     * 获取带店铺申请的一级类目
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> listLevelCategories(BaseDTO dto);

    /**
     * 登录店铺查询自已的客服信息
     * @param dto
     * @return
     */
    CommonShopVO.ShopServiceVO getShopServiceVO(BaseDTO dto);

    /**
     * 查询店铺客服信息
     * @return
     */
    CommonShopVO.ShopServiceOutVO getShopService(String id);

    /**
     * 查询店铺拥有的类目信息
     * @param shopId
     * @param categoryName
     * @return
     */
    CommonShopVO.ShopGoodsCategoryVO innerGetGoodsCategoryVO(String shopId, String categoryName);

    /**
     * 获取店铺申请类目信息
     * @param shopId
     * @return
     */
    CommonShopVO.ShopCategoryInfoVO innerShopCategoryInfoVO(String shopId);


    int checkShopNavigation(String shopId,String shopNavigationName,Integer usefiled);

    /**
     * 通过邀请码查询店铺信息
     * @param shareCode
     * @return
     */
    CommonShopVO.SimpleVO innerServiceByShareCode(String shareCode);

    /**
     * 通过一级店铺自定义类目id查询所有下面的子级类目列表
     * @param level1navigationId
     * @return
     */
    List<String> shopNavigationIdList(String level1navigationId);

    /**
     * 获取店铺信息
     * @param shopId
     * @return
     */
    CommonShopVO.ListVO innerShopInfo(String shopId);

    void visits(CommonShopDTO.VisitsDTO shopId);
}