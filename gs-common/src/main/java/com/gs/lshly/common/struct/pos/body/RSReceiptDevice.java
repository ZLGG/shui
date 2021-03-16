package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModelProperty;

public class RSReceiptDevice {
    @ApiModelProperty("设备ID")
    private String deviceId;
    @ApiModelProperty("设备类型 硬件名称")
    private String deviceType;
    @ApiModelProperty("设备型号 硬件型号")
    private String deviceModel;
    @ApiModelProperty("Serial Number 序列号")
    private String sn;
    @ApiModelProperty("应用名称")
    private String softwareName = "千帆掌柜";
    @ApiModelProperty("应用规格")
    private String softwareSpec = "unknown";
    @ApiModelProperty("应用版本")
    private String softwareVersion = "unknown";
}