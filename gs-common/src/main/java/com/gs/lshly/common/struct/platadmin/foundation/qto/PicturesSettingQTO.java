package com.gs.lshly.common.struct.platadmin.foundation.qto;
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
* @since 2020-10-21
*/
public abstract class PicturesSettingQTO implements Serializable {

    @Data
    @ApiModel("PicturesSettingQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("默认图片路径")
        private String imageUrl;

        @ApiModelProperty("默认图片名称")
        private String imageName;

        @ApiModelProperty("存储引擎")
        private String storageEnginee;

        @ApiModelProperty("图片尺寸")
        private String imageSize;

        @ApiModelProperty("图片格式")
        private String imageType;

        @ApiModelProperty("图片尺寸类型")
        private Integer imageSizeType;

    }
}
