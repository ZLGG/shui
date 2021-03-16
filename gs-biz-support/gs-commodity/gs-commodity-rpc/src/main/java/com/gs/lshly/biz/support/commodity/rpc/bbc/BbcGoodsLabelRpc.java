package com.gs.lshly.biz.support.commodity.rpc.bbc;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsLabelQTO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsLabelRpc;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsLabelService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-11-11
*/
@DubboService
public class BbcGoodsLabelRpc implements IBbcGoodsLabelRpc{
    @Autowired
    private IBbcGoodsLabelService  bbcGoodsLabelService;


    @Override
    public List<String> listGoodsId(BbcGoodsLabelQTO.QTO qto) {
        return bbcGoodsLabelService.listGoodsId(qto);
    }
}