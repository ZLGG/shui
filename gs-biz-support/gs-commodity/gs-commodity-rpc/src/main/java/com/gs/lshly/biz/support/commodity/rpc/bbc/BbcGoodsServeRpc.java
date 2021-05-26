package com.gs.lshly.biz.support.commodity.rpc.bbc;

import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsServeService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsServeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsServeQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsServeVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsServeRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsServeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class BbcGoodsServeRpc implements IBbcGoodsServeRpc {

    @Autowired
    private IBbcGoodsServeService goodsServeService;


    @Override
    public List<BbcGoodsServeVO.ListVO> getGoodsServeDetailByGoodsId(BbcGoodsServeQTO.GoodsInfoQTO qto) {
        return goodsServeService.getGoodsServeDetail(qto);
    }
}
