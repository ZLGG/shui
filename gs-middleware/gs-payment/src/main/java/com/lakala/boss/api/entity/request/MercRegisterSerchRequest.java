package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.MercRegisterSerchResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 进件查询接口请求参数
 * @date Date : 2019年10月14日 14:22
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class MercRegisterSerchRequest extends BaseRequest<MercRegisterSerchResponse> {

    /**
     * 业务类型
     */
    private String service = "MercRegisterSerch";

    /**
     * 商户进件流水号
     */
    private String orgiRequestId;

    /**
     * 请求时间 YYYYMMDDHHMISS
     */
    private String reqTime;

    @Override
    public Class<MercRegisterSerchResponse> getResponseClass() {
        return MercRegisterSerchResponse.class;
    }
}
