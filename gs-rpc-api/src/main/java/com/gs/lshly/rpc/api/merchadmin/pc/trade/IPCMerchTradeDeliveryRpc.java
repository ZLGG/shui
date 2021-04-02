package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IPCMerchTradeDeliveryRpc {

    PageData<PCMerchTradeDeliveryVO.ListVO> pageData(PCMerchTradeDeliveryQTO.QTO qto);

    void addTradeDelivery(PCMerchTradeDeliveryDTO.deliveryDTO eto);

    void editTradeDelivery(PCMerchTradeDeliveryDTO.ETO eto);

    PCMerchTradeDeliveryVO.DetailVO detailTradeDelivery(PCMerchTradeDeliveryDTO.IdDTO dto);

    List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(PCMerchTradeDeliveryDTO.IdDTO dto);

    void addTakeGoodsCodeCheck(PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO eto);

    ExportDataDTO export(PCMerchTradeDeliveryQTO.IdListQTO qo) throws Exception;
}