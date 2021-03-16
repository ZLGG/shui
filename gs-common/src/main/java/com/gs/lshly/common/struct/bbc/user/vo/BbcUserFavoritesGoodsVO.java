package com.gs.lshly.common.struct.bbc.user.vo;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbcUserFavoritesGoodsVO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO extends BbcGoodsInfoVO.HomeAndShopInnerServiceVO{

    }

}
