package com.gs.lshly.common.struct.bbb.pc.user.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2021-01-13
*/
public abstract class PCBbbUserSignInVO implements Serializable {

    @Data
    @ApiModel("PCBbbUserSignInVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("用户ID")
        private String userId;




    }

    @Data
    @ApiModel("PCBbbUserSignInVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
