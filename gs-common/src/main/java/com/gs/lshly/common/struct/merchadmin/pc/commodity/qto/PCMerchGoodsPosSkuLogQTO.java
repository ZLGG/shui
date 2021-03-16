package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-12-15
*/
public abstract class PCMerchGoodsPosSkuLogQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsPosSkuLogQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("sku商品编号")
        private String skuId;

        @ApiModelProperty("sku对应的商品编号")
        private String numIid;

        @ApiModelProperty("sku库存")
        private Integer quantity;

        @ApiModelProperty("sku商品价格")
        private BigDecimal price;

        @ApiModelProperty("sku商品状态（normal=正常，delete=删除）")
        private String status;

        @ApiModelProperty("sku对应的属性名称，（格式为：pid1:vid1:pid_name1:vid_name1; pid2:vid2:pid_name2:vid_name2）")
        private String propertiesName;

        @ApiModelProperty("条形码")
        private String barcode;

        @ApiModelProperty("成本价")
        private BigDecimal costPrice;

        @ApiModelProperty("sku的销售属性组合字符串（颜色，大小，等等，可通过类目API获取某类目下的销售属性）,格式是p1:v1;p2:v2")
        private String properties;

        @ApiModelProperty("卖点（副标题）")
        private String sellPoint;

        @ApiModelProperty("sku图片列表")
        private String skuImgs;

        @ApiModelProperty("sku市场价")
        private BigDecimal marketPrice;

        @ApiModelProperty("sku重量")
        private BigDecimal weight;

        @ApiModelProperty("对sku的操作是否成功")
        private Boolean isSuccess;

        @ApiModelProperty("操作人")
        private String operator;
    }
}
