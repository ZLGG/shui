package com.gs.lshly.biz.support.trade.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketMerchantCardUsersRpc;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketMerchantCardUsersService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2021-01-08
*/
@DubboService
public class PCBbbMarketMerchantCardUsersRpc implements IPCBbbMarketMerchantCardUsersRpc{
    @Autowired
    private IPCBbbMarketMerchantCardUsersService  pCBbbMarketMerchantCardUsersService;

    @Override
    public PageData<PCBbbMarketMerchantCardUsersVO.PageData> pageData(PCBbbMarketMerchantCardUsersQTO.QTO qto){
        return pCBbbMarketMerchantCardUsersService.pageData(qto);
    }

    @Override
    public void addMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto){
        pCBbbMarketMerchantCardUsersService.addMarketMerchantCardUsers(eto);
    }

    @Override
    public void deleteMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto){
        pCBbbMarketMerchantCardUsersService.deleteMarketMerchantCardUsers(dto);
    }


    @Override
    public void editMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto){
        pCBbbMarketMerchantCardUsersService.editMarketMerchantCardUsers(eto);
    }

    @Override
    public PCBbbMarketMerchantCardUsersVO.DetailVO detailMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto){
        return  pCBbbMarketMerchantCardUsersService.detailMarketMerchantCardUsers(dto);
    }

}