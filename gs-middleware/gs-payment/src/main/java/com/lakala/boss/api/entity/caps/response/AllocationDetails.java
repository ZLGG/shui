package com.lakala.boss.api.entity.caps.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsResponse
 * @Description: 调拨明细
 * @date Date : 2019年12月28日 11:03
 */
@Data
@ToString
public class AllocationDetails {
    /**
     * 调拨订单号
     */
    private String allocationOrderNo;
    /**
     * 资方商户编号
     */
    private String subMerchantId;
    /**
     * 资方姓名
     */
    private String subMerchantIdName;
    /**
     * 调拨方向
     */
    private String allocationFlg;
    /**
     * 调拨结果
     */
    private String allocationResult;
    /**
     * 现金账户余额
     */
    private String cashBal;
    /**
     * 代付账户余额
     */
    private String capsBal;
    /**
     * 调拨完成时间
     */
    private String allocationEndTime;
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
