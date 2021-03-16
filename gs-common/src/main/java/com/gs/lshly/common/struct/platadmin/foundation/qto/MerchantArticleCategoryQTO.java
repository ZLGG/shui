package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-10-29
*/
public abstract class MerchantArticleCategoryQTO implements Serializable {

    @Data
    @ApiModel("MerchantArticleCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("是否底部显示[10=是 20=否]")
        private Integer bottom;

        @ApiModelProperty("终端[10=2c 20=2b ]")
        private Integer terminal;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("层级[1-2]1级不关联文章")
        private Integer leve;

        @ApiModelProperty("父ID")
        private String parentId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }
}
