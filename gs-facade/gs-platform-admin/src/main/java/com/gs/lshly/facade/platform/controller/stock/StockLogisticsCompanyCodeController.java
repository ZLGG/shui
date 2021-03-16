package com.gs.lshly.facade.platform.controller.stock;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockKdniaoQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockKdniaoVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.stock.IStockKdniaoRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2021-1-29
*/
@RestController
@RequestMapping("/platadmin/stockLogisticsCompanyCode")
@Api(tags = "物流跟踪配置")
@Module(code = "logisticsTracking",parent = "logistics",name = "物流跟踪" ,index = 2)
public class StockLogisticsCompanyCodeController {

    @DubboReference
    private IStockKdniaoRpc StockKdniaoRpc;

    @ApiOperation("物流跟踪配置列表展示")
    @GetMapping("/listWatch")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<StockKdniaoVO.ListVO>> pageData(StockKdniaoQTO.QTO qto) {
        return ResponseData.data(StockKdniaoRpc.pageData(qto));
    }


    @ApiOperation("新增或着修改物流跟踪配置接口")
    @PostMapping("")
    @Func(code = "add" ,name = "新增")
    public ResponseData<Void> addStockKdniao(@Valid @RequestBody StockKdniaoDTO.ETO dto) {
        StockKdniaoRpc.addStockKdniao(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }



}
