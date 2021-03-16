package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MoziSMSDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IPCMerchTradeGoodsRpc {

    PageData<PCMerchTradeGoodsVO.ListVO> pageData(PCMerchTradeGoodsQTO.QTO qto);

    void addTradeGoods(PCMerchTradeGoodsDTO.ETO eto);

    void deleteTradeGoods(PCMerchTradeGoodsDTO.IdDTO dto);


    void editTradeGoods(PCMerchTradeGoodsDTO.ETO eto);

    PCMerchTradeGoodsVO.DetailVO detailTradeGoods(PCMerchTradeGoodsDTO.IdDTO dto);

    List<TradeGoodsVO.GoodsSaleVO> goodsSaleList(TradeGoodsDTO.GoodsSale dto);

    List<TradeGoodsVO.GoodsSaleVO> goodsSaleListDetail(TradeGoodsDTO.GoodsSale dto);

    TradeVO.SalesSummaryVO salesSummaryList(BaseDTO dto);

    TradeVO.PerformanceVO performanceList(TradeDTO.PerformanceListDTO dto);

    TradeVO.ClientListVO clientList(TradeDTO.ClientListDTO dto);

    TradeVO.GoodsDateVO goodsDateList(TradeDTO.PerformanceListDTO dto);

    String sendContentDate(MoziSMSDTO.SendContentDTO dto);

    String TemplateDate(MoziSMSDTO.TemplateDTO dto);

    String balanceQueryDate(MoziSMSDTO.BalanceQueryDTO balanceQueryDTO);

    String testDete(String mobiles, String sign, String parameter, String merchantOrderId);
}