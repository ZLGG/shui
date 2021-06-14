package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;

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

    PCMerchMarketPtSeckillVO.SpuVO allSpu(PCMerchMarketPtSeckillQTO.AllSpuQTO qto);

    PageData<PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO> seckillSpuGoods(PCMerchMarketPtSeckillQTO.SpuQTO qto);

    void addSpuGoods(PCMerchMarketPtSeckillDTO.SpuIdETO dto);

    void addSkuGoodsDetail(PCMerchMarketPtSeckillDTO.EndGoods dto);

    void delSpu(PCMerchMarketPtSeckillDTO.SpuIdList dto);
}