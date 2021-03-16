package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5ComplexHomeQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5ComplexHomeVO;

public interface IBbbH5ComplexHomeService {

    BbbH5ComplexHomeVO.TopComplexVO topComplex(BbbH5ComplexHomeQTO.QTO qto);

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto);
}