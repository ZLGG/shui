package com.gs.lshly.common.struct.bbc.foundation.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-11-02
*/
public abstract class BbcSiteFloorQTO implements Serializable {

    @Data
    @ApiModel("BbcSiteFloorQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;

    }

    @Data
    @ApiModel("BbcSiteFloorQTO.GoodsMoreQTO")
    @Accessors(chain = true)
    public static class GoodsMoreQTO extends BaseQTO {

        @ApiModelProperty(value = "楼层ID",hidden = true)
        private String floorId;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;

    }

    @Data
    @ApiModel("BbcSiteFloorQTO.GoodsMaxQTO")
    @Accessors(chain = true)
    public static class GoodsMaxQTO extends BaseDTO {

        @ApiModelProperty(value = "楼层ID",hidden = true)
        private String floorId;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;

    }
}
