package com.gs.lshly.common.struct.bbb.pc.user.dto;
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
* @author xxfc
* @since 2020-12-09
*/
public abstract class PCBbbUserCardDTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserCardDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("优惠卷ID")
        private String cardId;


    }

    @Data
    @ApiModel("PCBbbUserCardDTO.IdDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private String id;
    }


}
