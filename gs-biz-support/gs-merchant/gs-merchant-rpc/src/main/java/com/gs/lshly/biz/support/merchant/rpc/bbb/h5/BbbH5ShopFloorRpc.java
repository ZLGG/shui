package com.gs.lshly.biz.support.merchant.rpc.bbb.h5;

import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopFloorService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopFloorQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopFloorVO;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopFloorRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-11-05
*/
@DubboService
public class BbbH5ShopFloorRpc implements IBbcShopFloorRpc{
    @Autowired
    private IBbcShopFloorService  bbcShopFloorService;

    @Override
    public PageData<BbcShopFloorVO.ListVO> pageData(BbcShopFloorQTO.QTO qto){
        return bbcShopFloorService.pageData(qto);
    }

}