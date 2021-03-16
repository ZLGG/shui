package com.gs.lshly.common.struct.bbb.h5.user.qto;
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
* @since 2021-01-05
*/
public abstract class H5BbbUserCardQTO implements Serializable {

    @Data
    @ApiModel("H5BbbUserCardQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
