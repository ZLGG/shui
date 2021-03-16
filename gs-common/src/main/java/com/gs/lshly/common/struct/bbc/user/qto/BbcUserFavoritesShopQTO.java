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
* @author 陈奇
* @since 2020-10-24
*/
public abstract class BbcUserFavoritesShopQTO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
