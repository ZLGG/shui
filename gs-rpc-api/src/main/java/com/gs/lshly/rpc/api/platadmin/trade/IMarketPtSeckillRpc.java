package com.gs.lshly.rpc.api.platadmin.trade;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

/**
 * 秒杀活动
 *
 * 
 * @author yingjun
 * @date 2021年5月7日 上午10:34:45
 */
public interface IMarketPtSeckillRpc {

	/**
	 * 分页显示秒杀活动列表
	 * @param qto
	 * @return
	 */
    PageData<MarketPtSeckillVO.ListVO> pageData(MarketPtSeckillQTO.QTO qto);

    void addMarketPtSeckill(MarketPtSeckillDTO.ETO eto);

    void deleteMarketPtSeckill(MarketPtSeckillDTO.IdListDTO dto);

    void editMarketPtSeckill(MarketPtSeckillDTO.ETO eto);

    MarketPtSeckillVO.DetailVO detailMarketPtSeckill(MarketPtSeckillDTO.IdDTO dto);

    List<MarketPtSeckillVO.ListVO> list();

}