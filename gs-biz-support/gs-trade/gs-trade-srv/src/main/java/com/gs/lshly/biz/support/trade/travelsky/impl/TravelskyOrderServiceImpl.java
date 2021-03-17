package com.gs.lshly.biz.support.trade.travelsky.impl;

import java.security.MessageDigest;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradePosLog;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.entity.TradeRightsPosLog;
import com.gs.lshly.biz.support.trade.repository.ITradePosLogRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsPosLogRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeDeliveryService;
import com.gs.lshly.biz.support.trade.service.pos.IPosTradeService;
import com.gs.lshly.biz.support.trade.travelsky.ITravelskyOrderService;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.TradeRightsStateEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.pos.dto.PosFinishAndCancelRequestDTO;
import com.gs.lshly.common.struct.pos.dto.PosFinishAndCancelTradeRequestDTO;
import com.gs.lshly.common.struct.pos.dto.PosRSPurchaseSyncRequestDTO;
import com.gs.lshly.common.struct.pos.dto.PosTradeODeliverOrderRequestDTO;
import com.gs.lshly.common.struct.pos.dto.PosTradeOOnlineOrderRequestDTO;
import com.gs.lshly.common.struct.pos.dto.PosTradeRightRequestDTO;
import com.gs.lshly.common.struct.pos.dto.PosTradeStateRequestDTO;
import com.gs.lshly.common.utils.HttpsUtil;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月16日 下午2:42:29
 */
@Component
@Slf4j
public class TravelskyOrderServiceImpl implements ITravelskyOrderService {

	@Override
	public String createOrder() {
		String result = "";
		try {
		// TODO Auto-generated method stub
		//sign=md5(sid=1&productid=2&timestamp=20091113101533&orderid=2009121212345678&count=1
		//&mobile=13500000000&price=9000&key=012345678901)
		String sign=MD5("sid=20001&productid=6752&timestamp=20091113101533"
				+ "&orderid=2009121212345676&count=1"
				+ "&mobile=13500000000&price=9000&key=123qwe12","UTF-8").toLowerCase();
		String url = "http://ifapi.alluu.com/YDInterface/OrderSync.aspx?sid=20001"
				+ "&productid=6752&timestamp=20091113101533&"
				+ "orderid=2009121212345676&count=1"
				+ "&username=test&mobile=13500000000&price=9000&sign="+sign;
		
			 result = HttpsUtil.get(url);
			 /**
			  * 14:26:43.903 
			  *  - [createOrder][response][result=><?xml version="1.0" encoding="UTF-8"?><Result><Status>0</Status><ResultMsg>success</ResultMsg><OrderDetail><OrderID>2021031614263765836770167</OrderID><ProductCode>453890258,</ProductCode><validDate>2021-05-15</validDate><SMSMsg>【手机电子消费券】密码:453890258,你成功订购电子消费券测试产品,数量1张,有效期到2021年5月15日，客服:4001870118</SMSMsg></OrderDetail></Result>]

			  */
			log.info("[createOrder][response][result=>{}]",result.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getOrder() {
		String result = "";
		try {
		// TODO Auto-generated method stub
		//sign=md5(sid=1&productid=2&timestamp=20091113101533&orderid=2009121212345678&count=1
		//&mobile=13500000000&price=9000&key=012345678901)
		String sign=MD5("sid=20001&timestamp="
				+ "20091113101533&orderid=2009121212345676&key=123qwe12","UTF-8").toLowerCase();
		String url = "http://ifapi.alluu.com/YDInterface/searchOrders.aspx?sid=20001&timestamp="
				+ "20091113101533&orderid=2009121212345676&sign="+sign;
		
			 result = HttpsUtil.get(url);
			 /**
			  * 14:26:43.903 
			  *  - [createOrder][response][result=><?xml version="1.0" encoding="UTF-8"?><Result><Status>0</Status><ResultMsg>success</ResultMsg><OrderDetail><OrderID>2021031614263765836770167</OrderID><ProductCode>453890258,</ProductCode><validDate>2021-05-15</validDate><SMSMsg>【手机电子消费券】密码:453890258,你成功订购电子消费券测试产品,数量1张,有效期到2021年5月15日，客服:4001870118</SMSMsg></OrderDetail></Result>]

			  */
			log.info("[createOrder][response][result=>{}]",result.toString());
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
     * @param str 内容
     * @param charset 编码方式
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
