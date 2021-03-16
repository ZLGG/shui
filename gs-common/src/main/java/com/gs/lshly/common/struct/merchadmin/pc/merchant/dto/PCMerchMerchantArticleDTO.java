package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
* @since 2020-11-07
*/
public abstract class PCMerchMerchantArticleDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantArticleDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("显示类型[10=pc 20=h5 30=全部]")
        private Integer pcShow;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("文章栏目ID")
        private String categoryId;

        @ApiModelProperty("发布时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime sendTime;


    }

    @Data
    @ApiModel("PCMerchMerchantArticleDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

    }

    @Data
    @ApiModel("PCMerchMerchantArticleDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "id列表")
        List<String> idDTOS;
    }
}
