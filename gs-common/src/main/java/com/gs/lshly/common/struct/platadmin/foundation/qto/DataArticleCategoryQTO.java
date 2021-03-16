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
* @author 陈奇
* @since 2020-10-17
*/
public abstract class DataArticleCategoryQTO implements Serializable {

    @Data
    @ApiModel("DataArticleCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("父节点")
        private String perentId;

        @ApiModelProperty("是否第一层级[10=是  20=否]")
        private Integer leveone;
    }

    @Data
    @ApiModel("DataArticleCategoryQTO.FirstQTO")
    @Accessors(chain = true)
    public static class FirstQTO extends BaseQTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "是否第一层级[10=是  20=否]",hidden = true)
        private Integer leveone;
    }

    @Data
    @ApiModel("DataArticleCategoryQTO.SecondQTO")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class SecondQTO extends BaseQTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("父节点")
        private String perentId;
    }
}
