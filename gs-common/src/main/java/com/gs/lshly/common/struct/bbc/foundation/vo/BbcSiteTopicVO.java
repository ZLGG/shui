package com.gs.lshly.common.struct.bbc.foundation.vo;
import java.io.Serializable;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
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
        private List<BbcGoodsInfoVO.DetailVO> goodsList;
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
    
    /**
     * IN会员专区
     *
     * 
     * @author yingjun
     * @date 2021年3月17日 上午1:23:43
     */
	@Data
    @ApiModel("BbcSiteTopicVO.GoodsVO")
    @Accessors(chain = true)
    public static class GoodsVO implements Serializable{
		
		@ApiModelProperty("id")
        private String id;
		
        @ApiModelProperty("标题")
        private String name;
        
        @ApiModelProperty("内容")
        private PageData<BbcGoodsInfoVO.DetailVO> list;
        
        @ApiModelProperty("封面图")
        private String imageUrl;
        
        @ApiModelProperty("积分值")
        private Integer telecomsIntegral;
    }
	
	/**
	 * 专区产品
	 *
	 * 
	 * @author yingjun
	 * @date 2021年3月30日 下午7:11:33
	 */
	@Data
    @ApiModel("BbcSiteTopicVO.GoodsInfoVO")
    public static class GoodsInfoVO implements Serializable {
    	
    	@ApiModelProperty("id")
        private String id;

    	@ApiModelProperty("分区名称")
        private String name;

        @ApiModelProperty("备注")
        private String remark;
        
        @ApiModelProperty("排序")
        private Integer idx;
        
        @ApiModelProperty("商品列表")
        private List<BbcGoodsInfoVO.DetailVO> goodsList;
    }
	
	
    /**
     * IN会员专区
     *
     * 
     * @author yingjun
     * @date 2021年3月17日 上午1:23:43
     */
	@Data
    @ApiModel("BbcSiteTopicVO.ListByTopicNameVO")
    @Accessors(chain = true)
    public static class ListByTopicNameVO implements Serializable{
		
		@ApiModelProperty("id")
        private String id;
		
        @ApiModelProperty("标题")
        private String name;
        
        @ApiModelProperty("内容")
        private List list;
        
        @ApiModelProperty("封面图")
        private String imageUrl;
        
    }
}
