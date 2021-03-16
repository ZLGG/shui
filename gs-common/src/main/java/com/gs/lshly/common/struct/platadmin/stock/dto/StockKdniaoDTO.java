package com.gs.lshly.common.struct.platadmin.stock.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author zst
* @since 2021-01-29
*/
public abstract class StockKdniaoDTO implements Serializable {

    @Data
    @ApiModel("StockKdniaoDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("电商id")
        private String eBusinessID;

        @ApiModelProperty("电商加密私钥")
        private String appKey;

        @ApiModelProperty("是否启用订单跟踪查询[10开启 20关闭]")
        private Integer isStart;
    }

    @Data
    @ApiModel("StockKdniaoDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


    @Data
    @ApiModel("StockKdniaoDTO.TradeTailDTO")
    @AllArgsConstructor
    public static class TradeTailDTO extends BaseDTO {

        @ApiModelProperty( "快递单号")
        private String expressNumber;

        @ApiModelProperty("顺丰参数")
        private String customerName;

        @ApiModelProperty("物流公司编号")
        private String shipperCode;

    }


}
