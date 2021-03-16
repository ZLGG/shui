package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5ComplexHlyService;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5HlyQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5HlyVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5ComplexHlyRpc;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5ComplexHlyRpc;
import org.apache.dubbo.config.annotation.DubboService;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbbH5ComplexHlyRpc implements IBbbH5ComplexHlyRpc {

    private IBbbH5ComplexHlyService bbbH5ComplexHlyService;

    @Override
    public BbbH5HlyVO.TopComplexVO topComplex(BbbH5HlyQTO.QTO qto) {
        return bbbH5ComplexHlyService.topComplex(qto);
    }

}