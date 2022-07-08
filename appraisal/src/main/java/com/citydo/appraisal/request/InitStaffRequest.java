package com.citydo.appraisal.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zlg
 * @Description
 * @Date 2022/6/29 9:46
 */
@Data
public class InitStaffRequest {

    @ApiModelProperty(value = "初始化文件url")
    private String url;

    @ApiModelProperty(value = "初始化文件类型")
    private String ExcelType;
}
