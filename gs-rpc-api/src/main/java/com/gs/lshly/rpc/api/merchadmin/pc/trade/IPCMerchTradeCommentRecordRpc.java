package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentRecordVO;

/**
*
* @author Starry
* @since 2020-11-16
*/
public interface IPCMerchTradeCommentRecordRpc {

    PageData<PCMerchTradeCommentRecordVO.ListVO> pageData(PCMerchTradeCommentRecordQTO.QTO qto);

    void addTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto);

    void deleteTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto);


    void editTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto);

    PCMerchTradeCommentRecordVO.DetailVO detailTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto);

}