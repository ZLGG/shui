package com.lakala.boss.api.entity.caps.body;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Jian Jang
 * @version V1.0.0
 * @Project: caps
 * @Package com.lakala.boss.api.entity.body
 * @Description: 资方头寸查询响应BODY
 * @date Date : 2019年12月25日 11:03
 */
@Data
@ToString
public class Caps001ResponseBody implements Serializable {
    private static final long serialVersionUID = 7352991927283542426L;

    List<Caps001Body> accountList;

}

@Data
@ToString
class Caps001Body implements Serializable {
    private static final long serialVersionUID = -4593635296855365865L;
    /***
     * 资方商户号
     */
    private String subMerchantId;
    /***
     * 资方商户名称
     */
    private String subMerchantIdName;
    /***
     * 账户总金额
     */
    private String totalAmt;

    /***
     * 账户可用余额
     */
    private String balance;
    /***
     * 账户冻结金额
     */
    private String frozedAmt;
    /***
     * 账户余额时间yyyyMMddHHMMSS
     */
    private String endTime;
}