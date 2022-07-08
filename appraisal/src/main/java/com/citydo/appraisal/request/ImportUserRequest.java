package com.citydo.appraisal.request;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zhangluguang
 * @description:
 * @date 2022/6/27  13:38
 */
@Data
public class ImportUserRequest {

    @ExcelProperty(value = "员工姓名", index = 0)
    private String staffName;

    @ExcelProperty(value = "员工工号", index = 1)
    private String staffCode;

    @ExcelProperty(value = "员工部门",index = 2)
    private String staffOrg;

    @ExcelProperty(value = "考核体系", index = 3)
    private String assessmentSys;

    @ExcelProperty(value = "员工职位", index = 4)
    private String staffPosition;

    @ExcelProperty(value = "一级审批人", index = 5)
    private String oneApprover;

    @ExcelProperty(value = "一级审批人工号", index = 6)
    private String oneApproverCode;

    @ExcelProperty(value = "二级审批人", index = 7)
    private String twoApprover;

    @ExcelProperty(value = "二级审批人工号", index = 8)
    private String twoApproverCode;

    @ExcelProperty(value = "三级审批人", index = 9)
    private String threeApprover;

    @ExcelProperty(value = "三级审批人工号", index = 10)
    private String threeApproverCode;

    @ExcelProperty(value = "权重", index = 11)
    private Integer weight;
}
