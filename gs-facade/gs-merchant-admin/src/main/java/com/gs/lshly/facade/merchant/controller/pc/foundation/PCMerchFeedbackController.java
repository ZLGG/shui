package com.gs.lshly.facade.merchant.controller.pc.foundation;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchFeedbackDTO;
import com.gs.lshly.rpc.api.merchadmin.pc.foundation.IPCMerchFeedbackRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/merchant/feedback")
@Api(tags = "商家意见反馈")
public class PCMerchFeedbackController {


    @DubboReference
    private IPCMerchFeedbackRpc pcMerchFeedbackRpc;

    @ApiOperation("新增意见反馈")
    @PostMapping("")
    public ResponseData<Void> addDataFeedback(@Valid @RequestBody PCMerchFeedbackDTO.ETO dto) {
        pcMerchFeedbackRpc.addDataFeedback(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
}
