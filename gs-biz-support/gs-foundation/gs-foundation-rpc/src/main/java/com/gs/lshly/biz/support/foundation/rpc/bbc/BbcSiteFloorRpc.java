package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteFloorDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteFloorRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteFloorService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbcSiteFloorRpc implements IBbcSiteFloorRpc{
    @Autowired
    private IBbcSiteFloorService  bbcSiteFloorService;

    @Override
    public List<BbcSiteFloorVO.ListVO> list(BbcSiteFloorQTO.QTO qto){
        return bbcSiteFloorService.list(qto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> goodsMore(BbcSiteFloorQTO.GoodsMoreQTO qto) {
        return bbcSiteFloorService.goodsMore(qto);
    }

    @Override
    public List<BbcSiteFloorVO.List2VO> list2(BbcSiteFloorQTO.QTO qto) {
        return bbcSiteFloorService.list2(qto);
    }

    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> ListGoodsMax(BbcSiteFloorQTO.GoodsMaxQTO qto) {
        return bbcSiteFloorService.ListGoodsMax(qto);
    }



}