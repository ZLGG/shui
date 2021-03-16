package com.gs.lshly.common.struct.merchadmin.h5.user.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2021-01-20
*/
public abstract class H5MerchUserPrivateUserDTO implements Serializable {

    @Data
    @ApiModel("H5MerchUserPrivateUserDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员等级")
        private Integer userLeve;

        @ApiModelProperty("会员类型ID")
        private String userTypeId;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String rejectWhy;

        @ApiModelProperty("商家私域会员管理状态[10=关联 20=解除]")
        private Integer bindState;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("H5MerchUserPrivateUserDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


    @Data
    @ApiModel("H5MerchUserPrivateUserDTO.ApplyDTO")
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class ApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty(value = "拒审原因")
        private String rejectWhy;

        @ApiModelProperty(value = "私域会员类型ID")
        private String userTypeId;


    }

}
