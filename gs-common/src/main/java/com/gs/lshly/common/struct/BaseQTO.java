package com.gs.lshly.common.struct;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BaseQTO
 * @author lxus
 * @since 2020/09/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseQTO extends BaseDTO {

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 分页大小
     */
    private Integer pageSize = 10;


}
