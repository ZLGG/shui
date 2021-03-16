package com.gs.lshly.common.struct.fy.wallet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午2:28
 */

@Data
@Accessors(chain = true)
public class FyBaseRespDTO<T> implements Serializable {

    @ApiModelProperty(value = "响应码：0001 时需要调用replyMsgCode确认验证码")
    private String respCode;

    @ApiModelProperty(value = "响应描述")
    private String respDesc;

    @ApiModelProperty(value = "签名")
    private String signature;

    @ApiModelProperty(value = "返回数据")
    private T obj;


    @Data
    @ApiModel(value = "FyBaseRespDTO.BaseRespDTO")
    @Accessors(chain = true)
    public static class BaseRespDTO implements Serializable {
        @ApiModelProperty(value = "请求流水号")
        private String senderSsn;

        @ApiModelProperty(value = "商户编号")
        private String fuMerchantNum;

        @ApiModelProperty(value = "请求方保留域")
        private String reqReserved;

        @ApiModelProperty(value = "保留域")
        private String reserved;

        @ApiModelProperty(value = "错误码")
        private String errorCode;

        @ApiModelProperty(value = "错误信息")
        private String errorMsg;
    }
}
