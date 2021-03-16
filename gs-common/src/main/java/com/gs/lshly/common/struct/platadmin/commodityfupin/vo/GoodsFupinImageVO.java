package com.gs.lshly.common.struct.platadmin.commodityfupin.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-12-09
*/
public abstract class GoodsFupinImageVO implements Serializable {

    @Data
    @ApiModel("GoodsFupinImageVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("图片凭证id")
        private String id;


        @ApiModelProperty("扶贫商品id")
        private String fupinGoodsId;


        @ApiModelProperty("图片凭证地址")
        private String imgUrl;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("GoodsFupinImageVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
