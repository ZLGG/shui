package com.citydo.appraisal.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考核体系(AppraisalSys)实体类
 *
 * @author makejava
 * @since 2022-07-07 10:41:43
 */
public class AppraisalSys implements Serializable {
    private static final long serialVersionUID = -90928292937537097L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 考核体系
     */
    private String assessmentSys;
    /**
     * 表单名称
     */
    private String formName;
    /**
     * 子表单组件名称
     */
    private String formKey;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssessmentSys() {
        return assessmentSys;
    }

    public void setAssessmentSys(String assessmentSys) {
        this.assessmentSys = assessmentSys;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

}
