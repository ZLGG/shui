package com.gs.lshly.biz.support.trade.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;

public interface IBbbH5MarketMerchantCardUsersService {

    PageData<BbbH5MarketMerchantCardUsersVO.PageData> pageData(BbbH5MarketMerchantCardUsersQTO.QTO qto);



}