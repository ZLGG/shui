package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5HlyQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5HlyVO;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface IBbbH5ComplexHlyRpc {

    BbbH5HlyVO.TopComplexVO topComplex(BbbH5HlyQTO.QTO qto);

}