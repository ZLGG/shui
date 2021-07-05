package com.gs.lshly.common.struct.ctcc.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
public class BSS30DTO implements Serializable{

	@Data
    @ApiModel(value = "PointRightsDealForKJDTO")
	@Accessors(chain = true)
    public static class PointRightsDealForKJDTO {
		private String codeVal;//请求号码
    	private String codeType;//请求号码类型
    	private String orderNum;//订单号码
    	private String ordCreateDate;//创建时间
    	private String pointSource;
    	
    	
    	private List<PointReqItemDtoForDubboList> pointReqItemDtoForDubboList;
    	
    	@Data
        @ApiModel(value = "PointReqItemDtoForDubboList")
    	@Accessors(chain = true)
        public static class PointReqItemDtoForDubboList {
    		
    		private Long pointAmount;
    		private String pointTypeGroupNbr;
    		private Long orderItemId;
    		private Long offerInstId;
    		private String placeCode;
    		private String goodsNo;
    		private String isComeUser;
    		private Long amount;
    		private String exchangeType;
    		private String exchangeChn;
    	}

    }
}
