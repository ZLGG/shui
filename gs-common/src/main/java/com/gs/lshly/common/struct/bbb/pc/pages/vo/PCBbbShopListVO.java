package com.gs.lshly.common.struct.bbb.pc.pages.vo;

import com.gs.lshly.common.enums.TrueFalseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-16
*/
public abstract class PCBbbShopListVO implements Serializable {

    @Data
    @ApiModel("PCBbbShopListVO.ShopInfoVo")
    @Accessors(chain = true)
    public static class ShopInfoVo implements Serializable{

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String id;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("是否收藏[0=否 1=是]")
        private Integer favoriteState = TrueFalseEnum.否.getCode();
    }


}
