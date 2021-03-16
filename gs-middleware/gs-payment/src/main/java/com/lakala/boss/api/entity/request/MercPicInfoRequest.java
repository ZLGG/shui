package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.MercPicInfoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 图片上传通知接口请求参数
 * @date Date : 2019年10月14日 15:04
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class MercPicInfoRequest extends BaseRequest<MercPicInfoResponse> {


    /**
     * 业务类型
     */
    private String service="mercPicInfo";

    /**
     * 子商户编号 图片所属商户号
     */
    private String picMerchantId;

    /**
     * 图片服务器存放路径
     * 子商户图片在sftp服务器存放路径 路径+压缩包名
     */
    private String picPath;

    @Override
    public Class<MercPicInfoResponse> getResponseClass() {
        return MercPicInfoResponse.class;
    }
}
