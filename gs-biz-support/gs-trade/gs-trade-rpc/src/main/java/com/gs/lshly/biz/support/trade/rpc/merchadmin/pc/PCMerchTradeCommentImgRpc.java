package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentImgVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCommentImgRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentImgService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-11-16
*/
@DubboService
public class PCMerchTradeCommentImgRpc implements IPCMerchTradeCommentImgRpc{
    @Autowired
    private IPCMerchTradeCommentImgService  pCMerchTradeCommentImgService;

    @Override
    public PageData<PCMerchTradeCommentImgVO.ListVO> pageData(PCMerchTradeCommentImgQTO.QTO qto){
        return pCMerchTradeCommentImgService.pageData(qto);
    }

    @Override
    public void addTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto){
        pCMerchTradeCommentImgService.addTradeCommentImg(eto);
    }

    @Override
    public void deleteTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto){
        pCMerchTradeCommentImgService.deleteTradeCommentImg(dto);
    }


    @Override
    public void editTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto){
        pCMerchTradeCommentImgService.editTradeCommentImg(eto);
    }

    @Override
    public PCMerchTradeCommentImgVO.DetailVO detailTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto){
        return  pCMerchTradeCommentImgService.detailTradeCommentImg(dto);
    }

}