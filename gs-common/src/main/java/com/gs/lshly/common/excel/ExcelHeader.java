package com.gs.lshly.common.excel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lxus
 */
@Data
public class ExcelHeader implements Serializable {

    String property;

    String title;

    Integer idx;

    String enumClassName;

}
