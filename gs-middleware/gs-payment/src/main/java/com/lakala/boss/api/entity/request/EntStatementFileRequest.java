package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.EntStatementFileResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 对账文件下载接口请求参数
 * @date Date : 2019年10月14日 15:35
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntStatementFileRequest extends BaseRequest<EntStatementFileResponse> {

    /**
     * 业务类型
     */
    private String service = "EntStatementFile";

    /**
     * 分账方商户编号
     */
    private String rcvMerchantId;

    /**
     * 对账文件下载申请日期 YYYYMMDD
     */
    private String acDate;

    @Override
    public Class<EntStatementFileResponse> getResponseClass() {
        return EntStatementFileResponse.class;
    }
}
