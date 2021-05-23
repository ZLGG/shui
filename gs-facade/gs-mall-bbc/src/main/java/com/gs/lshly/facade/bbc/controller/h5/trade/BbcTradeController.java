package com.gs.lshly.facade.bbc.controller.h5.trade;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserAuthRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author oy
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/bbc")
@Api(tags = "交易订单管理-v1.1.0")
@Slf4j
@SuppressWarnings("unchecked")
public class BbcTradeController {

	@DubboReference
	private IBbcUserAuthRpc bbcUserAuthRpc;

	@DubboReference
	private IBbcTradeRpc bbcTradeRpc;

	@ApiOperation("1、去结算-v1.1.0")
	@PostMapping("/userCenter/settlement")
	public ResponseData<BbcTradeSettlementVO.DetailVO> settlement(@Valid @RequestBody BbcTradeBuildDTO.cartIdsDTO dto) {
		dto.setTerminal(ActivityTerminalEnum.wap端);
		return bbcTradeRpc.settlementVO(dto);
	}

	@ApiOperation("计算运费")
	@PostMapping("/userCenter/deliveryAmount")
	public ResponseData<Void> deliveryAmount(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {
		dto.setTerminal(ActivityTerminalEnum.wap端);
		return bbcTradeRpc.deliveryAmount(dto);
	}

	/**
	 * 提交订单 场景：用户选购多个店铺商品，点击提交订单 前端处理：每个店铺为一个订单请求，分开请求 后端逻辑：提交订单时，生成待付款订单，返回订单id
	 *
	 * @param dto
	 * @return
	 */
	@ApiOperation("2、提交订单-v1.1.0")
	@PostMapping("/userCenter/orderSubmit")
	public ResponseData<BbcTradeDTO.ListIdDTO> orderSubmit(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {

		log.info("----------------------提交订单-----------------");
		dto.setTerminal(ActivityTerminalEnum.wap端);
		return bbcTradeRpc.orderSubmit(dto);
	}

	/**
	 * 提交订单 场景：用户选购多个店铺商品，点击提交订单 前端处理：每个店铺为一个订单请求，分开请求 后端逻辑：提交订单时，生成待付款订单，返回订单id
	 *
	 * @param dto
	 * @return
	 */
	@ApiOperation("3、获取支付验证码-v1.1.0")
	@PostMapping("/userCenter/getPhoneCheck")
	public ResponseData<Void> getPhoneCheck(@Valid @RequestBody BbcUserDTO.GetPhoneValidCodeDTO dto) {
		bbcUserAuthRpc.getPhoneValidCode(dto);
		return ResponseData.success("短信发送成功");
	}

	@ApiOperation("4、验证支付验证码+积分支付-v1.1.0")
	@PostMapping("/userCenter/checkAndPointDoPay")
	public ResponseData<Void> checkAndPointDoPay(@Valid @RequestBody BbcTradePayBuildDTO.CheckAndPointDoPayETO dto) {

		/**
		 * 验证支付密码
		 * 
		 * 
		 * Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
		 * String validCode = code != null ? code + "" : "";
		 * log.info("获取-手机号码："+dto.getPhone()+"-验证码："+validCode); if
		 * (!StringUtils.equals(validCode, dto.getValidCode())) { throw new
		 * BusinessException("验证码不匹配"); }
		 */
		return bbcTradeRpc.checkAndPointDoPay(dto);
	}

	/**
	 * 支付订单 前端：根据订单号、用户选择的支付类型、混合支付时自行分配的金额，生成支付单，返回第三方支付相关信息
	 * 后端逻辑：如果多个订单合并支付，生成一个主订单号。单独支付时，主订单号为子订单号。
	 *
	 * @param dto
	 * @return
	 */
	@ApiOperation("5、第三方支付-v1.1.0")
	@PostMapping("/userCenter/doPay")
	public ResponseData<Void> doPay(@Valid @RequestBody BbcTradePayBuildDTO.ETO dto) {
		return bbcTradeRpc.orderPay(dto);
	}

	@ApiOperation("支付回调")
	@PostMapping("/doPayNotify")
	// public String doPayNotify(BbcTradeResultNotifyVO.notifyVO notifyVO) {
	public String doPayNotify(@Valid @RequestBody BbcTradeResultNotifyVO.notifyVO notifyVO) {
		return bbcTradeRpc.payNotify(notifyVO);
	}

	@ApiOperation("支付成功")
	@PostMapping("/doPaySuccess")
	public String doPaySuccess(@Valid @RequestBody String tradeCode) {
		return bbcTradeRpc.paySuccess(tradeCode);
	}

	@ApiOperation("订单列表")
	@PostMapping("/userCenter/orderList")
	public ResponseData<PageData<BbcTradeListVO.tradeVO>> orderList(@RequestBody BbcTradeQTO.TradeList qto) {
		PageData<BbcTradeListVO.tradeVO> page = bbcTradeRpc.tradeListPageData(qto);
		return ResponseData.data(page);
	}

	@ApiOperation("订单详情")
	@PostMapping("/userCenter/orderDetail")
	public ResponseData<BbcTradeListVO.tradeVO> orderDetail(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {
		return bbcTradeRpc.orderDetail(dto);
	}

	@ApiOperation("订单确认收货")
	@PostMapping("/userCenter/orderConfirmReceipt")
	public ResponseData<Void> orderConfirmReceipt(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {
		return bbcTradeRpc.orderConfirmReceipt(dto);
	}

	@ApiOperation("隐藏订单")
	@PostMapping("/userCenter/orderHide")
	public ResponseData<Void> orderHide(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {
		return bbcTradeRpc.orderHide(dto);
	}

	@ApiOperation("取消订单")
	@PostMapping("/userCenter/orderCancel")
	public ResponseData<Void> orderCancel(@Valid @RequestBody BbcTradeCancelDTO.CancelDTO dto) {
		return bbcTradeRpc.orderCancel(dto);
	}

	@ApiOperation("订单状态数量")
	@PostMapping("/userCenter/tradeStateCount")
	public ResponseData<List<BbcTradeListVO.stateCountVO>> tradeStateCount(@RequestBody BbcTradeDTO.IdDTO dto) {
		return ResponseData.data(bbcTradeRpc.tradeStateCount(dto));
	}

	@ApiOperation("我的优惠卷列表（资产管理）")
	@GetMapping("/userCenter/myUserCard")
	public ResponseData<PageData<BbcTradeListVO.PageData>> myUserCard(BbcTradeQTO.UserCardQTO qto) {
		return ResponseData.data(bbcTradeRpc.myUserCard(qto));
	}

	@ApiOperation("使用优惠卷列表")
	@PostMapping("/userCenter/useCard")
	public ResponseData<List<BbcTradeListVO.UseCard>> useCard(@Valid @RequestBody BbcTradeDTO.UseCard dto) {
		return ResponseData.data(bbcTradeRpc.useCard(dto));
	}

	@ApiOperation("线下支付")
	@PostMapping("/userCenter/offlinePay")
	public ResponseData<Void> offlinePay(@Valid @RequestBody BbcTradeDTO.OfflinePayDTO dto) {
		bbcTradeRpc.offlinePay(dto);
		return ResponseData.success(MsgConst.OFFLINEPAY_SUCCESS);
	}

	@ApiOperation("修改已下单订单地址-v1.1.0")
	@PostMapping("/modifyOrderAddress")
	public ResponseData<String> modifyOrderAddress(@Valid @RequestBody BbcTradeDTO.ModifyOrderAddressDTO dto) {
		bbcTradeRpc.modifyOrderAddress(dto);
		return ResponseData.success(MsgConst.UPDATE_SUCCESS);
	}
}
