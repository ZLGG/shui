package com.citydo.appraisal.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zlg
 * @Description
 * @Date 2022/7/6 14:26
 */
@Data
public class StaffInfoVO {

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
    /**
     * 员工部门
     */
    @ApiModelProperty(value = "员工部门")
    private String staffOrg;
    /**
     * 员工职位
     */
    @ApiModelProperty(value = "员工职位")
    private String staffPosition;

    @ApiModelProperty(value = "表单名 管理体系绩效考核汇总表-营销")
    private String formName;

    @ApiModelProperty(value = "表单关联类型 subform_table_m_marketing")
    private String formTable;


    private String label;

    private String value;


}
