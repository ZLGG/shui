package com.citydo.appraisal.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zlg
 * @Description
 * @Date 2022/7/6 14:39
 */
@Data
public class AppraisalRecordRequest {


    @ApiModelProperty(value = "表单id")
    private Long formId;

    @ApiModelProperty(value = "考核人手机号")
    private String mobile;
    /**
     * 员工姓名
     */
    @ApiModelProperty(value = "员工姓名")
    private String staffName;
    /**
     * 员工工号
     */
    @ApiModelProperty(value = "员工工号")
    private String staffCode;
    /**
     * 考核体系
     */
    @ApiModelProperty(value = "考核体系")
    private String assessmentSys;

}
