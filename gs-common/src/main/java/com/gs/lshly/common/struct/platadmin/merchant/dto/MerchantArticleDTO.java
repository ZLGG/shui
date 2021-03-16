package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-11-09
*/
public abstract class MerchantArticleDTO implements Serializable {

    @Data
    @ApiModel("MerchantArticleDTO.ApplyDTO")
    @Accessors(chain = true)
    public static class ApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "审核状态[20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty(value = "拒审原因")
        private String rejectWhy;

        @ApiModelProperty(value = "显示类型[10=pc 20=h5 30=全部]",hidden = true)
        private Integer pcShow;


    }

    @Data
    @ApiModel("MerchantArticleDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("MerchantArticleDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "商家文章ID列表")
        private List<String> idList;
    }

}
