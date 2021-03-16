package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.UnbindCardResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 协议解约接口请求参数
 * @date Date : 2019年10月14日 10:45
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UnbindCardRequest extends BaseRequest<UnbindCardResponse> {

    /**
     * 业务类型
     */
    private String service = "UnbindCard";

    /**
     * 用户银行卡绑定号
     */
    private String bankBoundNo;

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
    public Class<UnbindCardResponse> getResponseClass() {
        return UnbindCardResponse.class;
    }
}
