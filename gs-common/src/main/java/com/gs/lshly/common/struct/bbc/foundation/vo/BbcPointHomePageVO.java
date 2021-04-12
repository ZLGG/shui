package com.gs.lshly.common.struct.bbc.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 积分商城首页
 *
 * 
 * @author yingjun
 * @date 2021年4月12日 上午12:00:31
 */
public abstract class BbcPointHomePageVO implements Serializable {

    @Data
    @ApiModel("BbcPointHomePageVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{
    	
    	@ApiModelProperty("id")
        private String id;
		
        @ApiModelProperty("标题")
        private String name;
        
        @ApiModelProperty("楼层排序数")
        private Integer idx;
        
        @ApiModelProperty("编码标识")
        private String code;
        
        @ApiModelProperty("内容")
        private List list;
        
        @ApiModelProperty("备注：秒杀开始时间")
        private String remark;


    }

}
