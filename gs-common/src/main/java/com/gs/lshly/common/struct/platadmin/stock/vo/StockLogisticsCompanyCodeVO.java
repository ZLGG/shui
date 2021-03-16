package com.gs.lshly.common.struct.platadmin.stock.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zzg
* @since 2020-10-30
*/
public abstract class StockLogisticsCompanyCodeVO implements Serializable {



    @Data
    @ApiModel("StockLogisticsCompanyCodeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("物流公司编码")
        private String code;


        @ApiModelProperty("物流公司名称")
        private String logisticsCompanyAme;

    }


    @Data
    @ApiModel("StockLogisticsCompanyCodeVO.ListCodeCodeVO")
    @Accessors(chain = true)
    public static class ListCodeCodeVO implements Serializable{

        @ApiModelProperty("显示数组")
        private List<ListVO>   allCode;


    }



    @Data
    @ApiModel("StockLogisticsCompanyCodeVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
