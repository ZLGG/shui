package com.gs.lshly.common.struct.bbc.user.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbcUserFavoritesGoodsQTO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
