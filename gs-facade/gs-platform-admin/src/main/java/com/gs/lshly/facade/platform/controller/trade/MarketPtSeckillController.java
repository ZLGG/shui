package com.gs.lshly.facade.platform.controller.trade;
import javax.validation.Valid;

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

/**
 * 平台秒杀秒杀管理
 *
 * 
 * @author yingjun
 * @date 2021年5月7日 上午10:30:05
 */
@RestController
@RequestMapping("/platadmin/marketPtSeckill")
@Api(tags = "平台秒杀管理-v1.1.0")
@Module(code = "seckill",parent = "marketing",name = "秒杀",index = 8)
@SuppressWarnings("unchecked")
public class MarketPtSeckillController {

    @DubboReference
    private IMarketPtSeckillRpc marketPtSeckillRpc;

 
	@ApiOperation("平台秒杀列表-v1.1.0")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<MarketPtSeckillVO.ListVO>> list(MarketPtSeckillQTO.QTO qto) {
        return ResponseData.data(marketPtSeckillRpc.pageData(qto));
    }

    @ApiOperation("平台秒杀详情-v1.1.0")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<MarketPtSeckillVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(marketPtSeckillRpc.detailMarketPtSeckill(new MarketPtSeckillDTO.IdDTO(id)));
    }

    @ApiOperation("新增平台秒杀-v1.1.0")
    @PostMapping("")
    @Func(code = "add",name = "增")
    public ResponseData<Void> add(@RequestBody MarketPtSeckillDTO.ETO dto) {
            marketPtSeckillRpc.addMarketPtSeckill(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("批量删除平台秒杀-v1.1.0")
    @PostMapping(value = "/deleteBatches")
    @Func(code = "delete",name = "删除")
    public ResponseData<Void> delete(@Valid @RequestBody  MarketPtSeckillDTO.IdListDTO dto) {
        marketPtSeckillRpc.deleteMarketPtSeckill(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改平台秒杀-v1.1.0")
    @PutMapping(value = "/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody MarketPtSeckillDTO.ETO eto) {
        eto.setId(id);
        marketPtSeckillRpc.editMarketPtSeckill(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    /**
     * 
     * @param eto
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
}
