package com.gs.lshly.biz.support.trade.rpc.bb;

import java.math.BigDecimal;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.util.StringUtils;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bb.trade.dto.RechargeOrderDTO.ETO;
import com.gs.lshly.common.struct.bb.trade.qto.RechargeOrderQTO;
import com.gs.lshly.common.struct.bb.trade.vo.BbRechargeVO.PhoneBillVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.rpc.api.bb.trade.IBbRechargeOrderRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;

/**
*
* @author oy
* @since 2020-10-28
*/
@DubboService
public class BbRechargeOrderRpc implements IBbRechargeOrderRpc{
    
	@Autowired
    private IBbcTradeService  bbcTradeService;
	
	@DubboReference
	private IBbcUserRpc bbcUserRpc;

	/**
	 * TODO yingjun
	 */
	@Override
	public PhoneBillVO getPhoneBill(RechargeOrderQTO.QTO qto) {
		String userId = qto.getJwtUserId();
		if(StringUtils.isEmpty(userId))
			throw new BusinessException("未登录");
		BbcUserVO.InnerUserInfoVO detailVO = bbcUserRpc.innerGetUserInfo(userId);
		if(detailVO==null)
			throw new BusinessException("未登录");
		
		PhoneBillVO phoneBillVO = new PhoneBillVO();
		phoneBillVO.setPhone(detailVO.getPhone());
		phoneBillVO.setRealName(detailVO.getRealName());
		phoneBillVO.setRegion(detailVO.getRegion());
		phoneBillVO.setPhoneBill(getRandomRedPacketBetweenMinAndMax());
		
		return phoneBillVO;
	}

	public static BigDecimal getRandomRedPacketBetweenMinAndMax() {
		BigDecimal min = new BigDecimal("-100");
		BigDecimal max = new BigDecimal("100");
		float minF = min.floatValue();
		float maxF = max.floatValue();

		// 生成随机数
		BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);

		// 返回保留两位小数的随机数。不进行四舍五入
		return db.setScale(2, BigDecimal.ROUND_DOWN);
	}

	
	public static void main(String args[]){
		System.out.println(getRandomRedPacketBetweenMinAndMax());
	}

	@Override
	public void create(ETO eto) {
		// TODO Auto-generated method stub
		
	}
}