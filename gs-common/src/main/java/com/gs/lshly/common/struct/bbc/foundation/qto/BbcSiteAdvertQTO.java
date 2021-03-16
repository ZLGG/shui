package com.gs.lshly.common.struct.bbc.foundation.qto;
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
* @author hyy
* @since 2020-11-03
*/
public abstract class BbcSiteAdvertQTO implements Serializable {

    @Data
    @ApiModel("BbcSiteAdvertQTO.SubjectQTO")
    @Accessors(chain = true)
    public static class SubjectQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;


    }

    @Data
    @ApiModel("BbcSiteAdvertQTO.SubjectPageQTO")
    @Accessors(chain = true)
    public static class SubjectPageQTO extends BaseQTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;


    }

    @Data
    @ApiModel("BbcSiteAdvertQTO.CategoryQTO")
    @Accessors(chain = true)
    public static class CategoryQTO extends BaseDTO {

    }
}
