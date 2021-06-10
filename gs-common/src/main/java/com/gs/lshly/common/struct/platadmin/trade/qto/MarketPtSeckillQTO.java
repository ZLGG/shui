package com.gs.lshly.common.struct.platadmin.trade.qto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yingjun
 * @date 2021年5月7日 上午10:44:13
 */
@SuppressWarnings("serial")
public abstract class MarketPtSeckillQTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("MarketPtSeckillQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

/*        @ApiModelProperty(value = "状态[10=未审核 20=审核通过 30=审核未通过 40=全部]")
        private String check;

        @ApiModelProperty(value = "店铺名字")
        private String shopName;*/

        @ApiModelProperty(value = "关键字")
        private String acName;

        @ApiModelProperty("活动状态(10:未开始,20:进行,30:已结束)")
        private Integer state;

        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime endTime;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("MarketPtSeckillQTO.IdQTO")
    @Accessors(chain = true)
    public static class IdQTO implements Serializable {
        @ApiModelProperty("秒杀活动id")
        private String id;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("MarketPtSeckillQTO.QuantumQTO")
    @Accessors(chain = true)
    public static class QuantumQTO extends BaseQTO {
        @ApiModelProperty("秒杀活动id")
        private String id;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("MarketPtSeckillQTO.GoodsQTO")
    @Accessors(chain = true)
    public static class GoodsQTO extends BaseQTO {
        @ApiModelProperty("秒杀活动id")
        private String seckillId;

        @ApiModelProperty("场次id")
        private String timeQuanTumId;

        @ApiModelProperty("品牌id")
        private String brandId;

        @ApiModelProperty("类目id")
        private String categoryId;
    }


    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("MarketPtSeckillQTO.SkuGoodsQTO")
    @Accessors(chain = true)
    public static class SkuGoodsQTO implements Serializable {

        @ApiModelProperty("商家报名的spu商品表id")
        private String killSpuId;
    }
}
