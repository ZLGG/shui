package com.gs.lshly.rpc.api.bbb.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;

/**
*
* @author zdf
* @since 2021-01-08
*/
public interface IBbbH5MarketMerchantCardUsersRpc {

    PageData<BbbH5MarketMerchantCardUsersVO.PageData> pageData(BbbH5MarketMerchantCardUsersQTO.QTO qto);


}