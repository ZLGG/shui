package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;

import java.util.List;

public interface IPCMerchMarketPtSeckillService {
    PageData<PCMerchMarketPtSeckillVO.ListVO> seckillList(PCMerchMarketPtSeckillQTO.QTO qto);

    PageData<PCMerchMarketPtSeckillVO.SessionVO> seckillTimeQuantum(PCMerchMarketPtSeckillQTO.IdQTO qto);

    PCMerchMarketPtSeckillVO.SpuVO allSpu(PCMerchMarketPtSeckillQTO.AllSpuQTO qto);

    PageData<PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO> seckillSpuGoods(PCMerchMarketPtSeckillQTO.SpuQTO qto);

    void addSpuGoods(PCMerchMarketPtSeckillDTO.SpuIdETO dto);

    void addSkuGoodsDetail(PCMerchMarketPtSeckillDTO.EndGoods dto);

    void delSpu(PCMerchMarketPtSeckillDTO.SpuIdList dto);
}