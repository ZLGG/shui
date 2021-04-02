package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.biz.support.merchant.service.platadmin.IShopService;
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
public class ShopRpc implements IShopRpc{

    @Autowired
    private IShopService shopService;

    @Override
    public PageData<ShopVO.ListVO> pageListShop(ShopQTO.QTO qto){
        return shopService.pageListShop(qto);
    }


    @Override
    public void addSelftShop(ShopDTO.ETO dto) {
        shopService.addSelftShop(dto);
    }

    @Override
    public void editorSelftShop(ShopDTO.ETO dto) {
        shopService.editorSelftShop(dto);
    }

    /**
     * 开启店铺
     * @param dto
     */
    @Override
    public void closeShop(ShopDTO.IdDTO dto) {
        shopService.closeShop(dto);
    }

    /**
     * 关闭店铺
     * @param dto
     */
    @Override
    public void openShop(ShopDTO.IdDTO dto) {
        shopService.openShop(dto);
    }

    @Override
    public List<ShopVO.IdNameVO> selfShopList(ShopQTO.SelfShopQTO qto) {
        return shopService.selfShopList(qto);
    }

    @Override
    public ShopVO.SelfShopDetailVO selfShopDetails(ShopDTO.IdDTO dto) {

        return shopService.selfShopDetails(dto);
    }

    @Override
    public ShopVO.DetailVO shopDetails(ShopDTO.IdDTO dto) {
        return shopService.shopDetails(dto);
    }

    @Override
    public void editorShop(ShopDTO.ComplexETO dto) {
        shopService.editorShop(dto);
    }


    @Override
    public List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto, List<String> shopIdList) {
        return shopService.innerlistShopIdName(dto,shopIdList);
    }

    @Override
    public List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto,Integer shopType,Integer terminal) {

        return shopService.innerlistShopIdName(dto,shopType,terminal);
    }

    @Override
    public ShopVO.DetailVO innerByIdGetPosShopId(String shopId) {
        return shopService.innerByIdGetPosShopId(shopId);
    }

}