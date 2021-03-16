package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-09
*/
public abstract class CorpTypeDictQTO implements Serializable {

    @Data
    @ApiModel("CorpTypeDictQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询类型[10=企业类型名称]")
        private Integer queryType;

        @ApiModelProperty("查询内容")
        private String queryValue;

        @ApiModelProperty("商类型类[10=买家 20=卖家 30=全部]")
        private Integer businessType;
    }

    @Data
    @ApiModel("CorpTypeDictQTO.SimpleQTO")
    @Accessors(chain = true)
    public static class SimpleQTO extends BaseDTO {

        @ApiModelProperty("商类型类[10=买家 20=卖家 30=全部]")
        private Integer businessType;

    }

}
