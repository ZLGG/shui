package com.gs.lshly.common.struct.platadmin.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-14
*/
public abstract class ShopTypeDictVO implements Serializable {

    @Data
    @ApiModel("ShopTypeDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("店铺类型名[品牌旗舰店 品牌专卖店 类目专营店 运营商自营 多品类通用型")
        private String typeName;


        @ApiModelProperty("店铺类型枚举编号[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer typeCode;


        @ApiModelProperty("类型描述")
        private String typeDesc;


        @ApiModelProperty("状态[10=可用 20=禁用]")
        private Integer state;


        @ApiModelProperty("是否排它[10=否 20=是]")
        private Integer isMutex;


        @ApiModelProperty("商品数量上限")
        private Integer goodsMax;


        @ApiModelProperty("商家保证金")
        private BigDecimal bail;

        @ApiModelProperty("商家保证金告警")
        private BigDecimal bailDown;

        @ApiModelProperty("店铺名后缀")
        private String suffixName;




    }

    @Data
    @ApiModel("ShopTypeDictVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
