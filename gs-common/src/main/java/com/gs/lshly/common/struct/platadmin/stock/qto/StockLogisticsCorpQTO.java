package com.gs.lshly.common.struct.platadmin.stock.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zzg
* @since 2020-10-23
*/
public abstract class StockLogisticsCorpQTO implements Serializable {

    @Data
    @ApiModel("StockLogisticsCorpQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

       /* @ApiModelProperty("物流公司代码")
        private String code;

        @ApiModelProperty("物流公司名称")
        private String name;

        @ApiModelProperty("物流公司网站")
        private String www;*/
    }
}
