package com.gs.lshly.common.struct.bbc.stock.qto;
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
* @author zzg
* @since 2020-11-02
*/
public abstract class BbcStockAddressQTO implements Serializable {

    @Data
    @ApiModel("BbcStockAddressQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }
}
