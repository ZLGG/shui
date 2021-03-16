package com.gs.lshly.common.struct.platadmin.stock.vo;
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
public abstract class StockMapSecretVO implements Serializable {

    @Data
    @ApiModel("StockMapSecretVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("地图名称")
        private String mapName;


        @ApiModelProperty("密钥")
        private String mapSecret;

    }

    @Data
    @ApiModel("StockMapSecretVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
