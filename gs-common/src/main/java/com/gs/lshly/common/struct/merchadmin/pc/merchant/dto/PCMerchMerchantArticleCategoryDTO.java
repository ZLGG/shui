package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
* @author hyy
* @since 2020-11-07
*/
public abstract class PCMerchMerchantArticleCategoryDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantArticleCategoryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "层级[1-2]1级不关联文章",hidden = true)
        private Integer leve;

        @ApiModelProperty(value = "父ID",hidden = true)
        private String parentId;

        @ApiModelProperty(value = "是否底部显示[10=是 20=否]",hidden = true)
        private Integer bottom;

        @ApiModelProperty(value = "终端[10=2c 20=2b ]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("PCMerchMerchantArticleCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        @NotBlank
        private String id;
    }


}
