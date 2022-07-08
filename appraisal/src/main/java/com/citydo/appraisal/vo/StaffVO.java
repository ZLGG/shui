package com.citydo.appraisal.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zlg
 * @Description
 * @Date 2022/7/4 13:22
 */
@Data
public class StaffVO {

    @ApiModelProperty(value = "员工部门")
    private String staffOrg;

    @ApiModelProperty(value = "员工列表")
    private List<StaffInfoVO> staffList;


    private String label;

    private String value;

}
