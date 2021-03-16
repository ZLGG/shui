package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业证照
 * </p>
 *
 * @author xxfc
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_corp_cert_dict")
public class CorpCertDict extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 证照名称
     */
    private String certName;

    /**
     * 是否必需上传
     */
    private Integer isNeed;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 逻辑删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
