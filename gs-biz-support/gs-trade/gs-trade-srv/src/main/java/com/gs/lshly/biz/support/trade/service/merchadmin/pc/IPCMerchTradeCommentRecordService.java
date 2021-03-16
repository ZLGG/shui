package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentRecordVO;

public interface IPCMerchTradeCommentRecordService {

    /**
     * 添加交易评论记录数据
     * @param eto
     */
    void addTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto);

    PageData<PCMerchTradeCommentRecordVO.ListVO> pageData(PCMerchTradeCommentRecordQTO.QTO qto);

    void deleteTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto);


    void editTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto);

    PCMerchTradeCommentRecordVO.DetailVO detailTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto);

}