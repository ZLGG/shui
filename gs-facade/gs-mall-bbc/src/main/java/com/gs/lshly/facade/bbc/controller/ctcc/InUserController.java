package com.gs.lshly.facade.bbc.controller.ctcc;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.utils.HttpClientUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * In会员回调
 *
 * 
 * @author yingjun
 * @date 2021年7月3日 下午10:49:56
 */
@RestController
@RequestMapping("/ctcc/in")
@Api(tags = "B2I接口")
@Slf4j
public class InUserController {

	/**
	 * 简单加包业务受理接口
	 * @param goodsId
	 * @return
	 */
    @ApiOperation("简单加包业务受理接口")
    @GetMapping("/create")
    public ResponseData<Void> create(String codeNumber) {
    	Map<String,String> headers = new HashMap<String,String>();
    	
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("outOrderSeq", System.currentTimeMillis()+"");//渠道方订单流水
    	params.put("channelName", "电信商城");//渠道名
    	params.put("channelCode", "DXSC");//渠道编码
    	params.put("yxqdName", "电信商城");//营销渠道名
    	params.put("yxqdCode", "dxsc");//营销渠道编码
    	params.put("c3Name", "杭州市");//地市
    	params.put("c4Name", "杭州市");//县市
    	params.put("codeNumber", codeNumber);//办理号码
    	params.put("assetsType", "手机");//资产类型
    	params.put("linkMan", "应君");//联系人
    	params.put("linkPhone", "13675899916");//联系电话
    	params.put("gdType", "standard");//业务订单类型
    	params.put("rgFlag", "N");//人工介入
    	
    	JSONArray disCountList = new JSONArray();
    	JSONObject json = new JSONObject();
    	json.put("discountCode", "8617013100010006");//优惠编码
    	json.put("discountName", "智慧交通半年服务");//优惠名称
    	json.put("discountType", "促销");//优惠类型
    	disCountList.add(json);
    	params.put("disCountList", disCountList.toJSONString());
    	
    	JSONArray remarks3 = new JSONArray();
    	json = new JSONObject();
    	json.put("discountCode", "8617013100010006");//优惠编码
    	json.put("attrId", "20000101022");//优惠名称
    	json.put("attrValue", "12000");//优惠类型
    	remarks3.add(json);
    	params.put("remarks3", remarks3.toJSONString());
    	try {
    		String str = HttpClientUtils.postForm("http://134.96.185.193:30009/b2i/openapi/simpleBusinessAccept/create", params, headers);
    		log.info("请求B2I返回信息==>"+str);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	return ResponseData.success("导入成功");
    }

}
