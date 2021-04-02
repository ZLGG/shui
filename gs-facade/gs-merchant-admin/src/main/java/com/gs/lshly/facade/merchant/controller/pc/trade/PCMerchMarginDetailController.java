package com.gs.lshly.facade.merchant.controller.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMarketMarginDetailRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* <p>
*  前端控制器
* </p>
*
* @author zzg
* @since 2020-10-24
*/
@RestController
@RequestMapping("/merchadmin/marginDetail")
@Api(tags = "商家保证金详情")
public class PCMerchMarginDetailController {


    @DubboReference
    private IPCMarketMarginDetailRpc ipcMarketMarginDetailRpc;

    @ApiOperation("商家保证金列表")
    @GetMapping("/list")
    public ResponseData<PageData<TradeMarginDetailVO.ListVO>> list(TradeMarginDetailQTO.typeQTO qto) {
        return ResponseData.data(ipcMarketMarginDetailRpc.PageData(qto));
    }

    @ApiOperation("商家保证金详情")
    @GetMapping(value = "/{id}")
    public ResponseData<TradeMarginDetailVO.ListVO> get(@PathVariable String id) {
        return ResponseData.data(ipcMarketMarginDetailRpc.detailTradeMargin(new TradeMarginDetailDTO.IdDTO(id)));
    }

    @ApiOperation("商家自己保证金数据")
    @GetMapping(value = "view")
    public ResponseData<TradeMarginVO.ListVO> view(TradeMarginDetailQTO.viewQTO qto) {
        return ResponseData.data(ipcMarketMarginDetailRpc.view(qto));
    }
}
