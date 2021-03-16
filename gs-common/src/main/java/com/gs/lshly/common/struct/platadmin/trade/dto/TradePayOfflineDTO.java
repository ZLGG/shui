package com.gs.lshly.common.struct.platadmin.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
* @author zst
* @since 2020-11-30
*/
public abstract class TradePayOfflineDTO implements Serializable {

    @Data
    @ApiModel("TradePayOfflineDTO.DTO")
    @Accessors(chain = true)
    public static class DTO extends BaseDTO {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("审核备注")
        private String verifyRemark;

        @ApiModelProperty("审核附件")
        private String verifyAttach;

        @ApiModelProperty("审核状态(10通过20驳回)")
        private Integer verifyState;

    }


    @Data
    @ApiModel("TradePayOfflineDTO.RefuseDTO")
    @Accessors(chain = true)
    public static class RefuseDTO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("审核通过/拒绝[通过10 拒绝20 待审核30]")
        private Integer verifyType;

        @ApiModelProperty("审核意见")
        private String verifyRemark;

    }
}
