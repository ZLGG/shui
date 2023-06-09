package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantRoleDictQTO implements Serializable {

    @Data
    @ApiModel("MerchantRoleDictQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
