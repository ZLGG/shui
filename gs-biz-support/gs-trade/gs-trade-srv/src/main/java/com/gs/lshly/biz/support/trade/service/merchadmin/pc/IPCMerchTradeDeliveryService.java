package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;

import java.util.List;

public interface IPCMerchTradeDeliveryService {

    PageData<PCMerchTradeDeliveryVO.ListVO> pageData(PCMerchTradeDeliveryQTO.QTO qto);

    void addTradeDelivery(PCMerchTradeDeliveryDTO.deliveryDTO eto);

    void editTradeDelivery(PCMerchTradeDeliveryDTO.ETO eto);

    PCMerchTradeDeliveryVO.DetailVO detailTradeDelivery(PCMerchTradeDeliveryDTO.IdDTO dto);

    List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(PCMerchTradeDeliveryDTO.IdDTO dto);

    void addTakeGoodsCodeCheck(PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO eto);

    List<PCMerchTradeDeliveryVO.ListVOExport> export(PCMerchTradeDeliveryQTO.IdListQTO qo);
}