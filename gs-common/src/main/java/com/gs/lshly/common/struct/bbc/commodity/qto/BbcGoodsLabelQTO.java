package com.gs.lshly.common.struct.bbc.commodity.qto;
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
* @since 2020-11-11
*/
public abstract class BbcGoodsLabelQTO implements Serializable {

    @Data
    @ApiModel("BbcGoodsLabelQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {


    }
}
