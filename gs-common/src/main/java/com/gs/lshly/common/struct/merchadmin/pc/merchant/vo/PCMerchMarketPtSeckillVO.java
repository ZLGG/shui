package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 上午10:32:05
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("秒杀ID")
        private String seckillId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("秒杀名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String seckillDescribe;

        @ApiModelProperty("状态[10=审核 20=未审核 30=审核驳回]")
        private String  state;

        @ApiModelProperty("审核驳回原因")
        private String reasonsForRejection;

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
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
