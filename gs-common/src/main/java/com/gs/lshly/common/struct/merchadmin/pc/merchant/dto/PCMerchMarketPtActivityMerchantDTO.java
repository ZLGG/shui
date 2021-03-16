package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class PCMerchMarketPtActivityMerchantDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("活动ID")
        private String activityId;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String shopId;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("描述")
        private String activityDescribe;

        @ApiModelProperty("状态[10=审核 20=未审核 30=审核驳回]")
        private String  state;

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
    @ApiModel("PCMerchMarketPtActivityMerchantDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商家参与记录id")
        private String id;
    }
    //通过店铺信息来查询活动记录
    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantDTO.isRecord")
    @AllArgsConstructor
    public static class isRecordDTO extends BaseDTO {
        private String  activityId;

        private String merchantId;

        private String shopId;
    }

    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantDTO.idRecordDTO")
    @AllArgsConstructor
    public static class idRecordDTO extends BaseDTO {
        @ApiModelProperty("活动记录id")
        private String  id;
    }
    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantDTO.idRecordRejectionDTO")
    @AllArgsConstructor
    public static class idRecordRejectionDTO extends BaseDTO {
        @ApiModelProperty("活动记录id")
        private String  id;
        @ApiModelProperty("审核驳回原因")
        private String  reasonsForRejection;
    }


}
