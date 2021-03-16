package com.gs.lshly.rpc.api.merchadmin.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeDeliveryVO;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IH5MerchTradeDeliveryRpc {


    PageData<H5MerchTradeDeliveryVO.ListVO> pageData(H5MerchTradeDeliveryQTO.QTO qto);

    H5MerchTradeDeliveryVO.DetailVO detailTradeDelivery(H5MerchTradeDeliveryDTO.IdDTO idDTO);

    void addTradeDelivery(H5MerchTradeDeliveryDTO.deliveryDTO dto);

    void addTakeGoodsCodeCheck(H5MerchTradeDeliveryDTO.takeGoodsCodeCheckDTO dto);

    List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(H5MerchTradeDeliveryDTO.IdDTO dto);
}