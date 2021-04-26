package com.gs.lshly.facade.bbc.controller.h5.commodity;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * IN会员商品
 *
 * 
 * @author yingjun
 * @date 2021年4月26日 下午2:19:34
 */
@RestController
@RequestMapping("/bbc/inmember")
@Api(tags = "IN会员商品-v1.1.0")
@SuppressWarnings("unchecked")
public class BbcInMemberGoodsInfoController {

    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;


	@ApiOperation("IN会员商品列表-v1.1.0")
    @GetMapping("/goodsinfo")
    public ResponseData<BbcGoodsCategoryVO.CtccHomeVO> pageInMemberGoodsInfo(BbcGoodsInfoQTO.InMemberGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.pageInMemberGoodsInfo(qto));
    }

}
