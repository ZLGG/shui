package com.gs.lshly.facade.platform.controller.trade;

import javax.validation.Valid;

import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillTimeQuantumDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillTimeQuantumQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillTimeQuantumVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtSeckillMerchantRpc;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtSeckillTimeQuantumRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtSeckillRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 平台秒杀秒杀管理
 *
 * @author yingjun
 * @date 2021年5月7日 上午10:30:05
 */
@RestController
@RequestMapping("/platadmin/marketPtSeckill")
@Api(tags = "平台秒杀管理-v1.1.0")
@Module(code = "seckill", parent = "marketing", name = "秒杀", index = 3)
@SuppressWarnings("unchecked")
public class MarketPtSeckillController {

    @DubboReference
    private IMarketPtSeckillRpc marketPtSeckillRpc;

    @ApiOperation("平台秒杀列表-v1.1.0")
    @PostMapping("")
    @Func(code = "view", name = "查")
    public ResponseData<PageData<MarketPtSeckillVO.ActivityListVO>> list(@Valid @RequestBody MarketPtSeckillQTO.QTO qto) {
        return ResponseData.data(marketPtSeckillRpc.pageData(qto));
    }

    @ApiOperation("平台秒杀详情(编辑)-v1.1.0")
    @GetMapping(value = "/{id}")
    @Func(code = "view", name = "查")
    public ResponseData<MarketPtSeckillVO.ActivityVO> get(MarketPtSeckillQTO.IdQTO qto) {
        return ResponseData.data(marketPtSeckillRpc.detailMarketPtSeckill(qto));
    }

    @ApiOperation("保存平台秒杀-v1.1.0")
    @PostMapping("/add")
    @Func(code = "add", name = "增")
    public ResponseData<Void> addAndUpdate(@RequestBody MarketPtSeckillDTO.ETO dto) {
        marketPtSeckillRpc.addMarketPtSeckill(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("已报名商品spu列表-v1.1.0")
    @PostMapping("/spuGoods")
    @Func(code = "view", name = "查")
    public ResponseData<PageData<MarketPtSeckillVO.KillGoodsVO>> seckillGoods(@RequestBody MarketPtSeckillQTO.GoodsQTO qto) {
        return ResponseData.data(marketPtSeckillRpc.seckillGoods(qto));
    }

    @ApiOperation("已报名商品sku列表(编辑)-v1.1.0")
    @PostMapping("/skuGoods")
    @Func(code = "view", name = "查")
    public ResponseData<List<MarketPtSeckillVO.KillSkuGoods>> seckillSkuGoods(@RequestBody MarketPtSeckillQTO.SkuGoodsQTO qto) {
        return ResponseData.data(marketPtSeckillRpc.seckillSkuGoods(qto));
    }

    @ApiOperation("保存已报名的商品-v1.1.0")
    @PutMapping("/goods")
    public ResponseData<Void> saveKillGoods(@RequestBody MarketPtSeckillDTO.SeckillGoodsDTO dto) {
        marketPtSeckillRpc.saveKillGoods(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }


    @ApiOperation("秒杀活动时间段-v1.1.0")
    @GetMapping("/quantum/{id}")
    public ResponseData<PageData<MarketPtSeckillVO.QuantumSessionVO>> getKillQuanTum(MarketPtSeckillQTO.QuantumQTO dto) {
        return ResponseData.data(marketPtSeckillRpc.getKillQuanTum(dto));
    }
/*    @ApiOperation("批量删除平台秒杀-v1.1.0")
    @PostMapping(value = "/deleteBatches")
    @Func(code = "delete", name = "删除")
    public ResponseData<Void> delete(@Valid @RequestBody MarketPtSeckillDTO.IdListDTO dto) {
        marketPtSeckillRpc.deleteMarketPtSeckill(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }*/


/*    @ApiOperation("修改平台秒杀-v1.1.0")
    @PutMapping(value = "/{id}")
    @Func(code = "edit", name = "改")
    public ResponseData<Void> update() {
        eto.setId(id);
        marketPtSeckillRpc.editMarketPtSeckill(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }*/

/*    @ApiOperation("活动商家参与记录-v1.1.0")
    @GetMapping("/merchant")
    @Func(code = "view", name = "查")
    public ResponseData<PageData<MarketPtSeckillVO.ListVO>> merchankList(MarketPtSeckillQTO.QTO qto) {
        return ResponseData.data(marketPtSeckillRpc.pageData(qto));
    }*/
    /**
     *
     * @param
     * @return

     @ApiOperation("配置秒杀权限")
     @PutMapping(value = "/updateActivity")
     @Func(code = "edit",name = "改")
     public ResponseData<Void> updateActivity( @Valid @RequestBody MarketPtSeckillDTO.updateDTO eto) {
     marketPtSeckillRpc.updateSeckill(eto);
     return ResponseData.success(MsgConst.UPDATE_SUCCESS);
     }
     @ApiOperation("获取秒杀权限")
     @PutMapping(value = "/getActivity")
     @Func(code = "edit",name = "改")
     public ResponseData<MarketPtSeckillVO.updateDTO> getActivity() {
     return ResponseData.data(marketPtSeckillRpc.getActivity());
     }
     */
  /*  @ApiOperation("活动时间段列表-v1.1.0")
    @GetMapping("/time")
    @Func(code = "view", name = "查")
    public ResponseData<PageData<MarketPtSeckillTimeQuantumVO.ListVO>> pageTimeQuantumlist(MarketPtSeckillTimeQuantumQTO.QTO qto) {
        return ResponseData.data(marketPtSeckillTimeQuantumRpc.pageTimeQuantumlist(qto));
    }

    @ApiOperation("活动时间段详情-v1.1.0")
    @GetMapping("/time/{id}")
    @Func(code = "view", name = "查")
    public ResponseData<PageData<MarketPtSeckillTimeQuantumVO.ListVO>> timeQuantum(MarketPtSeckillTimeQuantumQTO.IdQTO qto) {
        return ResponseData.data(marketPtSeckillTimeQuantumRpc.timeQuantum(qto));
    }

    @ApiOperation("新增活动时间段-v1.1.0")
    @PostMapping("/time")
    @Func(code = "add", name = "增")
    public ResponseData<Void> addTimeQuantum(@RequestBody MarketPtSeckillTimeQuantumDTO.ETO dto) {
        marketPtSeckillTimeQuantumRpc.addTimeQuantum(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("修改活动时间段-v1.1.0")
    @PutMapping(value = "/time")
    @Func(code = "edit", name = "改")
    public ResponseData<Void> updateTimeQuantum(@Valid @RequestBody MarketPtSeckillTimeQuantumDTO.ETO dto) {
        marketPtSeckillTimeQuantumRpc.updateTimeQuantum(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    @ApiOperation("删除活动时间段-v1.1.0")
    @PostMapping(value = "/deleteTime")
    @Func(code = "delete", name = "删除")
    public ResponseData<Void> deleteTimeQuantum(MarketPtSeckillTimeQuantumDTO.IdDTO dto) {
        marketPtSeckillTimeQuantumRpc.deleteTimeQuantum(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }*/
}
