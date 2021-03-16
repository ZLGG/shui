package com.gs.lshly.common.struct.common.qto;
import com.gs.lshly.common.struct.BaseDTO;
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
* @since 2020-10-14
*/
public abstract class CommonMerchantApplyQTO implements Serializable {

    @Data
    @ApiModel("CommonMerchantApplyQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }



    @Data
    @ApiModel("CommonMerchantApplyQTO.CategoryQTO")
    public static class CategoryQTO extends BaseDTO {

        @ApiModelProperty(value = "终端",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("CommonMerchantApplyQTO.ApplyCategoryQTO")
    @Accessors(chain = true)
    public static class ApplyCategoryQTO extends BaseQTO {

        @ApiModelProperty(value = "终端",hidden = true)
        private Integer terminal;

    }

}
