package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午5:56
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "查询消费/撤销/退货结果请求信息")
public class QueryConsumeOrCancelOrReturnResultParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "原交易流水:回查交易需上送原交易请求流水", required = true)
    @NotBlank(message = "原交易流水不能为空")
    private String orFlowNo;

    @ApiModelProperty(value = "交易码:BS0012-人寿电商支付保费BS0013-人寿电商支付保费撤销 BS0015-批量消费的单笔撤销BS0024-单笔退货", required = true)
    @Pattern(regexp = "^[BS0012|BS0013|BS0015|BS0024]*$", message = "交易码错误")
    private String transCode;
}
