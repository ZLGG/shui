package com.gs.lshly.common.struct.platadmin.merchant.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-11-09
*/
public abstract class MerchantArticleVO implements Serializable {

    @Data
    @ApiModel("MerchantArticleVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("文章栏目ID")
        private String categoryId;

        @ApiModelProperty("文章栏目名称")
        private String categoryName;

        @ApiModelProperty("阅读量")
        private Integer readCount;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("发布时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime sendTime;

        @ApiModelProperty("更新时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;

        @ApiModelProperty("状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty(value = "显示类型[10=pc 20=h5 30=全部]")
        private Integer pcShow;
    }


    @Data
    @ApiModel("MerchantArticleVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty("内容")
        private String content;
    }
}
