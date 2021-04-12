package com.gs.lshly.common.struct.bbb.pc.commodity.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Starry
 * @Date 18:43 2020/11/25
 */
public class PCBbbGoodsCategoryQTO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {
    	@ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50积分商城]",hidden=true)
        private Integer subject;
    	
    	@ApiModelProperty(value="10 20",hidden=true)
        private Integer terminal;
    }

    @Data
    @ApiModel("PCBbbGoodsCategoryQTO.CategoryNavigationQTO")
    @Accessors(chain = true)
    public static class CategoryNavigationQTO extends BaseQTO {
        @ApiModelProperty("二级类目id")
        private String categoryLevel2Id;

        @ApiModelProperty("三级类目id")
        private List<String> categoryLevel3Id;

        @ApiModelProperty("从品牌中心进来,该字段不为空")
        private String fromAddress;
    }

    @Data
    @ApiModel("PCBbbGoodsCategoryQTO.CategoryForBrandQTO")
    @Accessors(chain = true)
    public static class CategoryForBrandQTO extends BaseDTO {

        @ApiModelProperty("品牌ID数组")
        private List<String> brandIdList;

    }
}
