package com.gs.lshly.facade.merchant.controller.pc.foundation;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
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
@RequestMapping("/merchant/remindMerchant")
@Api(tags = "商家消息提醒管理")
public class PCRemindMerchantController {

    @DubboReference
    private IRemindMerchantRpc remindMerchantRpc;

    @ApiOperation("商家消息提醒列表（只会查询未读状态的消息）")
    @GetMapping("")
    public ResponseData<PageData<RemindMerchantVO.ListVO>> list(RemindMerchantQTO.QTO qto) {
        return ResponseData.data(remindMerchantRpc.merchantPageData(qto));
    }

    @ApiOperation("商家消息提醒详情（接口会置消息状态为已读）")
    @GetMapping(value = "/{id}")
    public ResponseData<RemindMerchantVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(remindMerchantRpc.detailRemindMerchant(new RemindMerchantDTO.IdDTO(id)));
    }

//    @ApiOperation("删除商家消息提醒")
//    @DeleteMapping(value = "/{id}")
//    public ResponseData<Void> delete(@PathVariable String id) {
//        RemindMerchantDTO.IdDTO dto = new RemindMerchantDTO.IdDTO(id);
//        RemindMerchantRpc.deleteRemindMerchant(dto);
//        return ResponseData.success(MsgConst.DELETE_SUCCESS);
//    }


}
