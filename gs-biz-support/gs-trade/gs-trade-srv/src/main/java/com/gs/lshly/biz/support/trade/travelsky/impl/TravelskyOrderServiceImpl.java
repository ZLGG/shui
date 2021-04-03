package com.gs.lshly.biz.support.trade.travelsky.impl;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.entity.TradeGoodsTravelsky;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsTravelskyRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.travelsky.ITravelskyOrderService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTravelskyDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.HttpsUtil;
import com.gs.lshly.common.utils.StringManageUtil;
import com.gs.lshly.common.utils.XmlUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月16日 下午2:42:29
 */
@SuppressWarnings("unchecked")
@Component
@Slf4j
public class TravelskyOrderServiceImpl implements ITravelskyOrderService {

	@Value("${travelsky.sid}")
	private String sid;

	@Value("${travelsky.key}")
	private String key;

	@Value("${travelsky.url}")
	private String url;

	@Autowired
	private ITradeGoodsTravelskyRepository tradeGoodsTravelskyRepository;

	@Autowired
	private ITradeGoodsRepository tradeGoodsRepository;

	@Autowired
	private ITradeRepository tradeRepository;

	@DubboReference
	private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;

	@DubboReference
	private IBbcUserRpc IBbcUserRpc;

	@Override
	public void createOrder(BbcTravelskyDTO.ETO eto) {
		String xml = "";
		try {
			BbcUserVO.InnerUserInfoVO user = IBbcUserRpc.innerGetUserInfo(eto.getJwtUserId());
			if (user == null)
				throw new BusinessException("请求的用户不存在");
			String tradeGoodsId = eto.getTradeGoodsId();// 交易商品Id

			TradeGoods tradeGoods = tradeGoodsRepository.getById(tradeGoodsId);
			if (tradeGoods == null)
				throw new BusinessException("请求的订单不存在");

			String goodsId = tradeGoods.getGoodsId();
			BbcGoodsInfoVO.DetailVO detailVO = iBbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
			if (detailVO == null)
				throw new BusinessException("请求的商品不存在");

			Integer thirdProductId = detailVO.getThirdProductId();
			String tradeId = tradeGoods.getTradeId();

			Trade trade = tradeRepository.getById(tradeId);
			if (trade == null)
				throw new BusinessException("请求的订单不存在");
			BigDecimal payAmount = tradeGoods.getPayAmount();
			Integer price = StringManageUtil.multiply(payAmount, 100);
			String timestamp = DateUtils.fomatDate(new Date(), "yyyyMMddHHmmss");
			String message = "sid=" + sid + "&productid=" + thirdProductId + "&timestamp=" + timestamp + "&orderid="
					+ trade.getId() + "&count=" + tradeGoods.getQuantity() + "&mobile=" + user.getPhone() + "&price="
					+ price + "&key=" + key;

			String sign = MD5(message, "UTF-8").toLowerCase();
			String get = url + "sid=20001" + "&productid=" + thirdProductId + "&timestamp=" + timestamp + "&orderid="
					+ trade.getId() + "&count=" + tradeGoods.getQuantity() + "&username=" + user.getUserName()
					+ "&mobile=" + user.getPhone() + "&price=" + price + "&sign=" + sign;

			xml = HttpsUtil.get(get);

			Map<String, Object> map = XmlUtil.parseXml(xml);

			log.info("[createOrder][response][result=>{}]", xml.toString());
			TradeGoodsTravelsky tradeGoodsTravelsky = new TradeGoodsTravelsky();
			tradeGoodsTravelsky.setTradeGoodsId(tradeGoodsId);
			tradeGoodsTravelsky.setGoodsId(goodsId);
			tradeGoodsTravelsky.setTradeId(tradeId);
			tradeGoodsTravelsky.setThirdProductId(thirdProductId);
			tradeGoodsTravelsky.setUserId(tradeGoods.getUserId());
			tradeGoodsTravelsky.setShopId(tradeGoods.getShopId());
			tradeGoodsTravelsky.setMerchantId(tradeGoods.getMerchantId());
			String status = map.get("Status").toString();
			tradeGoodsTravelsky.setStatus(status);
			tradeGoodsTravelsky.setResultMsg(map.get("ResultMsg").toString());
			if (status.equals("0")) {
				Map<String, Object> detail = (Map<String, Object>) map.get("OrderDetail");
				tradeGoodsTravelsky.setProductCode(detail.get("ProductCode").toString());
				tradeGoodsTravelsky.setValidDate(detail.get("validDate").toString());
				tradeGoodsTravelsky.setOrderId(detail.get("OrderID").toString());
				tradeGoodsTravelsky.setSmsMsg(detail.get("SMSMsg").toString());
			}

			tradeGoodsTravelskyRepository.saveOrUpdate(tradeGoodsTravelsky);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("订购电子票接口出错");
		}
	}

	@Override
	public String getOrder() {
		String result = "";
		try {
			// TODO Auto-generated method stub
			// sign=md5(sid=1&productid=2&timestamp=20091113101533&orderid=2009121212345678&count=1
			// &mobile=13500000000&price=9000&key=012345678901)
			String sign = MD5("sid=20001&timestamp=" + "20091113101533&orderid=2009121212345676&key=123qwe12", "UTF-8")
					.toLowerCase();
			String url = "http://ifapi.alluu.com/YDInterface/searchOrders.aspx?sid=20001&timestamp="
					+ "20091113101533&orderid=2009121212345676&sign=" + sign;

			result = HttpsUtil.get(url);
			/**
			 * 14:26:43.903 - [createOrder][response][result=><?xml
			 * version="1.0" encoding="UTF-8"?><Result><Status>0</Status>
			 * <ResultMsg>success</ResultMsg>
			 * <OrderDetail><OrderID>2021031614263765836770167</OrderID>
			 * <ProductCode>453890258,</ProductCode>
			 * <validDate>2021-05-15</validDate>
			 * <SMSMsg>【手机电子消费券】密码:453890258,你成功订购电子消费券测试产品,数量1张,有效期到2021年5月15日，
			 * 客服:4001870118</SMSMsg></OrderDetail></Result>]
			 * 
			 */
			log.info("[createOrder][response][result=>{}]", result.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		TravelskyOrderServiceImpl service = new TravelskyOrderServiceImpl();
		service.getOrder();
	}

	/**
	 * MD5加密
	 * 
	 * @param str
	 *            内容
	 * @param charset
	 *            编码方式
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String MD5(String str, String charset) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes(charset));
		byte[] result = md.digest();
		StringBuffer sb = new StringBuffer(32);
		for (int i = 0; i < result.length; i++) {
			int val = result[i] & 0xff;
			if (val <= 0xf) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toLowerCase();
	}

}
