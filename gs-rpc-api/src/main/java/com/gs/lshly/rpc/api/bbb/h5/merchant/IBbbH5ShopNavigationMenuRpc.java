package com.gs.lshly.rpc.api.bbb.h5.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopNavigationMenuQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopNavigationMenuVO;


/**
*
* @author xxfc
* @since 2020-11-05
*/
public interface IBbbH5ShopNavigationMenuRpc {

    PageData<BbbH5ShopNavigationMenuVO.MenuListVO> pageData(BbbH5ShopNavigationMenuQTO.QTO qto);

}