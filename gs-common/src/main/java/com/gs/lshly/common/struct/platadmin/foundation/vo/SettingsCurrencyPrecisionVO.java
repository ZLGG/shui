package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsCurrencyPrecisionVO implements Serializable {

    @Data
    @ApiModel("SettingsCurrencyPrecisionVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("保留位数")
        private Integer digit;


        @ApiModelProperty("取整方式")
        private String ways;




    }

    @Data
    @ApiModel("SettingsCurrencyPrecisionVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
