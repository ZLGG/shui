package com.gs.lshly.biz.support.merchant.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.BbbShopDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.qto.BbbShopQTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.biz.support.merchant.service.bbb.pc.IBbbShopService;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopListVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
@DubboService
public class BbbShopRpc implements IBbbShopRpc{

    @Autowired
    private IBbbShopService bbbShopService;

    @Override
    public PageData<PCBbbShopListVO.ShopInfoVo> pageData(BbbShopQTO.SearchQTO qto) {
        return bbbShopService.pageData(qto);
    }

    @Override
    public PCBbbShopHomeVO.ShopHomeVO index(BbbShopDTO.IdDTO dto){
        return bbbShopService.index(dto);
    }

    @Override
    public List<PCBbbShopHomeVO.FloorVO> floorFirstList(BbbShopDTO.IdDTO dto) {
        return bbbShopService.floorFirstList(dto);
    }

    @Override
    public List<PCBbbShopHomeVO.FloorListVO> floorList(BbbShopDTO.IdDTO dto) {
        return bbbShopService.floorList(dto);
    }

    @Override
    public  PCBbbShopHomeVO.FloorGoodsListVO floorGoodsList(BbbShopDTO.FloorIdDTO dto) {
        return bbbShopService.floorGoodsList(dto);
    }

    @Override
    public PageData<PCBbbHomeVO.ShopSearchInfo> shopSearchList(PCBbbHomeQTO.ShopSearchQTO qto) {
        return bbbShopService.shopSearchList(qto);
    }

    @Override
    public PCBbbShopHomeVO.ShopServiceVO shopService(BbbShopDTO.IdDTO dto) {
        return bbbShopService.shopService(dto);
    }

    @Override
    public BbbShopVO.ComplexDetailVO inneComplexDetailShop(String shopId,BaseDTO dto) {
        return bbbShopService.inneComplexDetailShop(shopId,dto);
    }

    @Override
    public BbbShopVO.ComplexDetailVO innerSimple(String shopId) {
        return bbbShopService.innerSimple(shopId);
    }

    @Override
    public BbbShopVO.ShopScoreDetailVO innerShopScoreDetailVO(String shopId, BaseDTO dto) {
        return bbbShopService.innerShopScoreDetailVO(shopId,dto);
    }

    @Override
    public List<BbbShopVO.ComplexDetailVO> innerListComplexDetailShop(List<String> shopIdList,BaseDTO dto) {
        return bbbShopService.innerListComplexDetailShop(shopIdList,dto);
    }

    @Override
    public List<BbbShopVO.ShopIdName> innerListShopIdName(BbbShopDTO.MerchantIdDTO dto) {
        return bbbShopService.innerListShopIdName(dto);
    }

    @Override
    public List<BbbShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto) {
        return bbbShopService.innerListShopNavigation(dto);
    }

    @Override
    public List<String> innerGetNavigationList(String shopNavigationId) {
        return bbbShopService.innerGetNavigationList(shopNavigationId);
    }

}