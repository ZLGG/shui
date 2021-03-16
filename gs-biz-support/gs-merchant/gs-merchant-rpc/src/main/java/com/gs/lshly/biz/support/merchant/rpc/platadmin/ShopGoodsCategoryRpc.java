package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopGoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopGoodsCategoryVO;
import com.gs.lshly.biz.support.merchant.service.platadmin.IShopGoodsCategoryService;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-16
*/
@DubboService
public class ShopGoodsCategoryRpc implements IShopGoodsCategoryRpc{

    @Autowired
    private IShopGoodsCategoryService shopGoodsCategoryService;

    @Override
    public PageData<ShopGoodsCategoryVO.ListVO> pageData(ShopGoodsCategoryQTO.ShopIdQTO qto){
        return shopGoodsCategoryService.pageData(qto);
    }

    @Override
    public List<ShopGoodsCategoryVO.GoodsCategoryOneVO> list(ShopGoodsCategoryQTO.ShopIdQTO qto) {
        return shopGoodsCategoryService.list(qto);
    }

    @Override
    public List<String> listShopGoodsCategoryLv1(ShopGoodsCategoryQTO.ShopIdQTO qto) {
        return shopGoodsCategoryService.listShopGoodsCategoryLv1(qto);
    }

    @Override
    public void editShopGoodsCategoryPrice(ShopGoodsCategoryDTO.ListPriceETO eto) {
        shopGoodsCategoryService.editShopGoodsCategoryPrice(eto);
    }

    @Override
    public ShopGoodsCategoryVO.DetailVO detailShopGoodsCategory(ShopGoodsCategoryDTO.IdDTO dto){
        return shopGoodsCategoryService.detailShopGoodsCategory(dto);
    }

}