package com.lakala.boss.api.entity.caps.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsResponse
 * @Description: 头寸调拨返回
 * @date Date : 2019年12月28日 14:09
 */
@Data
@ToString
public class Caps002Response {

    /**
     * 资方商户编号
     */
    private String subMerchantId;
    /**
     * 资方商户名称
     */
    private String subMerchantIdName;
    /**
     * 调拨结果S-调拨成功
     * F-调拨失败
     * P-调拨处理中
     */
    private String allocationResult;
    /**
     * 调拨后现金账户余额
     */
    private String cashBal;
    /**
     * 调拨后代付账户余额
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
