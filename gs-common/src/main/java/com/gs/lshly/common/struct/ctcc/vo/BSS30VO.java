package com.gs.lshly.common.struct.ctcc.vo;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * BSS30服务返回信息
 *
 * 
 * @author yingjun
 * @date 2021年7月4日 上午9:32:37
 */
@SuppressWarnings("serial")
public abstract class BSS30VO implements Serializable {

	
	/**
	 * 查询用户信息
	 *
	 * 
	 * @author yingjun
	 * @date 2021年7月4日 上午9:33:46
	 */
	@Data
    @ApiModel("BSS30VO.QueryCustomerVO")
    @Accessors(chain = true)
    public static class QueryCustomerVO implements Serializable{
    	
		
    	private Msghead msghead;//返回头信息
    	
    	@Data
        @ApiModel(value = "msghead")
    	@Accessors(chain = true)
        public static class Msghead {
    		
    		private String resultMsg;//SUCCESS
    		private Long resultCode;//1 成功
    		private String reqId;
    	}

    	private Msgbody msgbody;//返回体信息(以JSON格式存储业务接口实际返回数据，以下为返回数据结构具体说明)
    	
    	@Data
        @ApiModel(value = "msgbody")
    	@Accessors(chain = true)
        public static class Msgbody {
    		
    		private String resultCode; //0:成功
    		private String resultMsg;//"处理成功"
    		
    		private ResultObject resultObject;
    		@Data
            @ApiModel(value = "resultObject")
        	@Accessors(chain = true)
            public static class ResultObject {
    			private String certType;//客户类型   3
    			private Long prodInstNum;//
    			private String contactName;
    			private String custAddr;//客户地址    阿斯顿法定
    			private String custBrandReps; //null
    			private String remark;//备注 null
    			private String statusCd;//状态   在网
    			private String custName;//客户名称   测试
    			private String enterDate;//入网时间
    			private String extCustId;//集团客户编号
    			private Long regionId;//地市id    8330100
    			private String contactAddr;//联系人地址
    			private Long taxPayerId;//纳税人ID
    			private Long custId;//客户ID   20258784
    			private String custType;//客户类型   公众客户
    			private String custNumber;//客户编号    171200002809017
    			private String partyNbr;//集团参与人编码
    			private String certNum;//证件号码    2019092412345
    			private String contactPhone;//联系电话   13444444444
    			private Long partyId;//参于人ID
    			
    			private List<CustAttrReps> custAttrReps;//客户属性
    			@Data
                @ApiModel(value = "custAttrReps")
            	@Accessors(chain = true)
                public static class CustAttrReps {
    				private Long attrId;//标识    300100000032
    				private Long attrValuedId;//属性值 标识
    				private String relType;
    				private String remark;//备注
    				private String attrValue;//属性值   1111
    				private String relSubType;
    				private String subAttrValue;
    				private String attrName;//属性名称
    				
    			}
    			
    			private List<AccountDtos> accountDtos;//帐户列表
    			@Data
                @ApiModel(value = "accountDtos")
            	@Accessors(chain = true)
                public static class AccountDtos {
    				private String ext3AcctId;
    				private String extAcctId;//集团帐户编码
    				private String acctLoginName;
    				private String ext2AcctId;
    				private Long acctId;//帐户id
    				private String ext1AcctId;
    				private String remark;//备注
    				private String statusCd;//状态
    				private String acctName;
    				private String expDate;//失效时间
    				private String effDate;//生效时间
    				private String acctCd;//合同号
    				private String regionId;//区县id
    				private Long custId;//客户ID
    				private String loginPassword;
    				private String groupAcctId;//集团帐户库标识
    				private Long prodInstId;//代表号码id
    				private Long acctBillingType;//帐户计费类型
    				
    			}
    			
    		}
    		private String attrValue;
    		
    	}
		
    }
	
	/**
	 * 积分扣减服务
	 *
	 * 
	 * @author yingjun
	 * @date 2021年7月4日 上午9:33:46
	 */
	@Data
    @ApiModel("BSS30VO.PointRightsDealForKJVO")
    @Accessors(chain = true)
    public static class PointRightsDealForKJVO implements Serializable{
		
		private Msghead msghead;//返回头信息
    	
    	@Data
        @ApiModel(value = "msghead")
    	@Accessors(chain = true)
        public static class Msghead {
    		
    		private String resultMsg;//SUCCESS
    		private Long resultCode;//1 成功
    		private String reqId;
    	}

    	private Msgbody msgbody;//返回体信息(以JSON格式存储业务接口实际返回数据，以下为返回数据结构具体说明)
    	
    	@Data
        @ApiModel(value = "msgbody")
    	@Accessors(chain = true)
        public static class Msgbody {
    		
    		private String resultCode; //0:成功
    		private String resultMsg;//"处理成功"
    		private String resultObject;
    	}
	}
	
}
