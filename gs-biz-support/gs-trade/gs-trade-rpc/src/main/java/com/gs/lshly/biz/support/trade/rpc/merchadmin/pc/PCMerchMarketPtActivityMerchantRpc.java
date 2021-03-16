package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtActivityMerchantService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtActivityMerchantRpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zdf
* @since 2020-12-01
*/
@DubboService
public class PCMerchMarketPtActivityMerchantRpc implements IPCMerchMarketPtActivityMerchantRpc{
    @Autowired
    private IPCMerchMarketPtActivityMerchantService pCMerchMarketPtActivityMerchantService;

    @Override
    public PageData<PCMerchMarketPtActivityMerchantVO.ListVO> pageData(PCMerchMarketPtActivityMerchantQTO.QTO qto){
        return pCMerchMarketPtActivityMerchantService.pageData(qto);
    }

    @Override
    public void addMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.ETO eto){
        pCMerchMarketPtActivityMerchantService.addMarketPtActivityMerchant(eto);
    }

    @Override
    public void deleteMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.IdDTO dto){
        pCMerchMarketPtActivityMerchantService.deleteMarketPtActivityMerchant(dto);
    }


    @Override
    public void editMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.ETO eto){
        pCMerchMarketPtActivityMerchantService.editMarketPtActivityMerchant(eto);
    }

    @Override
    public PCMerchMarketPtActivityMerchantVO.DetailVO detailMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.IdDTO dto){
        return  pCMerchMarketPtActivityMerchantService.detailMarketPtActivityMerchant(dto);
    }

    @Override
    public List<PCMerchMarketPtActivityMerchantVO.ListVO> queryCountRecord(PCMerchMarketPtActivityMerchantDTO.isRecordDTO dto) {
        return pCMerchMarketPtActivityMerchantService.queryCountRecord(dto);
    }

    @Override
    public PageData<PCMerchMarketPtActivityMerchantVO.MyMerchantActivity> queryMyList(PCMerchMarketPtActivityMerchantQTO.QTO qto) {
        return pCMerchMarketPtActivityMerchantService.queryMyList(qto);
    }

    @Override
    public void merchantActivitySign(PCMerchMarketPtActivityGoodsSpuDTO.Sign dto) {
        pCMerchMarketPtActivityMerchantService.merchantActivitySign(dto);
    }

    @Override
    public MarketPtActivityVO.MerchantViewDetails viewMyOrHistoryDetails(PCMerchMarketPtActivityMerchantDTO.IdDTO dto) {
        return pCMerchMarketPtActivityMerchantService.viewMyOrHistoryDetails(dto);
    }

    @Override
    public MarketPtActivityVO.MerchantViewDetails viewActivityListDetails(MarketPtActivityDTO.IdDTO dto) {
        return pCMerchMarketPtActivityMerchantService.viewActivityListDetails(dto) ;
    }

}