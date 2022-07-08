package com.citydo.appraisal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zlg
 * @Description
 * @Date 2022/7/7 10:08
 */
@Getter
@AllArgsConstructor
public enum AppraisalSysEnum {

    MANAGER_TECH("", "", "subform_table_m_marketing"),
//    MANAGER_TECH("", "", "subform_table_m_t"),
//    MANAGER_TECH("", "", "subform_table_m_b"),
//    MANAGER_TECH("", "", "subform_table_t_pma"),
//    MANAGER_TECH("", "", "subform_table_t_pmd"),
//    MANAGER_TECH("", "", "subform_table_t_sola"),
//    MANAGER_TECH("", "", "subform_table_t_sold"),
//    MANAGER_TECH("", "", "subform_table_t_ma"),
//    MANAGER_TECH("", "", "subform_table_t_ddh"),
//    MANAGER_TECH("", "", ""),
//    MANAGER_TECH("", "", ""),
//    MANAGER_TECH("", "", ""),
    ;

    private String type;

    private String title;

    private String table;

}
