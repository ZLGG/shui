package com.gs.lshly.common.struct.ctcc.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

public class B2IDTO implements Serializable{

	@Data
    @ApiModel(value = "SimpleBusinessAcceptCreateDTO")
	@Accessors(chain = true)
    public static class SimpleBusinessAcceptCreateDTO {
		private String outOrderSeq;//渠道方流水号
    	private String codeNumber;
    	private String linkMan;
    	private String linkPhone;
    	private List<DisCountList> disCountList;
    	
    	private List<Remarks3> remarks3;
    	
    	@Data
        @ApiModel(value = "DisCountListDTO")
    	@Accessors(chain = true)
        public static class DisCountList {
    		
    		private String discountCode;
    		private String discountName;
    		private String discountType;
    	}

    	@Data
        @ApiModel(value = "Remarks3DTO")
    	@Accessors(chain = true)
        public static class Remarks3 {
    		
    		private String discountCode;
    		private String attrId;
    		private String attrValue;
    	}
    }
}
