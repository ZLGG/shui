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
public abstract class PCMerchPicturesQTO implements Serializable {

    @Data
    @ApiModel("PCMerchPicturesQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id(默认为-1:平台）")
        private String merchantId;

        @ApiModelProperty("分组id")
        private String groupId;

        @ApiModelProperty("图片名字")
        private String imageName;

        @ApiModelProperty(value = "排序字段 name=图片名称，time=时间")
        private String orderByProperty;

    }

    @Data
    @ApiModel("PCMerchPicturesQTO.GroupIdQTO")
    @Accessors(chain = true)
    public static class GroupIdQTO implements Serializable {

        @ApiModelProperty("分组id")
        private String groupId;

    }
}
