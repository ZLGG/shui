package com.gs.lshly.biz.support.merchant.rpc.bbb.h5;

import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopNavigationMenuService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopNavigationMenuQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopNavigationMenuVO;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopNavigationMenuRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-11-05
*/
@DubboService
public class BbbH5ShopNavigationMenuRpc implements IBbcShopNavigationMenuRpc{

    @Autowired
    private IBbcShopNavigationMenuService  bbcShopNavigationMenuService;

    @Override
    public PageData<BbcShopNavigationMenuVO.MenuListVO> pageData(BbcShopNavigationMenuQTO.QTO qto){
        return bbcShopNavigationMenuService.pageData(qto);
    }

}