package com.gs.lshly.common.struct.platadmin.stock.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author zst
* @since 2021-01-29
*/
public abstract class StockKdniaoVO implements Serializable {

    @Data
    @ApiModel("StockKdniaoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("电商id")
        private String eBusinessID;


        @ApiModelProperty("电商加密私钥")
        private String appKey;


        @ApiModelProperty("是否启用订单跟踪查询[10开启 20关闭]")
        private Integer isStart;

    }

    @Data
    @ApiModel("StockKdniaoVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
