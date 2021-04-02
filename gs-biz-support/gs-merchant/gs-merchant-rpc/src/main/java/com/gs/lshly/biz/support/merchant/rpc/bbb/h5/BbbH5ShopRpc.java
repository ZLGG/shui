package com.gs.lshly.biz.support.merchant.rpc.bbb.h5;
import com.gs.lshly.biz.support.merchant.service.bbb.h5.IBbbH5ShopService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.dto.BbbH5ShopDTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopNavigationVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
@DubboService
public class BbbH5ShopRpc implements IBbbH5ShopRpc {

    @Autowired
    private IBbbH5ShopService bbbH5ShopService;

    @Override
    public PageData<BbbH5ShopVO.ListVO> pageData(BbbH5ShopQTO.QTO qto) {
        return bbbH5ShopService.pageData(qto);
    }

    @Override
    public BbbH5ShopVO.DetailVO detailShop(BbbH5ShopDTO.IdDTO dto) {
        return bbbH5ShopService.detailShop(dto);
    }

    @Override
    public BbbH5ShopVO.ComplexVO shopComplex(BbbH5ShopDTO.IdDTO dto) {
        return bbbH5ShopService.shopComplex(dto);
    }

    @Override
    public  PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> shopFloorGoods(BbbH5ShopQTO.FloorGoodsQTO qto) {
        return bbbH5ShopService.shopFloorGoods(qto);
    }

    @Override
    public List<BbbH5ShopNavigationVO.NavigationListVO> listNavigationListVO(BbbH5ShopDTO.IdDTO dto) {
        return bbbH5ShopService.listNavigationListVO(dto);
    }

    @Override
    public BbbH5ShopVO.ShopIdName getShopIdName(BbbH5ShopDTO.ShopNavigationIdDTO dto) {
        return bbbH5ShopService.getShopIdName(dto);
    }

    @Override
    public BbbH5ShopVO.InnerDetailVO innerDetailShop(BbbH5ShopQTO.InnerShopQTO qto) {
        return bbbH5ShopService.innerDetailShop(qto);
    }

    @Override
    public List<BbbH5ShopVO.InnerDetailVO> innerListDetailShop(BbbH5ShopQTO.InnerListShopQTO qto) {
        return bbbH5ShopService.innerListDetailShop(qto);
    }

    @Override
    public List<BbbH5ShopVO.ShopIdName> innerListShopIdName(BbbH5ShopDTO.MerchantIdDTO dto) {
        return null;
    }

    @Override
    public List<BbbH5ShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto) {
        return null;
    }

    @Override
    public BbcShopVO.isCity isCity(BbbH5ShopDTO.isCity dto) {
        return bbbH5ShopService.SisCity(dto);
    }

    @Override
    public BbbH5ShopVO.ShopServiceVO shopService(BbbH5ShopDTO.IdDTO idDTO) {
        return null;
    }

    @Override
    public List<String> innerGetShopIdList(String shopName) {
        return bbbH5ShopService.innerGetShopIdList(shopName);
    }

    @Override
    public List<String> innerGetNavigationList(String shopNavigationId) {
        return bbbH5ShopService.innerGetNavigationList(shopNavigationId);
    }


}