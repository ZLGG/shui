package com.gs.lshly.facade.platform.controller.user;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.OperatorTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataFeedbackDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataFeedbackQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataFeedbackVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataFeedbackRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fdfdf
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/platform/user/feedback")
@Api(tags = "会员意见反馈管理")
public class UserFeedbackController {

    @DubboReference
    private IDataFeedbackRpc dataMessageRpc;

    @ApiOperation("意见反馈列表")
    @GetMapping("")
    public ResponseData<PageData<DataFeedbackVO.ListVO>> pageData(DataFeedbackQTO.QTO qto) {
        qto.setOperatorType(OperatorTypeEnum.会员.getCode());
        return ResponseData.data(dataMessageRpc.pageData(qto));
    }

    @ApiOperation("意见反馈详情")
    @GetMapping(value = "/{id}")
    public ResponseData<DataFeedbackVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(dataMessageRpc.getDataFeedback(new DataFeedbackDTO.IdDTO(id)));
    }

    @ApiOperation("删除意见反馈")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> delete(@Valid @RequestBody DataFeedbackDTO.IdListDTO dto) {
        dataMessageRpc.deleteDataFeedback(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("处理意见反馈")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> handle(@PathVariable String id,@Valid @RequestBody DataFeedbackDTO.HanderDTO dto) {
        dto.setId(id);
        dataMessageRpc.handleDataFeedback(dto);
        return ResponseData.success(MsgConst.HANDER_SUCCESS);
    }


}
