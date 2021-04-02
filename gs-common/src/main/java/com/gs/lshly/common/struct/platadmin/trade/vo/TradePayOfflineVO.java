package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO
 *
 * @author tangxu
 * @version 1.0
 * @date 2020/12/11 10:12
 */
public class TradePayOfflineVO {

    @Data
    @ApiModel("VerifyOfflinePayVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "会员用户名",position = 2)
        private String userName;

        @ApiModelProperty(value = "转账金额",position = 3)
        private BigDecimal payAmount;

        @ApiModelProperty(value = "更新时间",position = 4)
        private LocalDateTime udate;

        @ApiModelProperty(value = "审核意见",position = 5)
        private String verifyRemark;

        @ApiModelProperty(value = "转账备注",position = 6)
        private String payRemark;

        @ApiModelProperty(value = "审核状态",position = 7)
        private Integer verifyState;

        @ApiModelProperty(value = "账户名",position = 8)
        private String accountName;

        @ApiModelProperty(value = "银行名称",position = 9)
        private String bankName;

        @ApiModelProperty(value = "银行账号",position = 10)
        private String accountNumber;

        @ApiModelProperty(value = "创建时间",position = 11)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "订单编号",position = 12)
        private String tradeId;

    }

    @Data
    @ApiModel("VerifyOfflinePayVO.ListVOExport")
    @Accessors(chain = true)
    public static class ListVOExport implements Serializable {

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "会员用户名",position = 2)
        private String userName;

        @ApiModelProperty(value = "转账金额",position = 3)
        private BigDecimal payAmount;

        @ApiModelProperty(value = "更新时间",position = 4)
        private LocalDateTime udate;

        @ApiModelProperty(value = "审核意见",position = 5)
        private String verifyRemark;

        @ApiModelProperty(value = "转账备注",position = 6)
        private String payRemark;

        @ApiModelProperty(value = "审核状态",position = 7)
        private String  verifyState;

        @ApiModelProperty(value = "账户名",position = 8)
        private String accountName;

        @ApiModelProperty(value = "银行名称",position = 9)
        private String bankName;

        @ApiModelProperty(value = "银行账号",position = 10)
        private String accountNumber;

        @ApiModelProperty(value = "创建时间",position = 11)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "订单编号",position = 12)
        private String tradeId;

    }

    @Data
    @ApiModel("VerifyOfflinePayVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable {

        @ApiModelProperty("订单信息")
        private Order order;

        @ApiModelProperty("订单信息")
        private Transfer transfer;

    }

    @Data
    @ApiModel("VerifyOfflinePayVO.Order")
    @Accessors(chain = true)
    public static class Order implements Serializable {

        @ApiModelProperty("订单号")
        private String tradeId;

        @ApiModelProperty("订单金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("邮费")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("订单备注")
        private String buyerRemark;

        @ApiModelProperty("收货地区")
        private String recvAddress;

        @ApiModelProperty("收货人")
        private String recvPersonName;
    }

    @Data
    @ApiModel("VerifyOfflinePayVO.Transfer")
    @Accessors(chain = true)
    public static class Transfer implements Serializable {

        @ApiModelProperty("转账订单号")
        private String tradeId;

        @ApiModelProperty("付款户名")
        private String accountName;

        @ApiModelProperty("转账金额")
        private BigDecimal payAmount;

        @ApiModelProperty("付款银行")
        private String bankName;

        @ApiModelProperty("转账备注")
        private String payRemark;

        @ApiModelProperty("审核说明")
        private String verifyRemark;

        @ApiModelProperty("凭证图片")
        private List<String> certificatePicture;
    }


    @Data
    @ApiModel("VerifyOfflinePayVO.Audit")
    @Accessors(chain = true)
    public static class Audit implements Serializable {

        @ApiModelProperty("凭证图片")
        private String certificatePicture;

        @ApiModelProperty("审核说明")
        private String verifyRemark;

    }
}
