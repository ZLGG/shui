package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-08
*/
public abstract class SiteBottomArticleDTO implements Serializable {

    @Data
    @ApiModel("SiteBottomArticleDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("底部文章名")
        private String name;

        @ApiModelProperty("文章链接")
        private String articleUrl;

        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
    }

    @Data
    @ApiModel("SiteBottomArticleDTO.ADTO")
    @Accessors(chain = true)
    public static class ADTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("底部文章名")
        private String name;

        @ApiModelProperty("文章链接")
        private String articleUrl;

        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
    }

    @Data
    @ApiModel("SiteBottomArticleDTO.PCUDTO")
    @Accessors(chain = true)
    public static class PCUDTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;

        @ApiModelProperty("底部文章配置")
        private List<PCBottomItem> bottomList;

    }

    @Data
    @ApiModel("SiteBottomArticleDTO.PCBottomItem")
    @Accessors(chain = true)
    public static class PCBottomItem extends BaseDTO {

        @ApiModelProperty("底部文章名")
        private String name;

        @ApiModelProperty("文章链接")
        private String articleUrl;

    }



    @Data
    @ApiModel("SiteBottomArticleDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
