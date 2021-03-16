package com.gs.lshly.common.struct.bbb.pc.user.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-01-30
*/
public abstract class PCBbbUserPrivateUserLogVO implements Serializable {

    @Data
    @ApiModel("PCBbbUserPrivateUserLogVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("私域会员id(主表id)")
        private String privateUserId;


        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("用户id")
        private String userId;

        @ApiModelProperty("用户名称")
        private String userName;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("审核说明")
        private String remark;

        @ApiModelProperty("生成时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;


    }

    @Data
    @ApiModel("PCBbbUserPrivateUserLogVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
