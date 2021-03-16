package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.QuerySubMercBalResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 余额查询接口请求参数
 * @date Date : 2019年10月14日 15:10
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class QuerySubMercBalRequest extends BaseRequest<QuerySubMercBalResponse> {

    /**
     * 业务类型
     */
    private String service = "QuerySubMercBal";

    /**
     * 子商户编号
     */
    private String subMerchantId;

    @Override
    public Class<QuerySubMercBalResponse> getResponseClass() {
        return QuerySubMercBalResponse.class;
    }
}
