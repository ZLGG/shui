package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author hyy
* @since 2020-10-29
*/
public abstract class MerchantArticleDTO implements Serializable {

    @Data
    @ApiModel("MerchantArticleDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("文章栏目ID           ")
        private String categoryId;

        @ApiModelProperty("阅读量")
        private Integer readCount;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("发布时间")
        private LocalDateTime sendTime;

        @ApiModelProperty("状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("MerchantArticleDTO.IdListDTO")
    @AllArgsConstructor
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("MerchantArticleDTO.ApplyDTO")
    @AllArgsConstructor
    public static class ApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

    }


    @Data
    @ApiModel("MerchantArticleDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
