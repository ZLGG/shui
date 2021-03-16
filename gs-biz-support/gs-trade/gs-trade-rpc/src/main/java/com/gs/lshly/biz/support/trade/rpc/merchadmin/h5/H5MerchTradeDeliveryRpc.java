package com.gs.lshly.biz.support.trade.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeDeliveryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeDeliveryVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeDeliveryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class H5MerchTradeDeliveryRpc implements IH5MerchTradeDeliveryRpc{
    @Autowired
    private IH5MerchTradeDeliveryService  h5MerchTradeDeliveryService;


    @Override
    public PageData<H5MerchTradeDeliveryVO.ListVO> pageData(H5MerchTradeDeliveryQTO.QTO qto) {
        return h5MerchTradeDeliveryService.pageData(qto);
    }

    @Override
    public H5MerchTradeDeliveryVO.DetailVO detailTradeDelivery(H5MerchTradeDeliveryDTO.IdDTO idDTO) {
        return h5MerchTradeDeliveryService.detailTradeDelivery(idDTO);
    }

    @Override
    public void addTradeDelivery(H5MerchTradeDeliveryDTO.deliveryDTO dto) {
        h5MerchTradeDeliveryService.addTradeDelivery(dto);
    }

    @Override
    public void addTakeGoodsCodeCheck(H5MerchTradeDeliveryDTO.takeGoodsCodeCheckDTO dto) {
        h5MerchTradeDeliveryService.addTakeGoodsCodeCheck(dto);
    }

    @Override
    public List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(H5MerchTradeDeliveryDTO.IdDTO dto) {
        return h5MerchTradeDeliveryService.listShopLogisticsCompany(dto);
    }
}