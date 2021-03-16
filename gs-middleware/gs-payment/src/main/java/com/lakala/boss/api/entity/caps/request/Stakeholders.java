package com.lakala.boss.api.entity.caps.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsRequest
 * @Description: 出资方列表
 * @date Date : 2019年12月28日 14:40
 */
@Data
@ToString
public class Stakeholders {
    /**
     * 顺序号
     */
    private String orderSeqNo;
    /**
     * 出资方商户编号
     */
    private String subMerchantId;
    /**
     * 占比额度
     */
    private String subPayAmount;
    /**
     * 是否承担手续费0-不承担
     * 1-承担
     */
    private String payFeeFlg;
    /**
     * 预留字段
     */
//    private String detailBackUpField;
}
