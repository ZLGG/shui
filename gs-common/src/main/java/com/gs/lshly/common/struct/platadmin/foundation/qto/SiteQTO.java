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
* @since 2020-11-11
*/
public abstract class SiteQTO implements Serializable {

    @Data
    @ApiModel("SiteQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {


    }
}
