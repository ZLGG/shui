package com.gs.lshly.facade.bbb.controller.h5.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.FeedbackOperatorTypeEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataFeedbackDTO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5DataFeedbackRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/bbb/h5/userCenter/feedback")
@Api(tags = "用户意见反馈",description = " ")
public class BbbH5UserFeedbackController {


    @DubboReference
    private IBbbH5DataFeedbackRpc bbbH5DataFeedbackRpc;

    @ApiOperation("意见反馈到平台(会员)")
    @PostMapping("")
    public ResponseData<Void> addDataFeedback(@Valid @RequestBody BbbH5DataFeedbackDTO.ETO dto) {
        dto.setFbType(FeedbackOperatorTypeEnum.会员.getCode());
        bbbH5DataFeedbackRpc.addDataFeedback(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
}
