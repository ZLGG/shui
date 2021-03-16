package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsImgVO;

/**
*
* @author zdf
* @since 2020-12-17
*/
public interface IPCMerchTradeRightsImgRpc {

    PageData<PCMerchTradeRightsImgVO.ListVO> pageData(PCMerchTradeRightsImgQTO.QTO qto);

    void addTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto);

    void deleteTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto);


    void editTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto);

    PCMerchTradeRightsImgVO.DetailVO detailTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto);

}