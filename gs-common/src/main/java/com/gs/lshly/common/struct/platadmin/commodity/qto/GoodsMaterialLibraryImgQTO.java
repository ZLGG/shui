package com.gs.lshly.common.struct.platadmin.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-12-10
*/
public abstract class GoodsMaterialLibraryImgQTO implements Serializable {

    @Data
    @ApiModel("GoodsMaterialLibraryImgQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("素材库id")
        private String materialLibraryId;

        @ApiModelProperty("图片路径")
        private String imageUrl;

        @ApiModelProperty("操作人")
        private String operator;
    }
}
