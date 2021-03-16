package com.gs.lshly.common.struct.platadmin.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-01-04
*/
public abstract class PCMerchSiteVO implements Serializable {

    @Data
    @ApiModel("PCMerchSiteVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("站点名称")
        private String name;


        @ApiModelProperty("商家登录背景图")
        private String merchantLoginBackimage;


        @ApiModelProperty("是否PC显示[10=是 20=否]")
        private Integer pcShow;


        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;


        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;





        @ApiModelProperty("是否启用手机号验证[10=开启  20=关闭]")
        private Integer state;

    }

    @Data
    @ApiModel("PCMerchSiteVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("PCMerchSiteVO.LoginImageVO")
    public static class LoginImageVO implements Serializable{
        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家登录背景图")
        private String merchantLoginBackimage;

    }
}
