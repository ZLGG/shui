package com.gs.lshly.common.struct.platadmin.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantPermissionDictVO implements Serializable {

    @Data
    @ApiModel("MerchantPermissionDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("组枚举编号")
        private Integer groupCode;


        @ApiModelProperty("组名称")
        private String groupName;


        @ApiModelProperty("权限名")
        private String permissionName;


        @ApiModelProperty("路由地址")
        private String route;


        @ApiModelProperty("排序")
        private Integer idx;




    }

    @Data
    @ApiModel("MerchantPermissionDictVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
