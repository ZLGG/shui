package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-10-22
*/
public abstract class PCMerchPictureGroupDTO implements Serializable {

    @Data
    @ApiModel("PCMerchPictureGroupDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "图片分组id")
        private String id;

        @ApiModelProperty("分组名称")
        private String groupValue;

    }

    @Data
    @ApiModel("PCMerchPictureGroupDTO.SaveETO")
    @Accessors(chain = true)
    public static class SaveETO extends BaseDTO {

        @ApiModelProperty(value = "图片分组父id，全部为-1")
        private String parentId;

        @ApiModelProperty("图片分组父文件级别，全都级别为0")
        private Integer parentLevel;

        @ApiModelProperty("子文件列表")
        private List<ETO> groupValues = new ArrayList<>();
    }


    @Data
    @ApiModel("PCMerchPictureGroupDTO.ParentIdDTO")
    @Accessors(chain = true)
    public static class ParentIdDTO extends BaseDTO {

        @ApiModelProperty(value = "图片分组父id,全部为-1")
        private String parentId;

    }

    @Data
    @ApiModel("PCMerchPictureGroupDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "图片分组id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchPictureGroupDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "图片分组id")
        private List<String> idList = new ArrayList<>();
    }


}
