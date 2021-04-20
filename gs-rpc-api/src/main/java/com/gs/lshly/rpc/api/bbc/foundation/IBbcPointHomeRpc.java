package com.gs.lshly.rpc.api.bbc.foundation;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;

/**
 * 积分商城首页
 *
 * 
 * @author yingjun
 * @date 2021年4月12日 上午12:09:33
 */
public interface IBbcPointHomeRpc {

	/**
	 * 积分商城首页
	 * @param qto
	 * @return
	 */
    List<BbcPointHomePageVO.ListVO> getHome(BbcPointHomeQTO.QTO qto);
    
//    /**
//     * 电信国际首页
//     * @param qto
//     * @return
//     */
//    List<BbcPointHomePageVO.CtccInternationalListVO> ctccInternationalHome(BbcPointHomeQTO.CtccInternationalQTO qto);

}