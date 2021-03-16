package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeDeliveryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeDeliveryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class PCMerchTradeDeliveryRpc implements IPCMerchTradeDeliveryRpc{
    @Autowired
    private IPCMerchTradeDeliveryService  pCMerchTradeDeliveryService;

    @Override
    public PageData<PCMerchTradeDeliveryVO.ListVO> pageData(PCMerchTradeDeliveryQTO.QTO qto){
        return pCMerchTradeDeliveryService.pageData(qto);
    }

    @Override
    public void addTradeDelivery(PCMerchTradeDeliveryDTO.deliveryDTO eto){
        pCMerchTradeDeliveryService.addTradeDelivery(eto);
    }

    @Override
    public void editTradeDelivery(PCMerchTradeDeliveryDTO.ETO eto){
        pCMerchTradeDeliveryService.editTradeDelivery(eto);
    }

    @Override
    public PCMerchTradeDeliveryVO.DetailVO detailTradeDelivery(PCMerchTradeDeliveryDTO.IdDTO dto){
        return  pCMerchTradeDeliveryService.detailTradeDelivery(dto);
    }

    @Override
    public List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(PCMerchTradeDeliveryDTO.IdDTO dto) {
        return pCMerchTradeDeliveryService.listShopLogisticsCompany(dto);
    }

    @Override
    public void addTakeGoodsCodeCheck(PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO eto) {
        pCMerchTradeDeliveryService.addTakeGoodsCodeCheck(eto);
    }

}