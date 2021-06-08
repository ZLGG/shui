package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeSettlementRpc;
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
* @author zzg
* @since 2020-10-24
*/
@RestController
@RequestMapping("/platadmin/settlement")
@Api(tags = "结算管理")
//@Module(code = "summarySettlement",parent = "finance",name = "结算汇总" ,index = 1)
public class SettlementController {

    @DubboReference
    private ITradeSettlementRpc iTradeSettlementRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @ApiOperation("商家列表")
    @GetMapping("/merchantList")
    //@Func(code = "view" ,name = "查看")
    public ResponseData<List<CommonShopVO.MerchantListVO>> merchantList() {
        return ResponseData.data(commonShopRpc.merchantList(new BaseDTO()));
    }

    @ApiOperation("结算数据汇总")
    @GetMapping("/summaryOrderData")
    //@Func(code = "add" ,name = "新增")
    public ResponseData summaryOrderData() {

        //此处将订单表数据根据商家汇总到结算表
        Boolean result=iTradeSettlementRpc.summaryOrderData();
        if(result){
            return ResponseData.success("结算数据汇总成功");
        }else{
            return ResponseData.fail("结算数据汇总失败");
        }
    }

    @ApiOperation("结算汇总表列表")
    @PostMapping("/list")
    //@Func(code = "view" ,name = "查看")
    public ResponseData<PageData<TradeSettlementVO.ListVO>> list(@RequestBody TradeSettlementQTO.settlementList qto) {
        return ResponseData.data(iTradeSettlementRpc.pageData(qto));
    }

    @ApiOperation("结算汇总表详情")
    @GetMapping(value = "/{id}")
    //@Func(code = "view" ,name = "查看")
    public ResponseData<TradeSettlementVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(iTradeSettlementRpc.detailTradeSettlement(new TradeSettlementDTO.IdOfDTO(id)));
    }

    @ApiOperation("新增结算汇总表")
    @PostMapping("")
    //@Func(code = "add" ,name = "新增")
    public ResponseData<Void> add(@Valid @RequestBody TradeSettlementDTO.ETO dto) {
        iTradeSettlementRpc.addTradeSettlement(dto);
        return ResponseData.data(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("导出结算汇总表")
    @Log(module = "结算汇总表管理", func = "导出结算汇总表")
    @GetMapping(value = "/export")
    //@Func(code = "export" ,name = "导出")
    public void export(TradeSettlementQTO.settlementList qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = iTradeSettlementRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("结算确认")
    @GetMapping("/settleConfirmation/{tradeCode}")
    //@Func(code = "edit" ,name = "修改")
    public ResponseData settleConfirmation(@PathVariable String tradeCode) {
        String result=iTradeSettlementRpc.settleConfirmation(tradeCode);
        if("结算清分业务已完成".equals(result)|| "结算清分业务已受理成功".equals(result)){
            return ResponseData.success(result);
        }else{
            return ResponseData.fail(result);
        }
    }

}
