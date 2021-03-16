package com.gs.lshly.common.struct.platadmin.stock.vo;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author tx
* @since 2020-12-04
*/
public abstract class LogisticsInformationVO implements Serializable {

    @Data
    @ApiModel("LogisticsInformationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("追踪信息")
        private JSONArray Traces;
    }


    @Data
    @ApiModel("LogisticsInformationVO.TracesVO")
    @Accessors(chain = true)
    public static class TracesVO implements Serializable{

        @ApiModelProperty("时间")
        private String AcceptTime;


        @ApiModelProperty("描述")
        private String AcceptStation;


        @ApiModelProperty("所在城市")
        private String Location;


        @ApiModelProperty("备注")
        private String Remark;
    }

}
