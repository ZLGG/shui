package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 对账文件下载接口返回参数
 * @date Date : 2019年10月14日 15:34
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntStatementFileResponse extends BaseResponse {

    private static final long serialVersionUID = 801819595162348602L;
    /**
     * 流水号
     */
    private String tradeNo;
    /**
     * 商户编号
     */
    private String merchantId;
    /**
     * 对账文件url
     */
    private String downloadUrl;
    /**
     * 文件mac
     */
    private String fileMac;
}
