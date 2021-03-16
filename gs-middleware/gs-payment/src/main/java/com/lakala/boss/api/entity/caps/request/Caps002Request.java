package com.lakala.boss.api.entity.caps.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsRequest
 * @Description:
 * @date Date : 2019年12月28日 14:05
 */
@Data
@ToString
public class Caps002Request {
    /**
     * 资方商户编号
     */
    private String subMerchantId;
    /**
     * 调拨订单号
     */
    private String allocationOrderNo;
    /**
     * 调拨方向  0-现金账户调拨到代付账户
     * 1-代付账户调拨到现金账户
     */
    private String allocationFlg;
    /**
     * 调拨金额
     */
    private String allocationAmount;
    /**
     * 备用字段1
     */
    private String backUpField1;
    /**
     * 备用字段2
     */
    private String backUpField2;
    /**
     * 备用字段3
     */
    private String backUpField3;

}
