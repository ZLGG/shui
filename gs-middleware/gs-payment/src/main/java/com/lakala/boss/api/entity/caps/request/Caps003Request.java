package com.lakala.boss.api.entity.caps.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsRequest
 * @Description:  头寸调拨查询请求
 * @date Date : 2019年12月28日 10:37
 */
@Data
@ToString
public class Caps003Request {
    /**
     * 资方商户名称
     */
    private String subMerchantId;
    /**
     * 调拨日期
     */
    private String allocationDate;
    /**
     * 调拨订单号
     */
    private String allocationOrderNo;
    /**
     * 备注字段1
     */
    private String backUpField1;
    /**
     * 备注字段2
     */
    private String backUpField2;
    /**
     * 备注字段3
     */
    private String backUpField3;

}
