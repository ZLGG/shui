package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsGoodsVO;

/**
*
* @author zdf
* @since 2020-12-17
*/
public interface IPCMerchTradeRightsGoodsRpc {

    PageData<PCMerchTradeRightsGoodsVO.ListVO> pageData(PCMerchTradeRightsGoodsQTO.QTO qto);

    void addTradeRightsGoods(PCMerchTradeRightsGoodsDTO.ETO eto);

    void deleteTradeRightsGoods(PCMerchTradeRightsGoodsDTO.IdDTO dto);


    void editTradeRightsGoods(PCMerchTradeRightsGoodsDTO.ETO eto);

    PCMerchTradeRightsGoodsVO.DetailVO detailTradeRightsGoods(PCMerchTradeRightsGoodsDTO.IdDTO dto);

}