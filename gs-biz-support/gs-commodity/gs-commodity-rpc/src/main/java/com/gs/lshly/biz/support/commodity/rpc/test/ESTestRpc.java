package com.gs.lshly.biz.support.commodity.rpc.test;

import com.gs.lshly.biz.support.commodity.service.test.IESTestSrv;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.rpc.api.test.IESTestRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class ESTestRpc implements IESTestRpc {

    @Autowired(required = false)
    IESTestSrv testSrv;

    @Override
    public void importGoodsInfoById(String goodsId) {
        testSrv.importGoodsInfoById(goodsId);
    }

    @Override
    public List<BbcGoodsInfoVO.GoodsListVO> searchByNameOrTitle(String name, String title) {
        return testSrv.searchByNameOrTitle(name, title);
    }
}
