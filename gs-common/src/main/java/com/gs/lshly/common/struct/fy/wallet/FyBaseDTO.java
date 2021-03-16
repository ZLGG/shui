package com.gs.lshly.common.struct.fy.wallet;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 上午11:46
 */

@Data
@Accessors(chain = true)
public class FyBaseDTO<T> implements Serializable {

    @ApiModelProperty(value = "接口编号", required = true)
    private String action;

    @ApiModelProperty(value = "签名", required = true)
    private String signature;

    private T obj;

    @Data
    @ApiModel(value = "FyBaseDTO.DTO")
    @Accessors(chain = true)
    public static class DTO extends BaseDTO implements Serializable {
        @ApiModelProperty(value = "请求流水号", required = true)
        @NotBlank(message = "请求流水号不能为空")
        private String senderSsn;

        @ApiModelProperty(value = "商户编号")
        private String fuMerchantNum;

        @ApiModelProperty(value = "请求方保留域")
        private String reqReserved;

        @ApiModelProperty(value = "保留域")
        private String reserved;
    }
}
