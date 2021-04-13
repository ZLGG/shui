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
public abstract class BbcUserShoppingCarQTO implements Serializable {

    @Data
    @ApiModel("BbcUserShoppingCarQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
    	
    	@ApiModelProperty("商品分类0/null/other所有 10 普通商品 20 积分商品 30 IN会员商品 ")
    	private Integer goodsType;
    }
}
