package com.gs.lshly.common.struct.platadmin.commodity.dto;
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
* @since 2020-10-29
*/
public abstract class GoodsAuditRecordDTO implements Serializable {

    @Data
    @ApiModel("GoodsAuditRecordDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("审核状态")
        private Integer state;

        @ApiModelProperty("审核拒绝原因")
        private String refuseRemark;

        @ApiModelProperty(value = "1:新增商品 2:更新商品")
        private Integer type;
    }

    @Data
    @ApiModel("GoodsAuditRecordDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String id;
    }


}
