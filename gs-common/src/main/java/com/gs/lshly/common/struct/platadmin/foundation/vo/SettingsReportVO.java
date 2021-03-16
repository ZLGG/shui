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
public abstract class SettingsReportVO implements Serializable {

    @Data
    @ApiModel("SettingsReportVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("是否开启签到[10=是 20=否]")
        private Integer isOpenState;


        @ApiModelProperty("是否开启签到送积分[10=是 20=否]")
        private Integer isReportInteg;


        @ApiModelProperty("签到送积分")
        private Integer reportInteg;




    }

    @Data
    @ApiModel("SettingsReportVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
