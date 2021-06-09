package com.gs.lshly.facade.bbc.controller.ctcc;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.utils.HttpClientUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * B2I接口
 *
 * 
 * @author yingjun
 * @date 2021年6月9日 上午9:22:52
 */
@RestController
@RequestMapping("/ctcc/b2i")
@Api(tags = "B2I接口")
public class B2IController {

	/**
	 * 简单加包业务受理接口
	 * @param goodsId
	 * @return
	 */
    @ApiOperation("简单加包业务受理接口")
    @GetMapping("/create")
    public ResponseData<Void> create(@PathVariable String goodsId) {
    	Map<String,String> headers = new HashMap<String,String>();
    	
    	
    	
//    	HttpClientUtils.postForm(url, params, headers);
    	return ResponseData.success("导入成功");
    }

}
