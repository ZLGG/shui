package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentImgVO;

/**
*
* @author Starry
* @since 2020-11-16
*/
public interface IPCMerchTradeCommentImgRpc {

    PageData<PCMerchTradeCommentImgVO.ListVO> pageData(PCMerchTradeCommentImgQTO.QTO qto);

    void addTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto);

    void deleteTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto);


    void editTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto);

    PCMerchTradeCommentImgVO.DetailVO detailTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto);

}