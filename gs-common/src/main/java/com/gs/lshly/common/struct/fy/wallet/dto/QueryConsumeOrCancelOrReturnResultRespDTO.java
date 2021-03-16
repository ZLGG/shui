package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午5:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "查询消费/撤销/退货结果响应信息")
public class QueryConsumeOrCancelOrReturnResultRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "交易状态:90-成功 99-失败 33异常")
    private String tradeStatus;

    @ApiModelProperty(value = "优惠金额:单位分")
    private String discountAmount;

}
