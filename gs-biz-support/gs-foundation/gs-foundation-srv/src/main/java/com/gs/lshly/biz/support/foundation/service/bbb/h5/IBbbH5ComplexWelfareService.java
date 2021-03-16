package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5WelfareQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5WelfareVO;

public interface IBbbH5ComplexWelfareService {

    BbbH5WelfareVO.TopComplexVO topComplex(BbbH5WelfareQTO.QTO qto);

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> floorGoodsQuery(BbbH5WelfareQTO.FloorGoodsQTO qto);

}