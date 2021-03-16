package com.gs.lshly.biz.support.merchant.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopBannerQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopBannerVO;

public interface IBbbH5ShopBannerService {

    PageData<BbbH5ShopBannerVO.ListVO> pageData(BbbH5ShopBannerQTO.QTO qto);


}