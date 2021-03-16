package com.gs.lshly.biz.support.stock.enums;


import com.gs.lshly.common.enums.EnumMessage;
import io.swagger.annotations.ApiModelProperty;

/**
 * 运费模板类型
 * @author lxus
 * @since 2020-11-18
 */
public enum StockTemplateTypeEnum implements EnumMessage {

    卖家承担(10, "卖家承担"),
    计重(20, "计重"),
    计件(30, "计件"),
    金额范围(40, "金额范围");

    StockTemplateTypeEnum(Integer code, String remark){
        this.code = code;
        this.remark = remark;
    }

    private Integer code;

    private String remark;

    public static String text(Integer templateType) {
        for (StockTemplateTypeEnum e : StockTemplateTypeEnum.values()) {
            if (e.code.equals(templateType)) {
                return e.remark;
            }
        }
        return null;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
