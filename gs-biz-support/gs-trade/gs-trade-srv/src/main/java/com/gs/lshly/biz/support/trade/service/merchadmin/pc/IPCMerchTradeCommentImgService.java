package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentImgVO;

public interface IPCMerchTradeCommentImgService {

    /**
     * 添加图片凭证信息
     * @param eto
     */
    void addTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto);

    PageData<PCMerchTradeCommentImgVO.ListVO> pageData(PCMerchTradeCommentImgQTO.QTO qto);

    void deleteTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto);


    void editTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto);

    PCMerchTradeCommentImgVO.DetailVO detailTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto);

}