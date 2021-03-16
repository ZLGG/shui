package com.gs.lshly.common.struct.bbb.h5.user.vo;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-24
*/
public abstract class BbbH5UserPrivateUserVO implements Serializable {

    @Data
    @ApiModel("BbbH5UserPrivateUserVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

    }

    @Data
    @ApiModel("BbbH5UserPrivateUserVO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺id列表")
        private List<String> idList;
    }

}
