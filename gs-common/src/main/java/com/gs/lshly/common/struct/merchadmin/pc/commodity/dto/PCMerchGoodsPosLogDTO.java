package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-12-15
*/
public abstract class PCMerchGoodsPosLogDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsPosLogDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "pos素材库id",hidden = true)
        private String id;

        @ApiModelProperty("商品主图地址")
        private String picUrl;

        @ApiModelProperty("商品数量")
        private Integer num;

        @ApiModelProperty("上架时间")
        private LocalDateTime listTime;

        @ApiModelProperty("下架时间")
        private LocalDateTime delistTime;

        @ApiModelProperty("商品价格")
        private BigDecimal price;

        @ApiModelProperty("商品图片列表")
        private String itemImgs;

        @ApiModelProperty("是否是虚拟商品")
        private String isVirtual;

        @ApiModelProperty("商品编号")
        private String numIid;

        @ApiModelProperty("商品标题")
        private String title;

        @ApiModelProperty("商品描述")
        private String desc;

        @ApiModelProperty("规格值，格式为：pid1:vid1:pid_name1:vid_name1; pid2:vid2:pid_name2:vid_name2")
        private String propsName;

        @ApiModelProperty("发布时间")
        private LocalDateTime created;

        @ApiModelProperty("商品计量单位")
        private String unit;

        @ApiModelProperty("是否拍下减库存 0拍下减库存 1付款减库存")
        private Integer subStock;

        @ApiModelProperty("商品的重量 单位 克")
        private BigDecimal itemWeight;

        @ApiModelProperty("成本价,单位元，保留2位小数")
        private BigDecimal costPrice;

        @ApiModelProperty("市场价，单位元，保留2位小数")
        private BigDecimal mktPrice;

        @ApiModelProperty("关键属性，格式为：epid1:evid1:epid_name1:evid_name1;epid2:evid2:epid_name2:evid_name2")
        private String extProps;

        @ApiModelProperty("属性参数 ， 格式有两种：第一种：groupName1:name1:value1;groupName2:name2:value2;groupName2:name3:value4，第二种：文本格式")
        private String paramProps;

        @ApiModelProperty("操作人")
        private String operator;
    }

    @Data
    @ApiModel("PCMerchGoodsPosLogDTO.NumIidDTO")
    @AllArgsConstructor
    public static class NumIidDTO extends BaseDTO {

        @ApiModelProperty(value = "商品编号")
        private String numIid;
    }

    @Data
    @ApiModel("PCMerchGoodsPosLogDTO.NumIidListDTO")
    public static class NumIidListDTO extends BaseDTO {

        @ApiModelProperty(value = "商品编号列表")
        private List<String> listNumIid;
    }

    @Data
    @ApiModel("PCMerchGoodsPosLogDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchGoodsPosLogDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private List<String> idList;
    }


}
