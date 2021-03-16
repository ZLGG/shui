package com.gs.lshly.facade.merchant.controller.pc.stock;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockTemplateQTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockTemplateRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/merchant/stockTemplate")
@Api(tags = "运费模版主表管理")
@Module(code = "expressTemplate",parent = "transaction",name = "快递模板管理" ,index = 6)
public class PCMerchStockTemplateController {

    @DubboReference
    private IPCMerchStockTemplateRpc pcMerchStockTemplateRpc;

    @ApiOperation("运费模板列表")
    @GetMapping("")
    @Func(code = "view" ,name = "查看")
    public ResponseData<List<CommonStockTemplateVO.ListDetailVO>> list(PCMerchStockTemplateQTO.QTO qto) {
        return ResponseData.data(pcMerchStockTemplateRpc.detailList(qto));
    }

    @ApiOperation("保存编辑运费模版")
    @PutMapping(value = "/editor")
    @Func(code = "add" ,name = "新增")
    public ResponseData<Void> update(@Valid @RequestBody CommonStockTemplateDTO.ETO eto) {
        pcMerchStockTemplateRpc.editStockTemplate(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("运费模版查询详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view" ,name = "查看")
    public ResponseData<CommonStockTemplateVO.ListDetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchStockTemplateRpc.detailStockTemplate(new CommonStockTemplateDTO.IdDTO(id)));
    }

    @ApiOperation("运费模版删除")
    @DeleteMapping(value = "/{id}")
    @Func(code = "delete" ,name = "删除")
    public ResponseData<CommonStockTemplateVO.ListDetailVO> delete(@PathVariable String id) {
        pcMerchStockTemplateRpc.delete(new CommonStockTemplateDTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("启用的运费模板（id—name）")
    @GetMapping("/idNames")
    @Func(code = "enable" ,name = "启用")
    public  ResponseData<List<CommonStockTemplateVO.IdNameVO>> idNames(){
        return  ResponseData.data(pcMerchStockTemplateRpc.idNames(new BaseDTO()));
    }


}
