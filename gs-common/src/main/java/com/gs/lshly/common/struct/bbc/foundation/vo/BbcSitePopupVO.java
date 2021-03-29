package com.gs.lshly.common.struct.bbc.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午2:11:58
 */
public abstract class BbcSitePopupVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2936344726126995945L;

	@Data
    @ApiModel("BbcSitePopupVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("是否需要弹窗 0:否 1:是")
        private Integer status;

        @ApiModelProperty("标题")
        private String name;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("图片地址")
        private String imageUrl;
    }
}
