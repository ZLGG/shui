package com.gs.lshly.rpc.api.bbb.h5.foundation;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5ComplexHomeQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5ComplexHomeVO;

/**
*
* @author hyy
* @since 2020-11-04
*/
public interface IBbbH5ComplexHomeRpc {

    BbbH5ComplexHomeVO.TopComplexVO topComplex(BbbH5ComplexHomeQTO.QTO qto);

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto);
}