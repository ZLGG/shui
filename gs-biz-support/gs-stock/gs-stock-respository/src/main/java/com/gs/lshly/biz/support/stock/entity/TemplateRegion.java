package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 运费金额地区表
 * </p>
 *
 * @author chenyang
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_template_region")
public class TemplateRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 运费模板id
     */
    private String templateId;

    /**
     * 运费金额id
     */
    private String templatePrcieId;

    /**
     * 省ID
     */
    private String regionId;

    /**
     * 省
     */
    private String regionName;

    /**
     * 最小地区层级[10=省 20=市 30=县]
     */
    private Integer regionLeve;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 逻辑删除标记
     */
    private Boolean flag;


}
