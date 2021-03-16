package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class SiteAdvertDTO implements Serializable {


    @Data
    @ApiModel("SiteAdvertDTO.H5CategoryETO")
    @Accessors(chain = true)
    public static class H5CategoryETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("商品分类列表")
        private List<H5CategoryItem> categoryList;

    }


    @Data
    @ApiModel("SiteAdvertDTO.H5CategoryItem")
    @Accessors(chain = true)
    public static class H5CategoryItem implements Serializable{

        @ApiModelProperty(value = "商品类目ID")
        private String categoryId;

        @ApiModelProperty(value = "商品类目广告图数组")
        private List<H5CategoryAdvertItem> advertList;

    }

    @Data
    @ApiModel("SiteAdvertDTO.H5AdvertItem")
    @Accessors(chain = true)
    public static class H5CategoryAdvertItem implements Serializable{

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("SiteAdvertDTO.H5SubjectETO")
    @Accessors(chain = true)
    public static class H5SubjectETO extends BaseDTO {

        @ApiModelProperty("广告图列表")
        private List<H5SubjectItemETO> advertList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;
    }



    @Data
    @ApiModel("SiteAdvertDTO.H5SubjectItemETO")
    @Accessors(chain = true)
    public static class H5SubjectItemETO implements Serializable{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("是否新增[0=否 1=是]")
        private Integer isNew;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }


    @Data
    @ApiModel("SiteAdvertDTO.PCGroupETO")
    @Accessors(chain = true)
    public static class PCGroupETO extends BaseDTO {

        @ApiModelProperty("广告图列表")
        private List<PCGroupItem> advertList;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteAdvertDTO.PCGroupItem")
    @Accessors(chain = true)
    public static class PCGroupItem implements Serializable{

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("排序号")
        private Integer idx;
    }


    @Data
    @ApiModel("SiteAdvertDTO.PCBillBoardETO")
    @Accessors(chain = true)
    public static class PCBillBoardETO extends BaseDTO {

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteAdvertDTO.PCBillBoardMoreETO")
    @Accessors(chain = true)
    public static class PCBillBoardMoreETO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "广告图数组")
        private  List<PCBillBoardItem> billBoardList;

    }

    @Data
    @ApiModel("SiteAdvertDTO.PCBillBoardItem")
    @Accessors(chain = true)
    public static class PCBillBoardItem implements Serializable {

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

    }

}
