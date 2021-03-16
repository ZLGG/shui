package com.gs.lshly.biz.support.merchant.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopNavigationMenuQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopNavigationMenuVO;

public interface IBbbH5ShopNavigationMenuService {

    PageData<BbbH5ShopNavigationMenuVO.MenuListVO> pageData(BbbH5ShopNavigationMenuQTO.QTO qto);

}