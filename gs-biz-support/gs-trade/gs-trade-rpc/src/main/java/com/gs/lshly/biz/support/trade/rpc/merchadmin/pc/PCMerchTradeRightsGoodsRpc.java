package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsGoodsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRightsGoodsRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-17
*/
@DubboService
public class PCMerchTradeRightsGoodsRpc implements IPCMerchTradeRightsGoodsRpc{
    @Autowired
    private IPCMerchTradeRightsGoodsService  pCMerchTradeRightsGoodsService;

    @Override
    public PageData<PCMerchTradeRightsGoodsVO.ListVO> pageData(PCMerchTradeRightsGoodsQTO.QTO qto){
        return pCMerchTradeRightsGoodsService.pageData(qto);
    }

    @Override
    public void addTradeRightsGoods(PCMerchTradeRightsGoodsDTO.ETO eto){
        pCMerchTradeRightsGoodsService.addTradeRightsGoods(eto);
    }

    @Override
    public void deleteTradeRightsGoods(PCMerchTradeRightsGoodsDTO.IdDTO dto){
        pCMerchTradeRightsGoodsService.deleteTradeRightsGoods(dto);
    }


    @Override
    public void editTradeRightsGoods(PCMerchTradeRightsGoodsDTO.ETO eto){
        pCMerchTradeRightsGoodsService.editTradeRightsGoods(eto);
    }

    @Override
    public PCMerchTradeRightsGoodsVO.DetailVO detailTradeRightsGoods(PCMerchTradeRightsGoodsDTO.IdDTO dto){
        return  pCMerchTradeRightsGoodsService.detailTradeRightsGoods(dto);
    }

}