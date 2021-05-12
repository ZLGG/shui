package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 下午2:22:34
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillMerchantDTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchMarketPtSeckillMerchantDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("秒杀ID")
        private String seckillId;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String shopId;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;

        @ApiModelProperty("秒杀名称")
        private String name;

        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("描述")
        private String seckillDescribe;

        @ApiModelProperty("状态[10=审核 20=未审核 30=审核驳回]")
        private String  state;

        @ApiModelProperty("报名开始时间")
        private LocalDateTime signStartTime;

        @ApiModelProperty("报名结束时间")
        private LocalDateTime signEndTime;

        @ApiModelProperty("秒杀上线时间")
        private LocalDateTime onlineStartTime;

        @ApiModelProperty("开售开始时间")
        private LocalDateTime seckillStartTime;

        @ApiModelProperty("开售结束时间")
        private LocalDateTime seckillEndTime;

        @ApiModelProperty("会员限购数量上限")
        private Integer userBuyMax;

        @ApiModelProperty("店铺参加商品数上限")
        private Integer shopGoodsMax;

        @ApiModelProperty("秒杀封面图")
        private String coverImage;

        @ApiModelProperty("开销提醒提前分钟数")
        private Integer smsBefore;

        @ApiModelProperty("是否短信提醒[10=是 20=否]")
        private Integer smsIsTell;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchMarketPtSeckillMerchantDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商家参与记录id")
        private String id;
    }
    //通过店铺信息来查询秒杀记录
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchMarketPtSeckillMerchantDTO.isRecord")
    @AllArgsConstructor
    public static class isRecordDTO extends BaseDTO {
        private String  seckillId;

        private String merchantId;

        private String shopId;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchMarketPtSeckillMerchantDTO.idRecordDTO")
    @AllArgsConstructor
    public static class idRecordDTO extends BaseDTO {
        @ApiModelProperty("秒杀记录id")
        private String  id;
    }
    
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchMarketPtSeckillMerchantDTO.idRecordRejectionDTO")
    @AllArgsConstructor
    public static class idRecordRejectionDTO extends BaseDTO {
        @ApiModelProperty("秒杀记录id")
        private String  id;
        @ApiModelProperty("审核驳回原因")
        private String  reasonsForRejection;
    }


}
