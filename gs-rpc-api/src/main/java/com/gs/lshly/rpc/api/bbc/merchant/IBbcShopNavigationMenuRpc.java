package com.gs.lshly.rpc.api.bbc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopNavigationMenuQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopNavigationMenuVO;

/**
*
* @author xxfc
* @since 2020-11-05
*/
public interface IBbcShopNavigationMenuRpc {

    PageData<BbcShopNavigationMenuVO.MenuListVO> pageData(BbcShopNavigationMenuQTO.QTO qto);

}