package com.gs.lshly.common.struct.bbb.pc.user.vo;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class PCBbbUserFrequentGoodsVO implements Serializable {

    @Data
    @ApiModel("PCBbbUserFrequentGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("常购ID")
        private String id;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;


        @ApiModelProperty("商品SKU信息")
        private List<PCBbbGoodsInfoVO.InnerServiceVO> goodsSkuList = new ArrayList<>();

    }

    @Data
    @ApiModel("PCBbbUserFrequentGoodsVO.SkuListVO")
    public static class SkuListVO extends PCBbbGoodsInfoVO.InnerServiceVO {

        private String id;

    }
}
