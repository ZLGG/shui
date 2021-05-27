package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.*;
import com.gs.lshly.common.struct.platadmin.foundation.vo.*;
import com.gs.lshly.rpc.api.platadmin.foundation.*;
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
 * @author 陈奇
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/platform/settings")
@Api(tags = "基本设置管理")
public class SettingsController {

    @DubboReference
    private ISettingsRpc settingsRpc;

    @DubboReference
    private ISettingsTradeRpc settingsTradeRpc;

    @DubboReference
    private ISettingsReportRpc settingsReportRpc;

    @DubboReference
    private ISettingsReceiptRpc settingsReceiptRpc;

    @DubboReference
    private ISettingsIntegralRpc settingsIntegralRpc;

    @DubboReference
    private ISettingsCurrencyRpc settingsCurrencyRpc;

    @DubboReference
    private ISettingsWmsInterfaceRpc settingsWmsInterfaceRpc;


    @ApiOperation("基本设置详情")
    @GetMapping("/detail")
    public ResponseData<SettingsVO.ListVO> detail() {
        return ResponseData.data(settingsRpc.detail(new BaseDTO()));
    }

    @ApiOperation("基本设置编辑")
    @PostMapping("/editor")
    public ResponseData<Void> editor(@Valid @RequestBody SettingsDTO.ETO dto) {
        settingsRpc.editorSettings(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }


    @ApiOperation("货到付款设置详情")
    @GetMapping("/detailGetPay")
    public ResponseData<SettingsVO.GetPayVO> detailGetPay() {
        return ResponseData.data(settingsRpc.detailGetPay(new BaseDTO()));
    }

    @ApiOperation("货到付款设置编辑")
    @PostMapping("/editorGetPay")
    public ResponseData<Void> editorGetPay(@Valid @RequestBody SettingsDTO.GetPayETO dto) {
        settingsRpc.editorGetPay(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("交易设置详情")
    @GetMapping(value = "/detailTrade")
    public ResponseData<SettingsTradeVO.DetailVO> detailTrade() {
        return ResponseData.data(settingsTradeRpc.detailSettingsTrade(new BaseDTO()));
    }

    @ApiOperation("交易设置编辑")
    @PostMapping("/editorTrade")
    public ResponseData<Void> editorTrade(@Valid @RequestBody SettingsTradeDTO.ETO dto) {
        settingsTradeRpc.addSettingsTrade(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("签到设置详情")
    @GetMapping(value = "/detailReport")
    public ResponseData<SettingsReportVO.DetailVO> detailReport() {
        return ResponseData.data(settingsReportRpc.detailSettingsReport(new BaseDTO()));
    }

    @ApiOperation("签到设置编辑")
    @PostMapping("/editorReport")
    public ResponseData<Void> editorReport(@Valid @RequestBody SettingsReportDTO.ETO dto) {
        settingsReportRpc.addSettingsReport(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


    @ApiOperation("平台收款账户详情")
    @GetMapping(value = "/detailReceipt")
    public ResponseData<SettingsReceiptVO.DetailVO> detailReceipt() {
        return ResponseData.data(settingsReceiptRpc.detailSettingsReceipt(new BaseDTO()));
    }

    @ApiOperation("平台收款账户编辑")
    @PostMapping("/editorReceipt")
    public ResponseData<Void> editorReceipt(@Valid @RequestBody SettingsReceiptDTO.ETO dto) {
        settingsReceiptRpc.addSettingsReceipt(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


    @ApiOperation("积分设置详情")
    @GetMapping(value = "/detailIntegral")
    public ResponseData<SettingsIntegralVO.DetailVO> detailIntegral() {
        return ResponseData.data(settingsIntegralRpc.detailSettingsIntegral(new BaseDTO()));
    }

    @ApiOperation("积分设置编辑")
    @PostMapping("/editorIntegral")
    public ResponseData<Void> editorIntegral(@Valid @RequestBody SettingsIntegralDTO.ETO dto) {
        settingsIntegralRpc.addSettingsIntegral(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


    @ApiOperation("WMS接口详情")
    @GetMapping(value = "/detailWms")
    public ResponseData<SettingsWmsInterfaceVO.DetailVO> detailWms() {
        return ResponseData.data(settingsWmsInterfaceRpc.detailSettingsWmsInterface(new BaseDTO()));
    }

    @ApiOperation("WMS接口编辑")
    @PostMapping("/editorWms")
    public ResponseData<Void> editorWms(@Valid @RequestBody SettingsWmsInterfaceDTO.ETO dto) {
        settingsWmsInterfaceRpc.addSettingsWmsInterface(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("售后设置")
    @PostMapping("/rightsSettings")
    public ResponseData<Void> rightsSettings(@Valid @RequestBody SettingsDTO.RightsSetting dto) {
        settingsRpc.rightsSettings(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("售后显示")
    @GetMapping("/rightsSettingsView")
    public ResponseData<SettingsVO.Rights> rightsSettingsView() {

        return ResponseData.data(settingsRpc.rightsSettingsView());
    }

    @ApiOperation("活动开售提醒")
    @PostMapping("/activityStartRemind")
    public ResponseData<Void> activityStartRemind(@Valid @RequestBody SettingsDTO.ActivityStartRemind dto) {
        settingsRpc.activityStartRemind(dto);
        return ResponseData.data(MsgConst.OPERATOR_SUCCESS);
    }


}
