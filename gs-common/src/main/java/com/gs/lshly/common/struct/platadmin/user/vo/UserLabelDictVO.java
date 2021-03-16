package com.gs.lshly.common.struct.platadmin.user.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserLabelDictVO implements Serializable {

    @Data
    @ApiModel("UserLabelDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("标签名")
        private String labelName;


        @ApiModelProperty("颜色")
        private String labelColor;




    }

    @Data
    @ApiModel("UserLabelDictVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("UserLabelDictVO.UserLabelVO")
    @Accessors(chain = true)
    public static class UserLabelVO implements Serializable{

        @ApiModelProperty("会员ID")
        private String user_id;

        @ApiModelProperty("会员标签数组")
        private List<UserLabelItemVO> labelList;
    }
    @Data
    @ApiModel("UserLabelDictVO.UserLabelItemVO")
    @Accessors(chain = true)
    public static class UserLabelItemVO implements Serializable{

        @ApiModelProperty("标签ID")
        private String label_id;

        @ApiModelProperty("标签名称")
        private String lable_name;

        @ApiModelProperty("标签颜色")
        private String lable_color;
    }

}
