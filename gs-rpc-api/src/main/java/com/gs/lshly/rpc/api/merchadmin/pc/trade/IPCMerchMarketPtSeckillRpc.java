package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

import java.util.List;

/**
 * 秒杀商家管理
 *
 * 
 * @author yingjun
 * @date 2021年6月10日 上午10:14:45
 */
public interface IPCMerchMarketPtSeckillRpc {
    PageData<PCMerchMarketPtSeckillVO.ListVO> seckillList(PCMerchMarketPtSeckillQTO.QTO qto);

    PageData<PCMerchMarketPtSeckillVO.SessionVO> seckillTimeQuantum(PCMerchMarketPtSeckillQTO.IdQTO qto);

    PageData<PCMerchMarketPtSeckillVO.AllSpuVO> allSpu(PCMerchMarketPtSeckillQTO.AllSpuQTO qto);

    PageData<PCMerchMarketPtSeckillVO.AllSkuVO> allSku(PCMerchMarketPtSeckillQTO.AllSkuQTO qto);

    PageData<PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO> seckillSpuGoods(PCMerchMarketPtSeckillQTO.SpuQTO qto);

    PageData<PCMerchMarketPtSeckillVO.SkuGoodsInfoVO> seckillSkuGoods(PCMerchMarketPtSeckillQTO.SkuQTO qto);

    void addSpuGoods(PCMerchMarketPtSeckillDTO.SpuIdETO dto);

    void addSkuGoods(PCMerchMarketPtSeckillDTO.SpuIdETO dto);

    void addSkuGoodsDetail(PCMerchMarketPtSeckillDTO.AddSeckillGoodsETO dto);

    void delSpu(PCMerchMarketPtSeckillDTO.SpuIdETO dto);
}