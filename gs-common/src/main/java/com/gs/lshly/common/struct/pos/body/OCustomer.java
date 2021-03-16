package com.gs.lshly.common.struct.pos.body;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OCustomer {
    /** 顾客id */
    private String uuid;
    /** 顾客名称 */
    private String name;
    /** 顾客手机号 */
    private String mobile;
}
