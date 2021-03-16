package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-09-29
*/
public abstract class GoodsParamsVO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("参数组id")
        private String id;


        @ApiModelProperty("类目id")
        private String categoryId;


        @ApiModelProperty("参数组名")
        private String name;


        @ApiModelProperty("操作人")
        private String operator;
    }

    @Data
    public static class ParamsListVO extends  ListVO{
        @ApiModelProperty(value = "参数列表")
        private List<GoodsParamInfoVO.ListVO> paramInfos;
    }
    @Data
    public static class DetailVO extends ListVO {

    }
}
