package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillTimeQuantumDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillTimeQuantumQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillTimeQuantumVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

import java.util.List;

/**
 * 
 *
 * 
 * @author hanly
 * @date 2021年6月1日 上午10:54:42
 */
public interface IMarketPtSeckillTimeQuantumService {
    PageData<MarketPtSeckillTimeQuantumVO.ListVO> pageTimeQuantumlist(MarketPtSeckillTimeQuantumQTO.QTO qto);

    MarketPtSeckillTimeQuantumVO.ListVO timeQuantum(MarketPtSeckillTimeQuantumQTO.IdQTO qto);

    void addTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto);

    void updateTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto);

    void deleteTimeQuantum(MarketPtSeckillTimeQuantumDTO.IdDTO dto);
}