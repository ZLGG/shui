package com.gs.lshly.common.struct.platadmin.foundation.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专题配置
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:25:05
 */
public abstract class SiteTopicVO implements Serializable {

    @Data
    @ApiModel("SiteTopicVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("发布时间")
        private LocalDateTime cdate;
        
        @ApiModelProperty("上线/下线  1/0 ")
        private Integer onoff;
        
        @ApiModelProperty("关联商口个数")
        private Integer goodsCount;
        
        @ApiModelProperty(value="封面图")
        private String imageUrl;
        
        @ApiModelProperty(value="所属大类[所属分类，可多选 10：电信甄选 20：名品集市  ]")
        private String category;
        
        @ApiModelProperty(value="排序字段")
        private Integer idx;
        
    }
    
    
    @Data
    @ApiModel("SiteTopicVO.PCDetailVO")
    @Accessors(chain = true)
    public static class PCDetailVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("备注")
        private String remark;
        
        @ApiModelProperty("上线/下线  1/0 ")
        private Integer onoff;
        
        @ApiModelProperty("商口列表")
        private List<PCGoodsDetailVO> goods;
        
        @ApiModelProperty(value="封面图")
        private String imageUrl;
        
        @ApiModelProperty(value="所属大类[所属分类，可多选 10：电信甄选 20：名品集市  ]")
        private String category;
        
        @ApiModelProperty(value="排序字段")
        private Integer idx;

    }
    
    @Data
    @ApiModel("SiteTopicVO.PCGoodsDetailVO")
    @Accessors(chain = true)
    public static class PCGoodsDetailVO implements Serializable{
    	
    	@ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品编号")
        private String goodsNo;
    }
}
