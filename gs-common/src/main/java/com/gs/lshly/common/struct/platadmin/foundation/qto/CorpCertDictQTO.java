package com.gs.lshly.common.struct.platadmin.foundation.qto;
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
public abstract class CorpCertDictQTO implements Serializable {

    @Data
    @ApiModel("CorpCertDictQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询类型[10=证照名称]")
        private Integer queryType;

        @ApiModelProperty("查询内容")
        private String queryValue;


    }

    @Data
    @ApiModel("CorpCertDictQTO.QTO")
    @Accessors(chain = true)
    public static class CorpTypeIdQTO extends BaseQTO {

        @ApiModelProperty("证照类型ID")
        private String corpTypeId;

    }

}
