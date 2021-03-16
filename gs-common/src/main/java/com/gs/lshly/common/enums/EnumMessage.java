package com.gs.lshly.common.enums;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/09/14
 */
public interface EnumMessage extends Serializable {

    Integer getCode();

    String getRemark();

    default String format(Integer code) {
        
        return "";
    }

}
