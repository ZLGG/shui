package com.gs.lshly.common.struct.platadmin.user.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class UserUser2bApplyQTO implements Serializable {

    @Data
    @ApiModel("UserUser2bApplyQTO.QTO")
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询类型[10=公司名称 20=法人代表 30=联系人手机号]")
        private Integer queryType;

        @ApiModelProperty("查询内容")
        private String queryValue;
    }
}
