package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.QueryCardBindResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 协议查询接口请求参数
 * @date Date : 2019年10月14日 10:26
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class QueryCardBindRequest extends BaseRequest<QueryCardBindResponse> {

    /**
     * 业务类型
     */
    private String service = "QueryCardBind";

    /**
     * 银行卡号
     */
    private String crdNo;

    /**
     * 签约协议类型
     * 00:快捷签约
     * 01:商业委托签约
     */
    private String contType;


    @Override
    public Class<QueryCardBindResponse> getResponseClass() {
        return QueryCardBindResponse.class;
    }
}
