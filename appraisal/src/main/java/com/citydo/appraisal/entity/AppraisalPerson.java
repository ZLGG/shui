package com.citydo.appraisal.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 考核人(AppraisalPerson)实体类
 *
 * @author makejava
 * @since 2022-07-06 11:37:47
 */
@Data
public class AppraisalPerson implements Serializable {
    private static final long serialVersionUID = 440017209773785667L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 考核人姓名
     */
    private String approverName;
    /**
     * 考核人工号
     */
    private String approverCode;
    /**
     * 手机号
     */
    private String mobile;
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
