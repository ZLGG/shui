package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsPayMethodDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsPayMethodQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsPayMethodVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsPayMethodRpc;
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
* @author 陈奇
* @since 2020-10-12
*/
@RestController
@RequestMapping("/platform/settingsPayMethod")
@Api(tags = "支付方式设置管理")
public class SettingsPayMethodController {

    @DubboReference
    private ISettingsPayMethodRpc settingsPayMethodRpc;

    @ApiOperation("支付方式列表")
    @GetMapping("")
    public ResponseData<PageData<SettingsPayMethodVO.ListVO>> list(SettingsPayMethodQTO.QTO qto) {
        return ResponseData.data(settingsPayMethodRpc.pageData(qto));
    }

    @ApiOperation("支付方式详情")
    @GetMapping(value = "/{id}")
    public ResponseData<SettingsPayMethodVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(settingsPayMethodRpc.detailSettingsPayMethod(new SettingsPayMethodDTO.IdDTO(id)));
    }

    @ApiOperation("新增支付方式")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody SettingsPayMethodDTO.ETO dto) {
        settingsPayMethodRpc.addSettingsPayMethod(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("支付方式编辑")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> editor(@PathVariable String id,@Valid @RequestBody SettingsPayMethodDTO.ETO dto) {
        dto.setId(id);
        settingsPayMethodRpc.editor(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("支付方式删除")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody SettingsPayMethodDTO.IdListDTO dto) {
        settingsPayMethodRpc.deleteBatch(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
