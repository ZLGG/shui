package com.gs.lshly.facade.bbc.controller.ctcc;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcInUserCouponDTO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcCouponRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcInUserCouponRpc;

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
@Api(tags = "IN会员回调接口")
@Slf4j
public class InUserController {
	
	@DubboReference
    private IBbcCouponRpc bbcCouponRpc;
	
	@DubboReference
	private IBbcInUserCouponRpc bbcInUserCouponRpc;


	/**
	 * IN会员回调接口
	 * 
	 * 给某个用户加优惠券+成为IN会员
	 * 
	 * 手机号码
	 * 类型
	 * @param goodsId
	 * @return
	 */
	@ApiOperation("IN会员回调接口")
	@GetMapping("/notify")
	public ResponseData<List<BbcCouponVO.InCouponVO>> notify(HttpServletRequest request) {
        this.printLog(request);
        
        String phone = "13675899916";
        Integer inCouponType = 1;
        List<BbcCouponVO.InCouponVO> retList = bbcCouponRpc.listInCouponByType(inCouponType);
        BbcInUserCouponDTO.CreateDTO dto = new BbcInUserCouponDTO.CreateDTO();
        dto.setPhone(phone);
        dto.setInCouponType(inCouponType);
        dto.setList(retList);
        bbcInUserCouponRpc.createInUserCoupon(dto);;
		return ResponseData.data(retList);
	}

	private void printLog(HttpServletRequest request){
		//获取请求行的相关信息
        log.info("getMethod:" + request.getMethod());
        log.info("getQueryString:" + request.getQueryString());
        log.info("getProtocol:" + request.getProtocol());
        log.info("getContextPath" + request.getContextPath());
        log.info("getPathInfo:" + request.getPathInfo());
        log.info("getPathTranslated:" + request.getPathTranslated());
        log.info("getServletPath:" + request.getServletPath());
        log.info("getRemoteAddr:" + request.getRemoteAddr());
        log.info("getRemoteHost:" + request.getRemoteHost());
        log.info("getRemotePort:" + request.getRemotePort());
        log.info("getLocalAddr:" + request.getLocalAddr());
        log.info("getLocalName:" + request.getLocalName());
        log.info("getLocalPort:" + request.getLocalPort());
        log.info("getServerName:" + request.getServerName());
        log.info("getServerPort:" + request.getServerPort());
        log.info("getScheme:" + request.getScheme());
        log.info("getRequestURL:" + request.getRequestURL());
        
		Map res = new HashMap();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 如果字段的值为空，判断若值为空，则删除这个字段>
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		
		log.info("请求数据："+res.toString());
	}
}
