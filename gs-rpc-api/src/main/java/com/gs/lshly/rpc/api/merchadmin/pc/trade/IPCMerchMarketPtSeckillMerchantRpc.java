package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

/**
 * 秒杀商家管理
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 上午10:14:45
 */
public interface IPCMerchMarketPtSeckillMerchantRpc {

    PageData<PCMerchMarketPtSeckillMerchantVO.ListVO> pageData(PCMerchMarketPtSeckillMerchantQTO.QTO qto);

    void addMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.ETO eto);

    void deleteMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto);


    void editMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.ETO eto);

    PCMerchMarketPtSeckillMerchantVO.DetailVO detailMarketPtSeckillMerchant(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto);

    List<PCMerchMarketPtSeckillMerchantVO.ListVO> queryCountRecord(PCMerchMarketPtSeckillMerchantDTO.isRecordDTO dto);

    PageData<PCMerchMarketPtSeckillMerchantVO.MyMerchantSeckill> queryMyList(PCMerchMarketPtSeckillMerchantQTO.QTO qto);

    void merchantSeckillSign(PCMerchMarketPtSeckillGoodsSpuDTO.Sign dto);

    MarketPtSeckillVO.MerchantViewDetails viewMyOrHistoryDetails(PCMerchMarketPtSeckillMerchantDTO.IdDTO dto);

    MarketPtSeckillVO.MerchantViewDetails viewSeckillListDetails(MarketPtSeckillDTO.IdDTO dto);
}