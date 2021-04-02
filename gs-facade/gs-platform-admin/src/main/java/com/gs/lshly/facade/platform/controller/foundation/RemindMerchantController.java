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
@Api(tags = "平台对商家消息提醒管理（只是数据查询并非提醒）")
public class RemindMerchantController {

    @DubboReference
    private IRemindMerchantRpc RemindMerchantRpc;

    @ApiOperation("商家消息提醒列表")
    @GetMapping("")
    public ResponseData<PageData<RemindMerchantVO.ListVO>> list(RemindMerchantQTO.QTO qto) {
        return ResponseData.data(RemindMerchantRpc.platPageData(qto));
    }

//    @ApiOperation("商家消息提醒详情")
//    @GetMapping(value = "/{id}")
//    public ResponseData<RemindMerchantVO.DetailVO> get(@PathVariable String id) {
//        return ResponseData.data(RemindMerchantRpc.detailRemindMerchant(new RemindMerchantDTO.IdDTO(id)));
//    }

//    @ApiOperation("删除商家消息提醒")
//    @DeleteMapping(value = "/{id}")
//    public ResponseData<Void> delete(@PathVariable String id) {
//        RemindMerchantDTO.IdDTO dto = new RemindMerchantDTO.IdDTO(id);
//        RemindMerchantRpc.deleteRemindMerchant(dto);
//        return ResponseData.success(MsgConst.DELETE_SUCCESS);
//    }


}
