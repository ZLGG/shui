package com.gs.lshly.biz.support.merchant.rpc.bbc;
import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.dto.BbcShopDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopNavigationVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
@DubboService
public class BbcShopRpc implements IBbcShopRpc{

    @Autowired
    private IBbcShopService bbcShopService;

    @Override
    public PageData<BbcShopVO.ListVO> pageData(BbcShopQTO.QTO qto){
        return bbcShopService.pageData(qto);
    }

    @Override
    public PageData<BbcShopVO.ScopeListVO> nearShopListPageData(BbcShopQTO.ScopeQTO qto){
        return bbcShopService.scopePageData(qto);
    }

    @Override
    public List<BbcShopVO.ScopeListVO> nearShopList(BbcShopQTO.ScopeQTO qto) {
        return bbcShopService.nearShopList(qto);
    }

    @Override
    public BbcStockDeliveryVO.SupportDeliveryTypeVO supportDeliveryStyle(BbcStockDeliveryDTO.SupportDeliveryTypeDTO dto) {
        if (StringUtils.isBlank(dto.getShopId())) {
            throw new BusinessException("店铺参数不能为空");
        }
        return bbcShopService.supportDeliveryStyle(dto);
    }

    @Override
    public CommonDeliveryCostCalcParam getPickupDeliveryCostCalcParam(String shopId) {
        return bbcShopService.getPickupDeliveryCostCalcParam(shopId);
    }

    @Override
    public BbcShopVO.DetailVO detailShop(BbcShopDTO.IdDTO dto){

        return bbcShopService.detailShop(dto);

    }

    @Override
    public BbcShopVO.ComplexVO shopComplex(BbcShopDTO.IdDTO dto) {

        return bbcShopService.shopComplex(dto);
    }

    @Override
    public List<BbcShopVO.ShopNavVO> navigationTree(BbcShopDTO.IdDTO dto) {
        return bbcShopService.navigationTree(dto);
    }

    @Override
    public List<BbcShopNavigationVO.NavigationListVO> listNavigationListVO(BbcShopDTO.IdDTO dto) {
        return bbcShopService.listNavigationListVO(dto);
    }

    @Override
    public BbcShopVO.ShopIdName getShopIdName(BbcShopDTO.ShopNavigationIdDTO dto) {
        return bbcShopService.getShopIdName(dto);
    }

    @Override
    public BbcShopVO.InnerDetailVO innerDetailShop(BbcShopQTO.InnerShopQTO qto) {
        return bbcShopService.innerDetailShop(qto);
    }

    @Override
    public List<BbcShopVO.InnerDetailVO> innerListDetailShop(BbcShopQTO.InnerListShopQTO qto) {
        return bbcShopService.innerListDetailShop(qto);
    }

    @Override
    public List<BbcShopVO.ShopIdName> innerListShopIdName(BbcShopDTO.MerchantIdDTO dto) {
        return bbcShopService.innerListShopIdName(dto);
    }

    @Override
    public List<BbcShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto) {
        return bbcShopService.innerListShopNavigation(dto);
    }

    @Override
    public BbcShopVO.isCity isCity(BbcShopDTO.isCity dto) {
        return bbcShopService.isCity(dto);
    }

    @Override
    public List<String> innerGetNavigationList(String shopNavigationId) {
        return bbcShopService.innerGetNavigationList(shopNavigationId);
    }

    @Override
    public List<String> innerShopDelivery(String shopId) {
        return bbcShopService.innerShopDelivery(shopId);
    }


}