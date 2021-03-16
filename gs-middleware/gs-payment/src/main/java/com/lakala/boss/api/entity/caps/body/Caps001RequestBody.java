package com.lakala.boss.api.entity.caps.body;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : Jian Jang
 * @version V1.0.0
 * @Project: caps
 * @Package com.lakala.boss.api.entity
 * @Description: 资方头寸查询业务参数实体类
 * @date Date : 2019年12月25日 10:56
 */
@Data
@Builder
@ToString
public class Caps001RequestBody implements Serializable {

    private static final long serialVersionUID = -7236667946511659032L;
    /***
     * 子商户号，如果查询主商户的账号余额则传主商户号
     */
    private String subMerchantId;

}
