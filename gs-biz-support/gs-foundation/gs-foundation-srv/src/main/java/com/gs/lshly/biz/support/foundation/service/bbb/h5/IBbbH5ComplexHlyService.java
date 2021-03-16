package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5HlyQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5HlyVO;

public interface IBbbH5ComplexHlyService {

    BbbH5HlyVO.TopComplexVO topComplex(BbbH5HlyQTO.QTO qto);

}