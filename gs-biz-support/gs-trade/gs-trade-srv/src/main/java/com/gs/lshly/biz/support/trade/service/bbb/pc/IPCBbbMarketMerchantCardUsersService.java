package com.gs.lshly.biz.support.trade.service.bbb.pc;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;

import java.util.List;

public interface IPCBbbMarketMerchantCardUsersService {

    PageData<PCBbbMarketMerchantCardUsersVO.PageData> pageData(PCBbbMarketMerchantCardUsersQTO.QTO qto);

    void addMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto);

    void deleteMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto);


    void editMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto);

    PCBbbMarketMerchantCardUsersVO.DetailVO detailMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto);

    CommonMarketDTO.MarketSku activeCardSku(String jwtUserId, List<CommonMarketDTO.SkuId> skuIds, ActivityTerminalEnum terminal);
}