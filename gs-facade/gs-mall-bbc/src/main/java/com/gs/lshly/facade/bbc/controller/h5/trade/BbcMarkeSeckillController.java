package com.gs.lshly.facade.bbc.controller.h5.trade;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketSeckillRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 秒杀活动
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午11:37:37
 */
@RestController
@RequestMapping("/bbc/h5")
@Api(tags = "秒杀活动-v1.1.0")
@SuppressWarnings("unchecked")
public class BbcMarkeSeckillController {

    @DubboReference
    private IBbcMarketSeckillRpc bbcMarketSeckillRpc;

    
	@ApiOperation("秒杀活动首页-v1.1.0")
    @GetMapping("/seckill")
    public ResponseData<PageData<BbcMarketSeckillVO.SeckillHome>> cutList(BbcMarketSeckillDTO.DTO dto) {
        return ResponseData.data(bbcMarketSeckillRpc.seckillHomeNew(dto));
    }
    
    @ApiOperation("秒杀活动商品分页加载-v1.1.0")
    @GetMapping("/seckill/pageGoods")
    public ResponseData<BbcMarketSeckillVO.SeckillGoodsVO> pageGoods(BbcMarketSeckillQTO.QTO qto) {
        return ResponseData.data(bbcMarketSeckillRpc.pageSeckillGoodsNew(qto));
    }

    
    @ApiOperation("秒杀商品详情页-v1.1.0")
    @GetMapping(value = "/seckill/getGoodsInfoDetail")
    public ResponseData<BbcGoodsInfoVO.DetailVO> get(BbcMarketSeckillQTO.DetailQTO qto) {
        return ResponseData.data(bbcMarketSeckillRpc.detailGoodsInfoNew(qto));
    }

}
