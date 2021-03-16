package com.gs.lshly.common.struct.bbb.pc.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class BbbSiteNavigationQTO implements Serializable {

    @Data
    @ApiModel("BBB.SiteNavigationQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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
    @ApiModel("BBB.SiteNavigationQTO.BQTO")
    @Accessors(chain = true)
    public static class BQTO extends BaseQTO {

        @ApiModelProperty(value ="导航类型[10=顶部链接 20=菜单导航]", hidden = true)
        private Integer type;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]", hidden = true)
        private Integer subject;
    }
}
