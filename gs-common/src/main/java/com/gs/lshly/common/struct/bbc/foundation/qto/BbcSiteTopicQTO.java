package com.gs.lshly.common.struct.bbc.foundation.qto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午10:45:38
 */
public abstract class BbcSiteTopicQTO implements Serializable {

    @Data
    @ApiModel("BbcSiteTopicQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
        
        
        @ApiModelProperty(value="终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;

    }
    
    
    @Data
    @ApiModel("BbcSiteTopicQTO.EnjoyQTO")
    @Accessors(chain = true)
    public static class EnjoyQTO extends BaseQTO {

        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
        
        @ApiModelProperty(value="终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;
        
        /**
        @ApiModelProperty(value="一级分类ID")
        private String category;
        
        @ApiModelProperty(value="所有分类",hidden=true)
        private String categoryIds;
        
        
        @ApiModelProperty(value="10 猜你喜欢",hidden=true)
        private Integer code;
        **/
    }
    
    
    @Data
    @ApiModel("BbcSiteTopicQTO.SearchmoreQTO")
    @Accessors(chain = true)
    public static class SearchmoreQTO extends BaseQTO {

        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
        
        @ApiModelProperty(value="终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;
        
        @ApiModelProperty(value="分类ID")
        private String id;
        
        @ApiModelProperty(value="分类名称")
        private String name;
        
    }
    
    @Data
    @ApiModel("BbcSiteTopicQTO.ListByTopicNameQTO")
    @Accessors(chain = true)
    public static class ListByTopicNameQTO extends BaseQTO {

        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
        
        @ApiModelProperty(value="终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;
        
        @ApiModelProperty(value="分类名称")
        private String name;
        
    }
}
