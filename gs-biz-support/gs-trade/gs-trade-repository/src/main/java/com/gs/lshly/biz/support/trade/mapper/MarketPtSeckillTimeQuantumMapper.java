package com.gs.lshly.biz.support.trade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillTimeQuantum;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;

/**
 * <p>
 * 平台秒杀活动 Mapper 接口
 * </p>
 *
 * @author hanly
 * @since 2021-06-01
 */
public interface MarketPtSeckillTimeQuantumMapper extends BaseMapper<MarketPtSeckillTimeQuantum> {
	
	
	@Select("select date_format(start_time, '%H') as hh,t.* from gs_market_pt_seckill_time_quantum t "
			+ " where t.start_time>='${startTime}' and t.start_time<='${endTime}' "
			+ " order by t.start_time ASC")
	List<BbcMarketSeckillVO.MarketPtSeckillTimeQuantumVO> list(@Param("startTime") String startTime,@Param("endTime") String endTime);
}
