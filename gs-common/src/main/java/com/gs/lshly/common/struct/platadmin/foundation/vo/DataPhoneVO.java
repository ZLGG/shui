package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2021-01-28
*/
public abstract class DataPhoneVO implements Serializable {

    @Data
    @ApiModel("DataPhoneVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("手机号")
        private String phone;




    }

    @Data
    @ApiModel("DataPhoneVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
