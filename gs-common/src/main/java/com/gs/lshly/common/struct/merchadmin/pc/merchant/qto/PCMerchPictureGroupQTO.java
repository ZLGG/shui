package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-22
*/
public abstract class PCMerchPictureGroupQTO implements Serializable {

    @Data
    @ApiModel("PCMerchPictureGroupQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("所属id（店铺id,-1为平台）")
        private String belongId;

        @ApiModelProperty("分组名称")
        private String groupValue;

    }


    @Data
    @ApiModel("PCMerchPictureGroupQTO.BelongIdQTO")
    public static class BelongIdQTO extends BaseQTO {

        @ApiModelProperty("所属id（店铺id,-1为平台）")
        private String belongId;

    }
}
