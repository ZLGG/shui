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
 * @date 2020/12/22 下午5:40
 */
@Data
@Accessors(chain = true)
public class CancelAccountConsumeParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @Data
    @ApiModel("CancelAccountConsumeParamsDTO.DTO")
    @Accessors(chain = true)
    public static class DTO extends ETO implements Serializable {

        @ApiModelProperty(value = "虚拟账号")
        private String accountNo;

        @ApiModelProperty(value = "收款商户号: 如为非面签二类户则上 送“虚账号”； 如为一类户或面签二类 户则上送对应的“卡号")
        private String merchantNo;

        @ApiModelProperty(value = "交易类型:BS0013-撤销")
        private String tradeType;

        @ApiModelProperty(value = "产品代码:productType=02、03时 ： NH00001-标示机票购买 的专用商户的代码 NH00002-标示手机充值 、升舱等交易，使用普 通商户的代码 其他暂不支持productType=4、5时上 送银行产品编号")
        private String productNo;

        @ApiModelProperty(value = "产品类型:0-消费类 1-理财类 02-upop卡代收消费类 03-upop卡代收理财类 4-基金消费类 5-基金理财类 6-授信额度消费类 7-商户对公户 8-客户还款 9-积分消费 10-积分理财、 11-资金冻结消费类 12-资金冻结理财类 13-商户代销基金消费 类 14-余额+商户代销基金 消费 15-一类户或面签二类 户 17-组合消费 18-代扣消费")
        private String productType;

        @ApiModelProperty(value = "他行付款账户:productType=02、03、 17时需上送绑定卡")
        private String payAccountNo;

        @ApiModelProperty(value = "代扣金额:单位：元， productType为17 时必 填")
        private String collectionAmt;

        @ApiModelProperty(value = "是否要公安校验:0-要公安校验（默认） 1-不用公安校验 如消费账号为一类户和 面签二类户则上送 “1”； 如消费账号为非面签二 类户则上送“0”或空")
        private String allowUnidentify;
    }

    @Data
    @ApiModel("CancelAccountConsumeParamsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends FyBaseDTO.DTO implements Serializable {
        @ApiModelProperty(value = "消费订单号")
        @NotBlank(message = "消费订单号不能为空")
        private String orderNo;

        @ApiModelProperty(value = "交易金额:元 （18,2）")
        private String transAmt;

        @ApiModelProperty(value = "支付金额:单位元")
        private String payAmt;

        @ApiModelProperty(value = "积分金额:单位元，无积分送0.00")
        private String freeAmt;

        @ApiModelProperty(value = "说明:产品代码的中文描述")
        private String explain;

        @ApiModelProperty(value = "业务日期:清算及对账日期yyyyMMdd")
        @Pattern(regexp = "^[1-2][0-9][0-9][0-9][0-1]{0,1}[0-9][0-3]{0,1}[0-9]$", message = "业务日期格式错误，例：yyyyMMdd")
        private String settleTime;

        @ApiModelProperty(value = "产品名称")
        private String productName;
    }


}

