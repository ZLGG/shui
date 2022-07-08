package com.citydo.appraisal.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 考核权重(AppraisalWeight)实体类
 *
 * @author makejava
 * @since 2022-07-06 11:37:48
 */
@Data
public class AppraisalWeight implements Serializable {
    private static final long serialVersionUID = 432098089211737190L;
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
     * 员工部门
     */
    private String staffOrg;
    /**
     * 考核体系
     */
    private String assessmentSys;
    /**
     * 权重
     */
    private Integer weight;
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
