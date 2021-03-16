package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5ComplexHomeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5ComplexHomeQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5ComplexHomeVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5ComplexHomeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbbH5ComplexHomeRpc implements IBbbH5ComplexHomeRpc {

    @Autowired
    private IBbbH5ComplexHomeService bbbH5ComplexHomeService;


    @Override
    public BbbH5ComplexHomeVO.TopComplexVO topComplex(BbbH5ComplexHomeQTO.QTO qto) {
        return bbbH5ComplexHomeService.topComplex(qto);
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto) {
        return bbbH5ComplexHomeService.goodsList(qto);
    }
}