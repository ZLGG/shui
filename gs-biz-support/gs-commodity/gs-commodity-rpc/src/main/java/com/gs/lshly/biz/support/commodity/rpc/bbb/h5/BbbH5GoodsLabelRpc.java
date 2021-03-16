package com.gs.lshly.biz.support.commodity.rpc.bbb.h5;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsLabelService;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsLabelQTO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsLabelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-11-11
*/
@DubboService
public class BbbH5GoodsLabelRpc implements IBbbH5GoodsLabelRpc {

    @Autowired
    private IBbbH5GoodsLabelService bbcGoodsLabelService;


    @Override
    public List<String> listGoodsId(BbbH5GoodsLabelQTO.QTO qto) {
        return bbcGoodsLabelService.listGoodsId(qto);
    }
}