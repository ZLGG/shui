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
 * @date 2020/12/22 下午2:33
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "查询余额响应信息")
public class QueryAccountBalanceRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "二三类户卡号可用余额:单位，分")
    private String currentAvaBalance;

    @ApiModelProperty(value = "活期余额:单位，分")
    private String currentBalance;

    @ApiModelProperty(value = "定期余额:单位，分")
    private String fixedBalance;

    @ApiModelProperty(value = "通知存款余额:单位，分")
    private String otherBalance;

    @ApiModelProperty(value = "二三类户卡号编号")
    private String vAccountNo;

    @ApiModelProperty(value = "二三类户卡号总余额:单位，分")
    private String eAccAmt;

    @ApiModelProperty(value = "二三类户卡号冻结余额:单位，分")
    private String eAccUnableAmt;
}
