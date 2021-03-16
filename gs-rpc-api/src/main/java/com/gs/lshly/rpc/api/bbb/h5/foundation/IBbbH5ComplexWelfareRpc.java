package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5WelfareQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5WelfareVO;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface IBbbH5ComplexWelfareRpc {

    BbbH5WelfareVO.TopComplexVO topComplex(BbbH5WelfareQTO.QTO qto);

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> floorGoodsQuery(BbbH5WelfareQTO.FloorGoodsQTO qto);

}