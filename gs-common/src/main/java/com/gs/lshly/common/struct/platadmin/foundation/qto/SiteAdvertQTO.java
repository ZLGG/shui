package com.gs.lshly.common.struct.platadmin.foundation.qto;
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
* @author hyy
* @since 2020-11-03
*/
public abstract class SiteAdvertQTO implements Serializable {

    @Data
    @ApiModel("SiteAdvertQTO.H5CategoryQTO")
    @Accessors(chain = true)
    public static class  H5CategoryQTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }


    @Data
    @ApiModel("SiteAdvertQTO.H5SubjectQTO")
    @Accessors(chain = true)
    public static class H5SubjectQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏  50=积分商城]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteAdvertQTO.PCSubjectQTO")
    @Accessors(chain = true)
    public static class PCSubjectQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteAdvertQTO.PCBillBoardQTO")
    @Accessors(chain = true)
    public static class PCBillBoardQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

}
