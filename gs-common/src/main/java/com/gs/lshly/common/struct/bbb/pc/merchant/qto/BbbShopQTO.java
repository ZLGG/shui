package com.gs.lshly.common.struct.bbb.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbbShopQTO implements Serializable {

    @Data
    @ApiModel("BbbShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺名称")
        private String shopName;

    }

    @Data
    @ApiModel("BbbShopQTO.ScopeQTO")
    @Accessors(chain = true)
    public static class ScopeQTO extends BaseQTO {

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("用户当前所在的经度(保留小数点后6位),默认公司位置")
        private String userLongitude;

        @ApiModelProperty("用户当前所在的纬度(保留小数点后6位),默认公司位置")
        private String userLatitude;

        @ApiModelProperty("距离范围(千米)默认三公里")
        private Integer len;

    }


    @Data
    @ApiModel("BbbShopQTO.SearchQTO")
    @Accessors(chain = true)
    public static class SearchQTO extends BaseQTO {

        @ApiModelProperty("三级类目id")
        private List<String> level3CategoryId;

        @ApiModelProperty("品牌id")
        private List<String> brandId;

        @ApiModelProperty(value = "排序条件字段 10=销量 20=店铺评价(星级)")
        private Integer orderByProperties;

    }


}
