package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsImgVO;

public interface IPCMerchTradeRightsImgService {

    PageData<PCMerchTradeRightsImgVO.ListVO> pageData(PCMerchTradeRightsImgQTO.QTO qto);

    void addTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto);

    void deleteTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto);


    void editTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto);

    PCMerchTradeRightsImgVO.DetailVO detailTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto);

}