package com.gs.lshly.biz.support.merchant.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopFloorQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopFloorVO;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopFloorRpc;
import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopFloorService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-11-05
*/
@DubboService
public class BbcShopFloorRpc implements IBbcShopFloorRpc{
    @Autowired
    private IBbcShopFloorService  bbcShopFloorService;

    @Override
    public PageData<BbcShopFloorVO.ListVO> pageData(BbcShopFloorQTO.QTO qto){
        return bbcShopFloorService.pageData(qto);
    }

}