package com.gs.lshly.common.ctcc.b2i;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gs.lshly.common.struct.ctcc.dto.B2IDTO;
import com.gs.lshly.common.struct.ctcc.vo.B2IVO;
import com.gs.lshly.common.utils.HttpClientUtils;
import com.gs.lshly.common.utils.MD5Util;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2020年6月22日 下午2:59:05
 */
@Slf4j
public class SimpleBusinessAccept {
    
	public static B2IVO.SimpleBusinessAcceptCreateVO create(B2IDTO.SimpleBusinessAcceptCreateDTO dto) {
		B2IVO.SimpleBusinessAcceptCreateVO simpleBusinessAcceptCreateVO=new B2IVO.SimpleBusinessAcceptCreateVO();
		try {
			Map<String,String> headers = new HashMap<String,String>();
	    	
	    	String sign = MD5Util.MD5Lower((dto.getOutOrderSeq() + "DXSCMD5DXSC&!&^%@"),"UTF-8");
	    	headers.put("appCode", "DXSC");
	        headers.put("signType", "MD5");
	        headers.put("tradeOrderSeq", dto.getOutOrderSeq());
	        headers.put("Sign", sign);
	    	
	    	Map<String,String> params = new HashMap<String,String>();
	    	params.put("outOrderSeq", dto.getOutOrderSeq());//渠道方订单流水
	    	params.put("channelName", "电信商城");//渠道名
	    	params.put("channelCode", "DXSC");//渠道编码
	    	params.put("yxqdName", "电信商城");//营销渠道名
	    	params.put("yxqdCode", "dxsc");//营销渠道编码
	    	params.put("c3Name", "杭州市");//地市
	    	params.put("c4Name", "杭州市");//县市
	//    	params.put("codeNumber", dto.);//办理号码
	    	params.put("assetsType", "手机");//资产类型
	    	params.put("linkMan", dto.getLinkMan());//联系人
	    	params.put("linkPhone",dto.getLinkPhone());//联系电话
	    	params.put("gdType", "standard");//业务订单类型
	    	params.put("rgFlag", "N");//人工介入
	    	
	    	JSONArray disCountList = new JSONArray();
	    	JSONObject json = new JSONObject();
	    	json.put("discountCode", dto.getDisCountList().get(0).getDiscountCode());//优惠编码
	    	json.put("discountName", dto.getDisCountList().get(0).getDiscountName());//优惠名称
	    	json.put("discountType", dto.getDisCountList().get(0).getDiscountType());//优惠类型
	    	params.put("disCountList", disCountList.toJSONString());
	    	
	    	JSONArray remarks3 = new JSONArray();
	    	json = new JSONObject();
	    	json.put("discountCode", dto.getRemarks3().get(0).getDiscountCode());//优惠编码
	    	json.put("attrId", dto.getRemarks3().get(0).getAttrId());//优惠名称
	    	json.put("attrValue", dto.getRemarks3().get(0).getAttrValue());//优惠类型
	    	params.put("remarks3", remarks3.toJSONString());
	    	
	    	String str = "";
    	
    		str = HttpClientUtils.postForm("http://134.96.185.193:30009/b2i/openapi/simpleBusinessAccept/create", params, headers);
    		log.info("请求B2I返回信息==>"+str);
    		JSONObject retJson = JSONObject.parseObject(str); 
    		simpleBusinessAcceptCreateVO=(B2IVO.SimpleBusinessAcceptCreateVO)JSONObject.toJavaObject(retJson, B2IVO.SimpleBusinessAcceptCreateVO.class);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return simpleBusinessAcceptCreateVO;
    }
}
