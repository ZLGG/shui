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
 * @date 2021年3月12日 下午4:21:03
 */
public abstract class BbcHomePageQTO implements Serializable {
	
	
    @Data
    @ApiModel("BbcHomePageQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
    	@ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50]",hidden=true)
        private Integer subject;
    }
    

    @Data
    @ApiModel("BbcSiteAdvertQTO.SubjectQTO")
    @Accessors(chain = true)
    public static class SubjectQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;


    }

    @Data
    @ApiModel("BbcSiteAdvertQTO.SubjectPageQTO")
    @Accessors(chain = true)
    public static class SubjectPageQTO extends BaseQTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;


    }

    @Data
    @ApiModel("BbcSiteAdvertQTO.CategoryQTO")
    @Accessors(chain = true)
    public static class CategoryQTO extends BaseDTO {

    }
    
    /**
     * 获取广告弹窗数据
     *
     * 
     * @author yingjun
     * @date 2021年3月29日 下午3:20:13
     */
    @Data
    @ApiModel("BbcSitePopupQTO.PopupQTO")
    @Accessors(chain = true)
    public static class PopupQTO extends BaseDTO {
    	@ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
    	
        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;
    }
}
