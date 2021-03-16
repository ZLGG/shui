package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5ComplexWelfareService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5WelfareQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5WelfareVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5ComplexWelfareRpc;
import org.apache.dubbo.config.annotation.DubboService;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbbH5ComplexWelfareRpc implements IBbbH5ComplexWelfareRpc {

    private IBbbH5ComplexWelfareService bbbH5ComplexWelfareService;

    @Override
    public BbbH5WelfareVO.TopComplexVO topComplex(BbbH5WelfareQTO.QTO qto) {
        return bbbH5ComplexWelfareService.topComplex(qto);
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> floorGoodsQuery(BbbH5WelfareQTO.FloorGoodsQTO qto) {
        return bbbH5ComplexWelfareService.floorGoodsQuery(qto);
    }
}