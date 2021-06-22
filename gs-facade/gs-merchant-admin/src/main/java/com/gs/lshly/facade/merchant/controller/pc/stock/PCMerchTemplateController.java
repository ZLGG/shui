package com.gs.lshly.facade.merchant.controller.pc.stock;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.common.struct.common.stock.TemplateVO;
import com.gs.lshly.common.struct.common.qto.TemplateQTO;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchTemplateRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 运费模版管理 controller
 *
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
        if (result) {
            return ResponseData.success(MsgConst.SAVE_SUCCESS);
        }
        return ResponseData.success(MsgConst.SAVE_FAILURE);
    }

    @ApiOperation("编辑运费模版")
    @PostMapping(value = "/edit")
    public ResponseData<Void> editTemplate(@Valid @RequestBody CommonTemplateDTO.UpdateETO eto) {
        Boolean result = pcMerchTemplateRpc.editTemplate(eto);
        if (result) {
            return ResponseData.success(MsgConst.SAVE_SUCCESS);
        }
        return ResponseData.success(MsgConst.SAVE_FAILURE);
    }

    @ApiOperation("运费模版详情")
    @GetMapping(value = "/detail/{id}")
    public ResponseData<TemplateVO.DetailVO> detail(@PathVariable("id") String id) {
        return ResponseData.data(pcMerchTemplateRpc.detail(id));
    }

    @ApiOperation("运费模版删除")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        Boolean result = pcMerchTemplateRpc.delete(id);
        if (result) {
            return ResponseData.success(MsgConst.DELETE_SUCCESS);
        } else {
            return ResponseData.success(MsgConst.DELETE_FAILURE);
        }
    }

    @ApiOperation("运费模板列表")
    @GetMapping("/list")
    public ResponseData<List<TemplateVO.ListVO>> list(TemplateQTO.QTO qto) {
        return ResponseData.data(pcMerchTemplateRpc.list(qto));
    }
}
