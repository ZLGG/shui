package com.gs.lshly.facade.platform.controller.foundation;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;
import com.gs.lshly.rpc.api.common.IRemindMerchantRpc;
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
* @author xxfc
* @since 2021-02-05
*/
@RestController
@RequestMapping("/platadmin/remindMerchant")
@Api(tags = "商家消息提醒管理")
public class RemindMerchantController {

    @DubboReference
    private IRemindMerchantRpc RemindMerchantRpc;

    @ApiOperation("商家消息提醒列表")
    @GetMapping("")
    public ResponseData<PageData<RemindMerchantVO.ListVO>> list(RemindMerchantQTO.QTO qto) {
        return ResponseData.data(RemindMerchantRpc.pageData(qto));
    }

    @ApiOperation("商家消息提醒详情")
    @GetMapping(value = "/{id}")
    public ResponseData<RemindMerchantVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(RemindMerchantRpc.detailRemindMerchant(new RemindMerchantDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家消息提醒")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody RemindMerchantDTO.ETO dto) {
            RemindMerchantRpc.addRemindMerchant(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

//    @ApiOperation("删除商家消息提醒")
//    @DeleteMapping(value = "/{id}")
//    public ResponseData<Void> delete(@PathVariable String id) {
//        RemindMerchantDTO.IdDTO dto = new RemindMerchantDTO.IdDTO(id);
//        RemindMerchantRpc.deleteRemindMerchant(dto);
//        return ResponseData.success(MsgConst.DELETE_SUCCESS);
//    }
//
//
//    @ApiOperation("修改商家消息提醒")
//    @PutMapping(value = "/{id}")
//    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody RemindMerchantDTO.ETO eto) {
//        RemindMerchantRpc.editRemindMerchant(id,eto);
//        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
//    }

}
