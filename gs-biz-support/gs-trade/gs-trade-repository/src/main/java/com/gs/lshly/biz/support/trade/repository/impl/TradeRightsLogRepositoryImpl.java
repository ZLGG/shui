package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.entity.TradeRightsLog;
import com.gs.lshly.biz.support.trade.mapper.TradeRightsLogMapper;
import com.gs.lshly.biz.support.trade.mapper.TradeRightsMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsLogRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRepository;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeRightsQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeRightsVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务处理日志 实现类
 * </p>
*
* @author hanly
 * @since 2021-06-03
*/
@Service
public class TradeRightsLogRepositoryImpl extends ServiceImpl<TradeRightsLogMapper, TradeRightsLog> implements ITradeRightsLogRepository {
}