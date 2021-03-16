package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentRecordVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCommentRecordRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentRecordService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-11-16
*/
@DubboService
public class PCMerchTradeCommentRecordRpc implements IPCMerchTradeCommentRecordRpc{
    @Autowired
    private IPCMerchTradeCommentRecordService  pCMerchTradeCommentRecordService;

    @Override
    public PageData<PCMerchTradeCommentRecordVO.ListVO> pageData(PCMerchTradeCommentRecordQTO.QTO qto){
        return pCMerchTradeCommentRecordService.pageData(qto);
    }

    @Override
    public void addTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto){
        pCMerchTradeCommentRecordService.addTradeCommentRecord(eto);
    }

    @Override
    public void deleteTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto){
        pCMerchTradeCommentRecordService.deleteTradeCommentRecord(dto);
    }


    @Override
    public void editTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto){
        pCMerchTradeCommentRecordService.editTradeCommentRecord(eto);
    }

    @Override
    public PCMerchTradeCommentRecordVO.DetailVO detailTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto){
        return  pCMerchTradeCommentRecordService.detailTradeCommentRecord(dto);
    }

}