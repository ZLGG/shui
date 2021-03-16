package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
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
public abstract class PCMerchGoodsMaterialLibraryQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsMaterialLibraryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("素材库模板类目id")
        private String categoryId;
    }
}
