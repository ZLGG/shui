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
* @since 2020-10-27
*/
public abstract class GoodsCategorySpecQTO implements Serializable {

    @Data
    @ApiModel("GoodsCategorySpecQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("规格id")
        private String specId;

        @ApiModelProperty("类别id")
        private String categoryId;

    }
}
