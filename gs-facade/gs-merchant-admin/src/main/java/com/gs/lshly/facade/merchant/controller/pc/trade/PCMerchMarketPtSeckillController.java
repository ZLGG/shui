package com.gs.lshly.facade.merchant.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillMerchantRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 秒杀秒杀参与记录管理
 *
 * @author hanly
 * @date 2021年6月10日 上午10:11:04
 */
@RestController
@RequestMapping("/merchadmin/marketPtSeckill")
@Api(tags = "商家秒杀活动管理管理-v1.1.0")
@SuppressWarnings("unchecked")
public class PCMerchMarketPtSeckillController {

    @DubboReference
    private IPCMerchMarketPtSeckillRpc ipcMerchMarketPtSeckillRpc;

    @ApiOperation("秒杀活动列表-v1.1.0")
    @PostMapping("")
    public ResponseData<PageData<PCMerchMarketPtSeckillVO.ListVO>> seckillList(@Valid @RequestBody PCMerchMarketPtSeckillQTO.QTO qto) {
        return ResponseData.data(ipcMerchMarketPtSeckillRpc.seckillList(qto));
    }

    @ApiOperation("秒杀活动场次-v1.1.0")
    @GetMapping(value = "/{id}")
    public ResponseData<PageData<PCMerchMarketPtSeckillVO.SessionVO>> seckillTimeQuantum(PCMerchMarketPtSeckillQTO.IdQTO qto) {
        return ResponseData.data(ipcMerchMarketPtSeckillRpc.seckillTimeQuantum(qto));
    }

    @ApiOperation("可参加的spu商品列表(添加商品)-v1.1.0")
    @PostMapping("/allSpu")
    public ResponseData<PCMerchMarketPtSeckillVO.SpuVO> allSpu(@RequestBody PCMerchMarketPtSeckillQTO.AllSpuQTO qto) {
        return ResponseData.data(ipcMerchMarketPtSeckillRpc.allSpu(qto));
    }

    @ApiOperation("保存已报名spu商品-v1.1.0")
    @PostMapping("/addSpu")
    public ResponseData<Void> addSpuGoods(@RequestBody PCMerchMarketPtSeckillDTO.SpuIdETO dto) {
        ipcMerchMarketPtSeckillRpc.addSpuGoods(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("删除已报名spu商品-v1.1.0")
    @PostMapping("/delSpu")
    public ResponseData<Void> delSpu(@RequestBody PCMerchMarketPtSeckillDTO.SpuIdETO dto) {
        ipcMerchMarketPtSeckillRpc.delSpu(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("已报名商品列表-v1.1.0")
    @PostMapping("/spuGoods")
    public ResponseData<PageData<PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO>> seckillSpuGoods(@RequestBody PCMerchMarketPtSeckillQTO.SpuQTO qto) {
        return ResponseData.data(ipcMerchMarketPtSeckillRpc.seckillSpuGoods(qto));
    }








    @ApiOperation("保存已报名sku商品具体信息-v1.1.0")
    @PostMapping("/addSku")
    public ResponseData<Void> addSkuGoodsDetail(@RequestBody PCMerchMarketPtSeckillDTO.AddSeckillGoodsETO dto) {
        ipcMerchMarketPtSeckillRpc.addSkuGoodsDetail(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }
}
