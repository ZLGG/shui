package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsCurrencyDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsCurrencyQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsCurrencyVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsCurrencyRpc;
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
@RequestMapping("/platform/settingsCurrency")
@Api(tags = "货币设置管理")
public class SettingsCurrencyController {

    @DubboReference
    private ISettingsCurrencyRpc settingsCurrencyRpc;

    @ApiOperation("货币设置详情")
    @GetMapping(value = "/detailCurrency")
    public ResponseData<SettingsCurrencyVO.DetailVO> detailCurrency() {
        return ResponseData.data(settingsCurrencyRpc.detailSettingsCurrency(new BaseDTO()));
    }

    @ApiOperation("货币设置编辑")
    @PostMapping("/editorCurrency")
    public ResponseData<Void> editorCurrency(@Valid @RequestBody SettingsCurrencyDTO.ETO dto) {
        settingsCurrencyRpc.addSettingsCurrency(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("货币精度设置详情")
    @GetMapping(value = "/detailStyle")
    public ResponseData<SettingsCurrencyVO.StyleDetailVO> detailStyle() {
        return ResponseData.data(settingsCurrencyRpc.detailStyle(new BaseDTO()));
    }

    @ApiOperation("货币精度设置编辑")
    @PostMapping("/editorStyle")
    public ResponseData<Void> editorStyle(@Valid @RequestBody SettingsCurrencyDTO.StyleETO dto) {
        settingsCurrencyRpc.editorStyle(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

}
