package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeGoodsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MoziSMSDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class PCMerchTradeGoodsRpc implements IPCMerchTradeGoodsRpc{
    @Autowired
    private IPCMerchTradeGoodsService  pCMerchTradeGoodsService;

    @Override
    public PageData<PCMerchTradeGoodsVO.ListVO> pageData(PCMerchTradeGoodsQTO.QTO qto){
        return pCMerchTradeGoodsService.pageData(qto);
    }

    @Override
    public void addTradeGoods(PCMerchTradeGoodsDTO.ETO eto){
        pCMerchTradeGoodsService.addTradeGoods(eto);
    }

    @Override
    public void deleteTradeGoods(PCMerchTradeGoodsDTO.IdDTO dto){
        pCMerchTradeGoodsService.deleteTradeGoods(dto);
    }


    @Override
    public void editTradeGoods(PCMerchTradeGoodsDTO.ETO eto){
        pCMerchTradeGoodsService.editTradeGoods(eto);
    }

    @Override
    public PCMerchTradeGoodsVO.DetailVO detailTradeGoods(PCMerchTradeGoodsDTO.IdDTO dto){
        return  pCMerchTradeGoodsService.detailTradeGoods(dto);
    }

    @Override
    public List<TradeGoodsVO.GoodsSaleVO> goodsSaleList(TradeGoodsDTO.GoodsSale dto){
        return  pCMerchTradeGoodsService.goodsSaleList(dto);
    }

    @Override
    public List<TradeGoodsVO.GoodsSaleVO> goodsSaleListDetail(TradeGoodsDTO.GoodsSale dto){
        return  pCMerchTradeGoodsService.goodsSaleListDetail(dto);
    }

    //导出商品销售分析列表
    @Override
    public ExportDataDTO exportGoodsSaleList(TradeDTO.PayDateList qo) throws Exception {
        return ExcelUtil.treatmentBean(pCMerchTradeGoodsService.exportGoodsSaleList(qo), TradeGoodsVO.GoodsSaleVO.class);
    }

    //导出商品销售排行明细
    @Override
    public ExportDataDTO exportGoodsSaleListDetail(TradeDTO.PayDateList qo) throws Exception {
        return ExcelUtil.treatmentBean(pCMerchTradeGoodsService.exportGoodsSaleListDetail(qo), TradeGoodsVO.GoodsSaleVO.class);
    }

    @Override
    public TradeVO.SalesSummaryVO salesSummaryList(BaseDTO dto){
        return  pCMerchTradeGoodsService.salesSummaryList(dto);
    }

    @Override
    public TradeVO.PerformanceVO performanceList(TradeDTO.PerformanceListDTO dto){
        return  pCMerchTradeGoodsService.performanceList(dto);
    }

    @Override
    public TradeVO.ClientListVO clientList(TradeDTO.ClientListDTO dto){
        return  pCMerchTradeGoodsService.clientList(dto);
    }

    @Override
    public TradeVO.GoodsDateVO goodsDateList(TradeDTO.PerformanceListDTO dto){
        return  pCMerchTradeGoodsService.goodsDateList(dto);
    }

    @Override
    public String sendContentDate(MoziSMSDTO.SendContentDTO dto){
        return  pCMerchTradeGoodsService.sendContentDate(dto);
    }

    @Override
    public String TemplateDate(MoziSMSDTO.TemplateDTO dto){
        return  pCMerchTradeGoodsService.TemplateDate(dto);
    }

    @Override
    public String balanceQueryDate(MoziSMSDTO.BalanceQueryDTO dto){
        return  pCMerchTradeGoodsService.balanceQueryDate(dto);
    }

    @Override
    public String testDete(String mobiles, String sign, String parameter, String merchantOrderId){
        return  pCMerchTradeGoodsService.testDete(mobiles,sign,parameter,merchantOrderId);
    }


}