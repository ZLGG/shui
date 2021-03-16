package com.gs.lshly.common.struct.platadmin.user.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserLeveDictVO implements Serializable {

    @Data
    @ApiModel("UserLeveDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("等级名称")
        private String name;


        @ApiModelProperty("等级所需成长值")
        private Integer power;


        @ApiModelProperty("等级图标")
        private String icon;


        @ApiModelProperty("等级适用店铺类型[10=2c 20=2b]")
        private Integer leveType;




    }

    @Data
    @ApiModel("UserLeveDictVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("UserLeveDictVO.LevelListVO")
    public static class LevelListVO implements Serializable{
        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("等级名称")
        private String name;
    }
}
