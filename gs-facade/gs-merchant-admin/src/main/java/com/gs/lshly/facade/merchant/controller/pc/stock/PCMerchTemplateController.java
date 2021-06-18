package com.gs.lshly.facade.merchant.controller.pc.stock;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockTemplateRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchTemplateRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 运费模版管理 controller
 * @author chenyang
 */
@RestController
@RequestMapping("/merchant/template")
@Api(tags = "运费模版管理-new")
public class PCMerchTemplateController {

    @DubboReference
    private IPCMerchTemplateRpc pcMerchTemplateRpc;

    @ApiOperation("新增运费模版")
    @PostMapping(value = "/add")
    public ResponseData<Void> addTemplate(@Valid @RequestBody CommonTemplateDTO.AddETO eto) {
        Boolean result = pcMerchTemplateRpc.addTemplate(eto);
        if(result){
           return ResponseData.success(MsgConst.SAVE_SUCCESS);
        }
        return ResponseData.success(MsgConst.SAVE_FAILURE);
    }

    @ApiOperation("编辑运费模版")
    @PostMapping(value = "/edit")
    public ResponseData<Void> editTemplate(@Valid @RequestBody CommonTemplateDTO.UpdateETO eto) {
        Boolean result = pcMerchTemplateRpc.editTemplate(eto);
        if(result){
            return ResponseData.success(MsgConst.SAVE_SUCCESS);
        }
        return ResponseData.success(MsgConst.SAVE_FAILURE);
    }
}
