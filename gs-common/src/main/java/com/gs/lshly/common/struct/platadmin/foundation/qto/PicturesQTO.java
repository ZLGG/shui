package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author Starry
* @since 2020-10-06
*/
public abstract class PicturesQTO implements Serializable {

    @Data
    @ApiModel("PicturesQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty(value = "头部菜单指示 10=平台，20=店铺图片，30 = 商家入驻图片，40用户图片，50=回收站")
        private Integer menuCondition;

        @ApiModelProperty("更新时间介词(10=大于，20=等于，30=小于）")
        private Integer compareType;

        @ApiModelProperty("更新时间")
        private LocalDateTime udate;

        @ApiModelProperty("图片名字")
        private String imageName;

        @ApiModelProperty("图片路径")
        private String imageUrl;


    }


}
