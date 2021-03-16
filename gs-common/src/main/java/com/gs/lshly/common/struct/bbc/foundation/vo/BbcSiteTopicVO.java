package com.gs.lshly.common.struct.bbc.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.InnerServiceVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午2:11:58
 */
public abstract class BbcSiteTopicVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2936344726126995945L;

	@Data
    @ApiModel("BbcSiteTopicVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String name;

        @ApiModelProperty("备注")
        private String remark;

        @ApiModelProperty("顺序")
        private Integer idx;
    }
	
	@Data
    @ApiModel("BbcSiteTopicVO.TopicVO")
    public static class TopicVO implements Serializable {
    	
    	@ApiModelProperty("id")
        private String id;

    	@ApiModelProperty("分区名称")
        private String name;

        @ApiModelProperty("备注")
        private String remark;
        
        @ApiModelProperty("排序")
        private Integer idx;
        
        @ApiModelProperty("商品列表")
        private List<GoodsInfoVO.DetailVO> goodsList;
    }
	
	
	@Data
    @ApiModel("BbcSiteTopicVO.CategoryListVO")
    @Accessors(chain = true)
    public static class CategoryListVO implements Serializable{
		
		@ApiModelProperty("id")
        private String id;
		
        @ApiModelProperty("标题")
        private String name;
        
        @ApiModelProperty("排序")
        private Integer idx;
        
        @ApiModelProperty("内容")
        private List list;
        
        @ApiModelProperty("封面图")
        private String imageUrl;
    }
    
    @Data
    @ApiModel("BbcSiteTopicVO.CategoryDetailVO")
    @Accessors(chain = true)
    public static class CategoryDetailVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("标题")
        private String name;
        
        @ApiModelProperty("备注")
        private String remark;
        
        @ApiModelProperty("图片链接")
        private String imageUrl;
        
        @ApiModelProperty("跳转链接")
        private String jumpUrl;
        
        @ApiModelProperty("排序")
        private Integer idx;
        
    }
}
