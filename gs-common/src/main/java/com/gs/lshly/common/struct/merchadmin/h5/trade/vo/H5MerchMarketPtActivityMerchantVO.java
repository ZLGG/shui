package com.gs.lshly.common.struct.merchadmin.h5.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-01
*/
public abstract class H5MerchMarketPtActivityMerchantVO implements Serializable {

    @Data
    @ApiModel("H5MerchMarketPtActivityMerchantVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("活动ID")
        private String activityId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("活动名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String describe;


        @ApiModelProperty("报名开始时间")
        private LocalDateTime signStartTime;


        @ApiModelProperty("报名结束时间")
        private LocalDateTime signEndTime;


        @ApiModelProperty("活动上线时间")
        private LocalDateTime onlineStartTime;


        @ApiModelProperty("开售开始时间")
        private LocalDateTime activityStartTime;


        @ApiModelProperty("开售结束时间")
        private LocalDateTime activityEndTime;


        @ApiModelProperty("会员限购数量上限")
        private Integer userBuyMax;


        @ApiModelProperty("店铺参加商品数上限")
        private Integer shopGoodsMax;


        @ApiModelProperty("活动封面图")
        private String coverImage;


        @ApiModelProperty("开销提醒提前分钟数")
        private Integer smsBefore;


        @ApiModelProperty("是否短信提醒[10=是 20=否]")
        private Integer smsIsTell;




    }

    @Data
    @ApiModel("H5MerchMarketPtActivityMerchantVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
