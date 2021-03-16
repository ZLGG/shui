package com.gs.lshly.common.struct.bbb.pc.user.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-01-30
*/
public abstract class PCBbbUserPrivateUserLogDTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserPrivateUserLogDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("私域会员id(主表id)")
        private String privateUserId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("用户id")
        private String userId;

        @ApiModelProperty("用户名称")
        private String userName;

        @ApiModelProperty("当前状态")
        private Integer state;

        @ApiModelProperty("审核说明")
        private String remark;
    }

    @Data
    @ApiModel("PCBbbUserPrivateUserLogDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
