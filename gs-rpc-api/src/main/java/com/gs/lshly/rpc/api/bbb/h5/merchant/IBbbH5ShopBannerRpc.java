package com.gs.lshly.rpc.api.bbb.h5.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopBannerQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopBannerVO;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IBbbH5ShopBannerRpc {

    PageData<BbbH5ShopBannerVO.ListVO> pageData(BbbH5ShopBannerQTO.QTO qto);

}