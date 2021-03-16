package com.gs.lshly.common.struct.common;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class LegalDictQTO implements Serializable {

    @Data
    @ApiModel("LegalDictQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商类型类[10=买家 20=卖家 30=全部]")
        private Integer businessType;

        @ApiModelProperty("查询类型[10=公司名称 20=法人姓名 30=公司联系人手机号]")
        private Integer queryType;

        @ApiModelProperty("查询内容")
        private String queryValue;

    }

    @Data
    @ApiModel("LegalDictQTO.NalQTO")
    @Accessors(chain = true)
    public static class NalQTO extends BaseQTO {

        @ApiModelProperty("商类型类[10=买家 20=卖家 30=全部]")
        private Integer businessType;

        @ApiModelProperty("查询类型[10=姓名 20=手机号]")
        private Integer queryType;

        @ApiModelProperty("查询内容")
        private String queryValue;

    }


}
