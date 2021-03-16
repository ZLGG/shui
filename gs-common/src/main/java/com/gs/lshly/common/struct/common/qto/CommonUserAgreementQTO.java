package com.gs.lshly.common.struct.common.qto;
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
* @author xxfc
* @since 2021-01-14
*/
public abstract class CommonUserAgreementQTO implements Serializable {

    @Data
    @ApiModel("CommonUserAgreementVO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "会员类型[10=2b 20=2c]")
        private Integer userType;

    }
}
