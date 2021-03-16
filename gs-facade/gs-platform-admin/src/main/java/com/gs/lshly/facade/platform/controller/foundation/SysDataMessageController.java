package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysDataMessageDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysDataMessageQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysDataMessageVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISysDataMessageRpc;
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
@RequestMapping("/platform/dataMessage")
@Api(tags = "站内消息管理")
public class SysDataMessageController {

    @DubboReference
    private ISysDataMessageRpc dataMessageRpc;

    @ApiOperation("站内消息列表")
    @GetMapping("")
    public ResponseData<PageData<SysDataMessageVO.ListVO>> list(SysDataMessageQTO.QTO qo) {
        return ResponseData.data(dataMessageRpc.list(qo));
    }

    @ApiOperation("新增站内消息")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody SysDataMessageDTO.ETO dto) {
        //新增站内消息,反馈内容必填
        dataMessageRpc.addSysDataMessage(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("站内消息详情")
    @GetMapping(value = "/{id}")
    public ResponseData<SysDataMessageVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(dataMessageRpc.getSysDataMessage(new SysDataMessageDTO.IdDTO(id)));
    }

    @ApiOperation("删除站内消息")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        SysDataMessageDTO.IdDTO dto = new SysDataMessageDTO.IdDTO(id);
        dataMessageRpc.deleteSysDataMessage(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
