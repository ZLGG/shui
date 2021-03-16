package com.gs.lshly.biz.support.merchant.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopBannerQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopBannerVO;

public interface IBbcShopBannerService {

    PageData<BbcShopBannerVO.ListVO> pageData(BbcShopBannerQTO.QTO qto);


}