package com.gs.lshly.rpc.api.bbb.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;

/**
*
* @author zdf
* @since 2021-01-08
*/
public interface IPCBbbMarketMerchantCardUsersRpc {

    PageData<PCBbbMarketMerchantCardUsersVO.PageData> pageData(PCBbbMarketMerchantCardUsersQTO.QTO qto);

    void addMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto);

    void deleteMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto);


    void editMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto);

    PCBbbMarketMerchantCardUsersVO.DetailVO detailMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto);

}