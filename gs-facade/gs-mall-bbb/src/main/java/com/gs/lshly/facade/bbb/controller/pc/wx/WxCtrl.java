package com.gs.lshly.facade.bbb.controller.pc.wx;

import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.wx.*;
import com.gs.lshly.common.utils.*;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.middleware.log.aop.LogAspect;
import com.gs.lshly.rpc.api.bbb.pc.wx.IBbbWxUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/wx")
@Api(tags = "微信扫码登录注册",description = " ")
@Slf4j
public class WxCtrl {

	@DubboReference
	IBbbWxUserRpc wxUserRpc;


	private boolean isWxRequest(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		if (StrUtil.isBlank(signature) || StrUtil.isBlank(timestamp) || StrUtil.isBlank(nonce)) {
			return false;
		}
		List<String> array = new ArrayList<>();
		array.add("lshlyWxToken");
		array.add(timestamp);
		array.add(nonce);
		Collections.sort(array);
		String tmp = ListUtil.listCoverString(array,"");
		String verifySignature = SHAUtil.Sha1(tmp);
		return  signature.equals(verifySignature);
	}
	private void addBasicReqParams(Map<String, Object> reqMap, BaseReq req) {
		req.setMsgType((String) reqMap.get("MsgType"));
		req.setFromUserName((String) reqMap.get("FromUserName"));
		req.setToUserName((String) reqMap.get("ToUserName"));
		req.setCreateTime(Long.parseLong((String) reqMap.get("CreateTime")));
	}
	private BaseEvent buildBasicEvent(Map<String, Object> reqMap, BaseEvent event) {
		addBasicReqParams(reqMap, event);
		event.setEvent((String) reqMap.get("Event"));
		return event;
	}

	@ResponseBody
	@GetMapping("/checkSignature")
	public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = "auth fail";
		if(isWxRequest(request)) {
			result = request.getParameter("echostr");
		}
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(result);
		writer.close();
	}

	@ResponseBody
	@PostMapping("/checkSignature")
	public void receiveMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if(isWxRequest(request)) {
			Map<String, Object> reqMap = XmlUtils.parseXml(request);
			String fromUserName = (String) reqMap.get("FromUserName");
			String toUserName = (String) reqMap.get("ToUserName");
			String msgType = (String) reqMap.get("MsgType");
			log.debug("收到消息,消息类型:{}", msgType);

			BaseMsg msg = null;

			if (msgType.equals(ReqType.EVENT)) {
				String eventType = (String) reqMap.get("Event");
				String ticket = (String) reqMap.get("Ticket");
				QrCodeEvent qrCodeEvent = null;
				//带参
				if (StrUtil.isNotBlank(ticket)) {
					String eventKey = (String) reqMap.get("EventKey");
					qrCodeEvent = new QrCodeEvent(eventKey, ticket);
					buildBasicEvent(reqMap, qrCodeEvent);
					if (eventType.equals(EventType.SCAN)) {
						//用户已关注时的事件推送-登录
						msg = wxUserRpc.handleQrCodeEvent(qrCodeEvent);
					}
					if (eventType.equals(EventType.SUBSCRIBE)) {
						//用户未关注时的事件推送-登录
						msg = wxUserRpc.handleQrCodeEvent(qrCodeEvent);
					}
					// 用户事件推送
				}
			}
			if (msg!=null) {
				msg.setFromUserName(toUserName);
				msg.setToUserName(fromUserName);
				String result = msg.toXml();

				response.setContentType("text/xml;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write(result);
				writer.close();
			}
		}
	}

	@ResponseBody
	@GetMapping("/getQrcode")
	public String getQrcode(@RequestParam(value = "sceneId")long sceneId) {
		String ticket = wxUserRpc.createTempTicket(sceneId);
		log.info("sceneId :" + sceneId + " 商城带参二维码\n" + ticket);
		return ticket;
	}

	@GetMapping("/bindOutPhone")
	@ApiOperation(value = "4,微信默认登陆后外部手机号绑定")
	@Log(module = "登陆", func = "PC-扫码登陆")
	public ResponseData<BbbUserVO.LoginVO> phone(@RequestParam(value = "sceneId")long sceneId, String validCode, String phone){
		BbbUserVO.LoginVO vo = wxUserRpc.bindPhone(sceneId, validCode, phone);
		log.info("扫码后外部手机号绑定："+ JsonUtils.toJson(vo));
		setLogAspect(vo);
		return ResponseData.data(vo);
	}

	@ResponseBody
	@GetMapping("/loginState")
	@ApiOperation("是否已经扫码登录")
	@Log(module = "登陆", func = "PC-扫码登陆")
	public ResponseData<BbbUserVO.LoginVO> qrcodeState(@RequestParam(value = "sceneId")long sceneId) {
		ResponseData<BbbUserVO.LoginVO> loginState = wxUserRpc.wasLogin(sceneId);
		setLogAspect(loginState.getData());
		log.info("检查登录状态。sceneId:{0},\tloginState:{3}", sceneId, loginState);
		return loginState;
	}

	private void setLogAspect(BbbUserVO.LoginVO vo){
		if (vo!=null && StrUtil.isNotBlank(vo.getAuthToken())) {
			LogAspect.set(LogAspect.toDTO(JwtUtil.getJwtUser(vo.getAuthToken())));
		}
	}
}
