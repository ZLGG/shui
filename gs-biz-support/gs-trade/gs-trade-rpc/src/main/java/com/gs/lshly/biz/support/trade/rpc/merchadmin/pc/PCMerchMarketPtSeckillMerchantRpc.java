package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtSeckillMerchantService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillMerchantRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 下午5:15:46
 */
@DubboService
public class PCMerchMarketPtSeckillMerchantRpc implements IPCMerchMarketPtSeckillMerchantRpc{
    
	@Autowired
    private IPCMerchMarketPtSeckillMerchantService pCMerchMarketPtSeckillMerchantService;

    @Override
    public PageData<PCMerchMarketPtSeckillMerchantVO.ListVO> pageData(PCMerchMarketPtSeckillMerchantQTO.QTO qto){
        return pCMerchMarketPtSeckillMerchantService.pageData(qto);
    }

    @Override
    public void addMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.ETO eto){
        pCMerchMarketPtSeckillMerchantService.addMarketPtSeckillMerchant(eto);
    }

    @Override
    public void deleteMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto){
        pCMerchMarketPtSeckillMerchantService.deleteMarketPtSeckillMerchant(dto);
    }


    @Override
    public void editMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.ETO eto){
        pCMerchMarketPtSeckillMerchantService.editMarketPtSeckillMerchant(eto);
    }

    @Override
    public PCMerchMarketPtSeckillMerchantVO.DetailVO detailMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto){
        return  pCMerchMarketPtSeckillMerchantService.detailMarketPtSeckillMerchant(dto);
    }

    @Override
    public List<PCMerchMarketPtSeckillMerchantVO.ListVO> queryCountRecord(PCMerchMarketPtSeckillMerchantDTO.isRecordDTO dto) {
        return pCMerchMarketPtSeckillMerchantService.queryCountRecord(dto);
    }

    @Override
    public PageData<PCMerchMarketPtSeckillMerchantVO.MyMerchantSeckill> queryMyList(PCMerchMarketPtSeckillMerchantQTO.QTO qto) {
        return pCMerchMarketPtSeckillMerchantService.queryMyList(qto);
    }

    @Override
    public void merchantSeckillSign(PCMerchMarketPtSeckillGoodsSpuDTO.Sign dto) {
        pCMerchMarketPtSeckillMerchantService.merchantSeckillSign(dto);
    }

    @Override
    public MarketPtSeckillVO.MerchantViewDetails viewMyOrHistoryDetails(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto) {
        return pCMerchMarketPtSeckillMerchantService.viewMyOrHistoryDetails(dto);
    }

    @Override
    public MarketPtSeckillVO.MerchantViewDetails viewSeckillListDetails(MarketPtSeckillDTO.IdDTO dto) {
        return pCMerchMarketPtSeckillMerchantService.viewSeckillListDetails(dto) ;
    }

}