package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRecordVO;

/**
*
* @author zdf
* @since 2020-12-17
*/
public interface IPCMerchTradeRightsRecordRpc {

    PageData<PCMerchTradeRightsRecordVO.ListVO> pageData(PCMerchTradeRightsRecordQTO.QTO qto);

    void addTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto);

    void deleteTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto);


    void editTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto);

    PCMerchTradeRightsRecordVO.DetailVO detailTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto);

}