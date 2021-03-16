package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRecordVO;

public interface IPCMerchTradeRightsRecordService {

    PageData<PCMerchTradeRightsRecordVO.ListVO> pageData(PCMerchTradeRightsRecordQTO.QTO qto);

    void addTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto);

    void deleteTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto);


    void editTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto);

    PCMerchTradeRightsRecordVO.DetailVO detailTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto);

}