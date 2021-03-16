package com.gs.lshly.biz.support.merchant.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopFloorVO;

public interface IBbbH5ShopFloorService {

    PageData<BbbH5ShopFloorVO.ListVO> pageData(BbbH5ShopFloorQTO.QTO qto);

}