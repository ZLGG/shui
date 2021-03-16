package com.gs.lshly.common.struct.bbb.pc.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class BbbSiteNavigationDTO implements Serializable {

    @Data
    @ApiModel("BBB.SiteNavigationDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("热点图片(顶部导航专属)")
        private String hotImageUrl;

        @ApiModelProperty("导航类型[10=顶部链接 20=菜单导航]")
        private Integer type;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;
    }

    @Data
    @ApiModel("BBB.SiteNavigationDTO.ADTO")
    @Accessors(chain = true)
    public static class ADTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty(value ="导航类型[10=顶部链接 20=菜单导航]",hidden = true)
        private Integer type;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
    }

    @Data
    @ApiModel("BBB.SiteNavigationDTO.UDTO")
    @Accessors(chain = true)
    public static class UDTO extends BaseDTO {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty(value ="导航类型[10=顶部链接 20=菜单导航]",hidden = true)
        private Integer type;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
    }

    @Data
    @ApiModel("BBB.SiteNavigationDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
