package com.gs.lshly.facade.platform.controller.stock;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCorpQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCorpVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.stock.IStockLogisticsCorpRpc;
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
* @author zzg
* @since 2020-10-23
*/
@RestController
@RequestMapping("/platadmin/stockLogisticsCorp")
@Api(tags = "物流公司总表管理")
@Module(code = "logisticsCompany",parent = "logistics",name = "物流公司" ,index = 1)
public class StockLogisticsCorpController {

    @DubboReference
    private IStockLogisticsCorpRpc StockLogisticsCorpRpc;

    @ApiOperation("物流公司总表列表")
    @GetMapping("")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<StockLogisticsCorpVO.ListVO>> list(StockLogisticsCorpQTO.QTO qto) {
        return ResponseData.data(StockLogisticsCorpRpc.pageData(qto));
    }

    @ApiOperation("物流公司总表详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view" ,name = "查看")
    public ResponseData<StockLogisticsCorpVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(StockLogisticsCorpRpc.detailStockLogisticsCorp(new StockLogisticsCorpDTO.IdDTO(id)));
    }

    @ApiOperation("新增物流公司总表")
    @PostMapping("")
    @Func(code = "add" ,name = "新增")
    public ResponseData<Void> add(@Valid @RequestBody StockLogisticsCorpDTO.ETO dto) {
            StockLogisticsCorpRpc.addStockLogisticsCorp(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除物流公司总表")
    @DeleteMapping(value = "/{id}")
    @Func(code = "delete" ,name = "删除")
    public ResponseData<Void> delete(@PathVariable String id) {
        StockLogisticsCorpDTO.IdDTO dto = new StockLogisticsCorpDTO.IdDTO(id);
        StockLogisticsCorpRpc.deleteStockLogisticsCorp(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("批量删除物流公司总表")
    @DeleteMapping
    @Func(code = "delete" ,name = "删除")
    public  ResponseData<Void>  batchDelete(@RequestParam("ids") String idsStr){
        String[] split = idsStr.split(",");
        for (String id  : split) {
            StockLogisticsCorpRpc.deleteStockLogisticsCorp(new StockLogisticsCorpDTO.IdDTO(id));
        }

        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改物流公司总表")
    @PutMapping(value = "/{id}")
    @Func(code = "edit" ,name = "修改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody StockLogisticsCorpDTO.ETO eto) {
        eto.setId(id);
        StockLogisticsCorpRpc.editStockLogisticsCorp(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("初始化物流公司接口")
    @PostMapping("/initCompany")
    @Func(code = "edit" ,name = "修改")
    public ResponseData<Void> initializeLogisticsCompany(StockLogisticsCorpDTO.QTO dto){
        StockLogisticsCorpRpc.initializeLogisticsCompany(dto);
        return ResponseData.success("初始化物流公司成功");
    }


}
