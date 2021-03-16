package com.gs.lshly.common.struct.platadmin.merchant.qto;
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
* @since 2020-10-16
*/
public abstract class MerchantShopCategoryApplyQTO implements Serializable {

    @Data
    @ApiModel("MerchantShopCategoryApplyQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审 40=全部 ]")
        private Integer state;

    }
}
