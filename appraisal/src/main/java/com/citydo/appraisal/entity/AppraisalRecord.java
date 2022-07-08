package com.citydo.appraisal.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 考核记录(AppraisalRecord)实体类
 *
 * @author makejava
 * @since 2022-07-06 11:37:48
 */
@Data
public class AppraisalRecord implements Serializable {
    private static final long serialVersionUID = 466848288124519611L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工工号
     */
    private String staffCode;
    /**
     * 考核体系
     */
    private String assessmentSys;
    /**
     * 员工部门
     */
    private String staffOrg;
    /**
     * 员工职位
     */
    private String staffPosition;
    /**
     * 考核人
     */
    private String approver;
    /**
     * 考核人工号
     */
    private String approverCode;
    /**
     * 表单id
     */
    private Long formId;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 修改人
     */
    private String modifiedBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 逻辑删除 0未删除，1删除
     */
    private Boolean isDelete;


}
