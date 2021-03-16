package com.gs.lshly.rpc.api.bbc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopBannerQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopBannerVO;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IBbcShopBannerRpc {

    PageData<BbcShopBannerVO.ListVO> pageData(BbcShopBannerQTO.QTO qto);

}