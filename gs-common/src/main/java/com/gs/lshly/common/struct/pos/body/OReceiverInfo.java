package com.gs.lshly.common.struct.pos.body;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OReceiverInfo {

    /** 联系电话 */
    private String phone;
    /** 姓名 */
    private String name;
    /** 收货地址 */
    private String address;
}
