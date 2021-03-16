package com.gs.lshly.common.struct.platadmin.stock.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2021-01-15
*/
public abstract class StockMapSecretDTO implements Serializable {

    @Data
    @ApiModel("StockMapSecretDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("地图名称")
        private String mapName;

        @ApiModelProperty("密钥")
        private String mapSecret;
    }

    @Data
    @ApiModel("StockMapSecretDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
