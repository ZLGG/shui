package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillTimeQuantumDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillTimeQuantumQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillTimeQuantumVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

import java.util.List;

/**
 * 活动时间段
 *
 * 
 * @author hanly
 * @date 2021年6月1日 上午10:34:45
 */
public interface IMarketPtSeckillTimeQuantumRpc {

    PageData<MarketPtSeckillTimeQuantumVO.ListVO> pageTimeQuantumlist(MarketPtSeckillTimeQuantumQTO.QTO qto);

    MarketPtSeckillTimeQuantumVO.ListVO timeQuantum(MarketPtSeckillTimeQuantumQTO.IdQTO qto);

    void addTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto);

    void updateTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto);

    void deleteTimeQuantum(MarketPtSeckillTimeQuantumDTO.IdDTO dto);
}