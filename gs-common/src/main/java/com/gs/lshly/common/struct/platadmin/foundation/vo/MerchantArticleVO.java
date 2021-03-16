package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-10-29
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

        @ApiModelProperty("文章节点")
        private String parentId;

        /*@ApiModelProperty("内容")
        private String content;


        @ApiModelProperty("文章栏目ID           ")
        private String categoryId;


        @ApiModelProperty("阅读量")
        private Integer readCount;
*/

        /*@ApiModelProperty("排序")
        private Integer idx;*/


        @ApiModelProperty("发布时间")
        private LocalDateTime sendTime;

        @ApiModelProperty("更新时间")
        private LocalDateTime updateTime;


        /*@ApiModelProperty("状态[10=待审 20=通过 30=拒审]")
        private Integer state;
*/

        /*@ApiModelProperty("店铺ID")
        private String shopId;*/


        @ApiModelProperty("商家ID")
        private String merchantId;




    }

    @Data
    @ApiModel("MerchantArticleVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
