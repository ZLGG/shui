package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeSettlementDetailRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2020-12-01
*/
@RestController
@RequestMapping("/platadmin/tradeSettlementDetail")
@Api(tags = "平台结算明细表管理")
public class SettlementDetailController {

    @DubboReference
    private ITradeSettlementDetailRpc TradeSettlementDetailRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @ApiOperation("商家列表")
    @GetMapping("/merchantList")
    @Func(code = "view" ,name = "查看")
    public ResponseData<List<CommonShopVO.MerchantListVO>> merchantList() {
        return ResponseData.data(commonShopRpc.merchantList(new BaseDTO()));
    }

    @ApiOperation("结算明细表列表")
    @PostMapping("list")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<TradeSettlementDetailVO.ListVO>> list(@RequestBody TradeSettlementDetailQTO.ListQTO qto) {
        return ResponseData.data(TradeSettlementDetailRpc.pageData(qto));
    }

    @ApiOperation("结算明细表详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view" ,name = "查看")
    public ResponseData<TradeSettlementDetailVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(TradeSettlementDetailRpc.detailTradeSettlementDetail(new TradeSettlementDetailDTO.IdDTO(id)));
    }

    @ApiOperation("新增结算明细表")
    @PostMapping("save")
    @Func(code = "add" ,name = "新增")
    public ResponseData<Void> add(@Valid @RequestBody TradeSettlementDetailDTO.ETO dto) {
        TradeSettlementDetailRpc.addTradeSettlementDetail(dto);
        return ResponseData.data(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("导出结算明细表")
    @Log(module = "导出结算明细表", func = "导出结算明细表")
    @GetMapping(value = "/export")
    @Func(code = "export" ,name = "导出")
    public void export(TradeSettlementDetailQTO.ListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = TradeSettlementDetailRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

}
