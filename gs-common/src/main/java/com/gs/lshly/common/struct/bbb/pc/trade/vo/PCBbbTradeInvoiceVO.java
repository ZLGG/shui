package com.gs.lshly.common.struct.bbb.pc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author zst
* @since 2020-12-24
*/
public abstract class PCBbbTradeInvoiceVO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeInvoiceVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("店铺id")
        private String shopId;


        @ApiModelProperty("订单编号")
        private String tradeId;


        @ApiModelProperty("会员id")
        private String userId;


        @ApiModelProperty("会员名字")
        private String userName;


        @ApiModelProperty("发票类型")
        private Integer invoiceRise;


        @ApiModelProperty("发票抬头")
        private String invoiceName;


        @ApiModelProperty("发票号")
        private String invoiceNumber;


        @ApiModelProperty("税号")
        private String taxNumber;


        @ApiModelProperty("公司名称")
        private String firmName;


        @ApiModelProperty("注册地址")
        private String registerAddress;


        @ApiModelProperty("账户")
        private String accountNumber;


        @ApiModelProperty("电话")
        private String phone;


        @ApiModelProperty("开户行")
        private String openingBank;


        @ApiModelProperty("发票类型[10增值税普通发票 20增值税专用发票]")
        private Integer invoiceType;


        @ApiModelProperty("开票状态[10待开 20已开 30已寄出 40已完成]")
        private Integer invoiceStatus;


        @ApiModelProperty("物流公司代码")
        private String logisticsCode;


        @ApiModelProperty("是否默认发票[10默认 20普通]")
        private Integer isDefault;


        @ApiModelProperty("发票内容")
        private String invoiceContent;


        @ApiModelProperty("快递单号")
        private String expressNumber;


        @ApiModelProperty("联系人")
        private String contactsUser;


        @ApiModelProperty("联系人邮箱")
        private String contactsEmail;
    }

    @Data
    @ApiModel("PCBbbTradeInvoiceVO.DetailVO")
    public static class DetailVO extends ListVO {

    }


    @Data
    @ApiModel("PCBbbTradeInvoiceVO.AddressDateVO")
    @Accessors(chain = true)
    public static class AddressDateVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("会员id")
        private String userId;

        @ApiModelProperty("会员名字")
        private String userName;

        @ApiModelProperty("省")
        private String province;

        @ApiModelProperty("省代码")
        private String provinceCode;

        @ApiModelProperty("市")
        private String city;

        @ApiModelProperty("市代码")
        private String cityCode;

        @ApiModelProperty("区")
        private String county;

        @ApiModelProperty("区代码")
        private String countyCode;

        @ApiModelProperty("街道地址")
        private String street;

        @ApiModelProperty("邮政编码")
        private String postalCode;

        @ApiModelProperty("收货人姓名")
        private String consigneeName;

        @ApiModelProperty("收货人电话")
        private String consigneePhone;

        @ApiModelProperty("是否默认地址[10默认 20普通]")
        private Integer isDefault;
    }


    @Data
    @ApiModel("PCBbbTradeInvoiceVO.ApplyInvoiceVO")
    @Accessors(chain = true)
    public static class ApplyInvoiceVO implements Serializable{

        @ApiModelProperty("发票数据")
        private ListVO invoiceDate;

        @ApiModelProperty("寄送地址数据")
        private AddressDateVO addressDateVO;

    }

    @Data
    @ApiModel("PCBbbTradeInvoiceVO.ChooseVO")
    @Accessors(chain = true)
    public static class ChooseVO implements Serializable{

        @ApiModelProperty("发票数据")
        private ListVO invoiceDate;


    }

    @Data
    @ApiModel("PCBbbTradeInvoiceVO.clickBillingVO")
    @Accessors(chain = true)
    public static class clickBillingVO implements Serializable{

        @ApiModelProperty("发票模版信息")
        private PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceVO invoiceInfo;

        @ApiModelProperty("发票地址模版信息")
        private PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceAddressVO invoicAddressInfo;


        @Data
        @ApiModel("PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceAddressVO")
        @Accessors(chain = true)
        public static class clickBillingInvoiceAddressVO implements Serializable{
            @ApiModelProperty("发票地址模版ID")
            private String id;


            @ApiModelProperty("街道地址")
            private String street;


            @ApiModelProperty("区")
            private String county;

            @ApiModelProperty("邮政编码")
            private String postalCode;

            @ApiModelProperty("收货人姓名")
            private String consigneeName;

            @ApiModelProperty("收货人电话")
            private String consigneePhone;

        }


        @Data
        @ApiModel("PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceVO")
        @Accessors(chain = true)
        public static class clickBillingInvoiceVO implements Serializable{
            @ApiModelProperty("发票模版ID")
            private String id;


            @ApiModelProperty("发票模版类型")
            private Integer invoiceRise;


            @ApiModelProperty("发票模版抬头")
            private String invoiceName;

        }

    }
    @Data
    @ApiModel("PCBbbTradeInvoiceVO.invoiceContendVO")
    @Accessors(chain = true)
    public static class invoiceContendVO implements Serializable{
        /**
         * 店铺名称
         */
        @ApiModelProperty("店铺名称")
        private String shopName;

        /**
         * 商品名称
         */
        @ApiModelProperty("商品名称")
        private String goodsName;

        /**
         * 商品标题
         */
        @ApiModelProperty("商品标题")
        private String goodsTitle;

        /**
         * 商品货号
         */
        @ApiModelProperty("商品货号")
        private String goodsNo;


        /**
         * 格规值
         */
        @ApiModelProperty("格规值")
        private String skuSpecValue;

        /**
         * 数量
         */
        @ApiModelProperty("数量")
        private Integer quantity;

        /**
         * 销售价
         */
        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        /**
         * 优惠金额
         */
        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;

        /**
         * 支付总金额
         */
        @ApiModelProperty("支付总金额")
        private BigDecimal payAmount;

        /**
         * 所得积分
         */
        @ApiModelProperty("所得积分")
        private BigDecimal giftIntegral;

    }

}
