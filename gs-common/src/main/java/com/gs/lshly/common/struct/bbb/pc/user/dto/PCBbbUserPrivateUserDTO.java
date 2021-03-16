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
import java.util.List;

/**
* @author xxfc
* @since 2020-12-24
*/
public abstract class PCBbbUserPrivateUserDTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserPrivateUserDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

    }

    @Data
    @ApiModel("PCBbbUserPrivateUserDTO.IdDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCBbbUserPrivateUserDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺id列表")
        private List<String> idList;
    }


}
