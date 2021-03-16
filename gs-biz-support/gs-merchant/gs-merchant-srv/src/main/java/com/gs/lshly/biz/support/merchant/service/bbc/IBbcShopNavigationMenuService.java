package com.gs.lshly.biz.support.merchant.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopNavigationMenuQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopNavigationMenuVO;

public interface IBbcShopNavigationMenuService {

    PageData<BbcShopNavigationMenuVO.MenuListVO> pageData(BbcShopNavigationMenuQTO.QTO qto);

}