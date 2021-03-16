package com.gs.lshly.common.struct.platadmin.commodityfupin.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class GoodsFupinImageQTO implements Serializable {

    @Data
    @ApiModel("GoodsFupinImageQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("扶贫商品id")
        private String fupinGoodsId;

        @ApiModelProperty("图片凭证地址")
        private String imgUrl;

        @ApiModelProperty("操作人")
        private String operator;
    }
}
