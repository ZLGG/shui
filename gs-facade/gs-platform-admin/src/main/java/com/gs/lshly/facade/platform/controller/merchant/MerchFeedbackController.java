package com.gs.lshly.facade.platform.controller.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.OperatorTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataFeedbackDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataFeedbackQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataFeedbackVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
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
@RequestMapping("/platform/merchant/feedback")
@Api(tags = "商家意见反馈管理")
@Module(code = "feedback", parent = "bussiness", name = "意见反馈", index = 9)
public class MerchFeedbackController {

    @DubboReference
    private IDataFeedbackRpc dataMessageRpc;

    @ApiOperation("意见反馈列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<DataFeedbackVO.ListVO>> pageData(DataFeedbackQTO.QTO qto) {
        qto.setOperatorType(OperatorTypeEnum.商家.getCode());
        return ResponseData.data(dataMessageRpc.pageData(qto));
    }

    @ApiOperation("删除意见反馈")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody DataFeedbackDTO.IdListDTO dto) {
        dataMessageRpc.deleteDataFeedback(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("意见反馈详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<DataFeedbackVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(dataMessageRpc.getDataFeedback(new DataFeedbackDTO.IdDTO(id)));
    }

    @ApiOperation("处理意见反馈")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> handle(@PathVariable String id,@Valid @RequestBody DataFeedbackDTO.HanderDTO dto) {
        dto.setId(id);
        dataMessageRpc.handleDataFeedback(dto);
        return ResponseData.success(MsgConst.HANDER_SUCCESS);
    }

}
