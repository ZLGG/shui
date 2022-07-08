package com.citydo.appraisal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zlg
 * @Description
 * @Date 2022/7/6 16:01
 */
@Getter
@AllArgsConstructor
public enum AppraisalLevelEnum {

    ONE_LEVEL((byte) 1, "一级考核人"),
    TWO_LEVEL((byte) 2, "二级考核人"),
    THREE_LEVEL((byte) 3, "三级考核人"),
    ;

    private Byte type;

    private String desc;

}
