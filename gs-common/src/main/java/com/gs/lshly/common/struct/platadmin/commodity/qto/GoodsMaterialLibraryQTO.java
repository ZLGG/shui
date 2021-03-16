package com.gs.lshly.common.struct.platadmin.commodity.qto;
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
* @since 2020-12-10
*/
public abstract class GoodsMaterialLibraryQTO implements Serializable {

    @Data
    @ApiModel("GoodsMaterialLibraryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品品牌名称")
        private String brandName;

        @ApiModelProperty("商品标题（商品名称）")
        private String goodsName;
    }
}
