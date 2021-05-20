package com.gs.lshly.common.struct.test;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TradeTestMQDTO {

    String orderId;

    String value;

}
