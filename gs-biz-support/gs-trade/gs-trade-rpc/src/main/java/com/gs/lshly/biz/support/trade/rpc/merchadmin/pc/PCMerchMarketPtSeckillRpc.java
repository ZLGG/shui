package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtSeckillService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtSeckillRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yingjun
 * @date 2021年5月8日 下午5:15:46
 */
@DubboService
public class PCMerchMarketPtSeckillRpc implements IPCMerchMarketPtSeckillRpc {

    @Autowired
    private IPCMerchMarketPtSeckillService ipcMerchMarketPtSeckillService;

    @Override
    public PageData<PCMerchMarketPtSeckillVO.ListVO> seckillList(PCMerchMarketPtSeckillQTO.QTO qto) {
        return ipcMerchMarketPtSeckillService.seckillList(qto);
    }

    @Override
    public PageData<PCMerchMarketPtSeckillVO.SessionVO> seckillTimeQuantum(PCMerchMarketPtSeckillQTO.IdQTO qto) {
        return ipcMerchMarketPtSeckillService.seckillTimeQuantum(qto);
    }

    @Override
    public PCMerchMarketPtSeckillVO.SpuVO allSpu(PCMerchMarketPtSeckillQTO.AllSpuQTO qto) {
        return ipcMerchMarketPtSeckillService.allSpu(qto);
    }

    @Override
    public PageData<PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO> seckillSpuGoods(PCMerchMarketPtSeckillQTO.SpuQTO qto) {
        return ipcMerchMarketPtSeckillService.seckillSpuGoods(qto);
    }

    @Override
    public void addSpuGoods(PCMerchMarketPtSeckillDTO.SpuIdETO dto) {
        ipcMerchMarketPtSeckillService.addSpuGoods(dto);
    }

    @Override
    public void addSkuGoodsDetail(PCMerchMarketPtSeckillDTO.EndGoods dto) {
        ipcMerchMarketPtSeckillService.addSkuGoodsDetail(dto);
    }

    @Override
    public void delSpu(PCMerchMarketPtSeckillDTO.SpuIdList dto) {
        ipcMerchMarketPtSeckillService.delSpu(dto);
    }
}