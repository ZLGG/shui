package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillService;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillTimeQuantumService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillTimeQuantumDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillTimeQuantumQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillTimeQuantumVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtSeckillRpc;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtSeckillTimeQuantumRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 活动时间段
 *
 * @author hanly
 * @date 2021年6月1日 上午10:50:13
 */
@DubboService
public class MarketPtSeckillTimeQuantumRpc implements IMarketPtSeckillTimeQuantumRpc {

    @Autowired
    private IMarketPtSeckillTimeQuantumService marketPtSeckillTimeQuantumService;

    @Override
    public PageData<MarketPtSeckillTimeQuantumVO.ListVO> pageTimeQuantumlist(MarketPtSeckillTimeQuantumQTO.QTO qto) {
        return marketPtSeckillTimeQuantumService.pageTimeQuantumlist(qto);
    }

    @Override
    public MarketPtSeckillTimeQuantumVO.ListVO timeQuantum(MarketPtSeckillTimeQuantumQTO.IdQTO qto) {
        return marketPtSeckillTimeQuantumService.timeQuantum(qto);
    }

    @Override
    public void addTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto) {
        marketPtSeckillTimeQuantumService.addTimeQuantum(dto);
    }

    @Override
    public void updateTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto) {
        marketPtSeckillTimeQuantumService.updateTimeQuantum(dto);
    }

    @Override
    public void deleteTimeQuantum(MarketPtSeckillTimeQuantumDTO.IdDTO dto) {
        marketPtSeckillTimeQuantumService.deleteTimeQuantum(dto);
    }
}