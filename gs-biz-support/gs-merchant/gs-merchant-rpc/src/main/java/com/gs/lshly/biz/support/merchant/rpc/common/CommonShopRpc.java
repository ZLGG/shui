package com.gs.lshly.biz.support.merchant.rpc.common;

import com.gs.lshly.biz.support.merchant.service.common.ICommonShopService;
import com.gs.lshly.biz.support.merchant.service.platadmin.IShopService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
@DubboService
public class CommonShopRpc implements ICommonShopRpc {

    @Autowired
    private ICommonShopService commonShopService;

    @Override
    public CommonShopVO.SimpleVO shopDetails(String shopId) {
        return commonShopService.shopDetails(shopId);
    }

    @Override
    public List<CommonShopVO.ShopIdNameVO> moreDetailShop(List<String> shopIdList) {
        return commonShopService.moreDetailShop(shopIdList);
    }

    @Override
    public CommonShopVO.ShopIdNameVO oneDetailShop(String shopId) {
        return commonShopService.oneDetailShop(shopId);
    }

    @Override
    public List<CommonShopVO.SimpleVO> searchDetailShop(String shopFeatures) {
        return commonShopService.searchDetailShop(shopFeatures);
    }

    @Override
    public CommonShopVO.NavigationVO shopNavigation(String id2) {
        return commonShopService.shopNavigation(id2);
    }

    @Override
    public CommonShopVO.NavigationVO getNavigationVO(CommonShopDTO.NavigationByDTO dto) {
        return commonShopService.getNavigationVO(dto);
    }

    @Override
    public List<CommonShopVO.NavigationListVO> shopNavigation(CommonShopDTO.NavigationDTO dto) {
        return commonShopService.shopNavigation(dto);
    }

    @Override
    public CommonShopVO.MerchantVO merchantByShopId(String shopId) {
        return commonShopService.merchantByShopId(shopId);
    }

    @Override
    public CommonShopVO.MerchantVO merchantWithCategoryByShopId(String shopId) {
        return commonShopService.merchantWithCategoryByShopId(shopId);
    }

    @Override
    public List<CommonShopVO.SimpleDetailVO> listSimpleByShopIdList(List<String> shopIdList) {
        return commonShopService.listSimpleByShopIdList(shopIdList);
    }

    @Override
    public List<CommonShopVO.SimpleDetailVO> createQrCode(List<String> shopIdList) {
        return null;
    }

    @Override
    public String lakalaNoByShopId(String shopId) {
        return commonShopService.lakalaNoByShopId(shopId);
    }

    @Override
    public List<CommonShopVO.MerchantListVO> merchantList(BaseDTO dto) {
        return commonShopService.merchantList(dto);
    }



    @Override
    public List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> listLevelCategories(BaseDTO dto) {
        return commonShopService.listLevelCategories(dto);
    }

    @Override
    public CommonShopVO.ShopServiceVO getShopServiceVO(BaseDTO dto) {
        return commonShopService.getShopServiceVO(dto);
    }

    @Override
    public CommonShopVO.ShopServiceOutVO getShopService(String shopId) {
        return commonShopService.getShopService(shopId);
    }

    @Override
    public CommonShopVO.ShopGoodsCategoryVO innerGetGoodsCategoryVO(String shopId, String categoryName) {
        return commonShopService.innerGetGoodsCategoryVO(shopId, categoryName);
    }

    @Override
    public int checkShopNavigation(String shopId, String shopNavigationName, Integer usefiled) {
        return commonShopService.checkShopNavigation(shopId, shopNavigationName, usefiled);
    }

    @Override
    public CommonShopVO.SimpleVO innerServiceByShareCode(String shareCode) {
        return commonShopService.innerServiceByShareCode(shareCode);
    }

    @Override
    public List<String> shopNavigationIdList(String level1navigationId) {
        return commonShopService.shopNavigationIdList(level1navigationId);
    }


}