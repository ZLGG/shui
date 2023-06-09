package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
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
* @since 2020-11-07
*/
public abstract class PCMerchMerchantArticleQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantArticleQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("显示类型[10=pc 20=h5 30=全部]")
        private Integer pcShow;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("状态[10=待审 20=通过 30=拒审]")
        private String state;
     }


}
