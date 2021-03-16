package com.gs.lshly.common.struct.bbb.pc.user.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-12-09
*/
public abstract class PCBbbUserCardVO implements Serializable {

    @Data
    @ApiModel("PCBbbUserCardVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("优惠卷ID")
        private String cardId;

        @ApiModelProperty("活动卷类型[10=平台购物卷 20=商家优惠卷]")
        private Integer cardType;

        @ApiModelProperty("店铺ID")
        private String shopId;

    }

    @Data
    @ApiModel("PCBbbUserCardVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
