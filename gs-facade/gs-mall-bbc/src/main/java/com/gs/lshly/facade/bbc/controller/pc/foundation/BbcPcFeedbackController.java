package com.gs.lshly.facade.bbc.controller.pc.foundation;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.user.dto.BbcDataFeedbackDTO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcDataFeedbackRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/bbc/pc/userCenter/feedback")
@Api(tags = "2C PC用户意见反馈")
public class BbcPcFeedbackController {


    @DubboReference
    private IBbcDataFeedbackRpc bbcDataFeedbackRpc;

    @ApiOperation("新增意见反馈")
    @PostMapping("")
    public ResponseData<Void> addDataFeedback(@Valid @RequestBody BbcDataFeedbackDTO.ETO dto) {
        bbcDataFeedbackRpc.addDataFeedback(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
}
