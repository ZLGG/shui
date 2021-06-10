package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.TradeRightsLog;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsLogRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsLogService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsLogQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsLogVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hanly
 * @since 2021-6-7
 */
@Component
@Slf4j
public class BbcTradeRightsLogServiceImpl implements IBbcTradeRightsLogService {

    @Autowired
    private ITradeRightsLogRepository tradeRightsLogRepository;


    @Override
    public List<BbcTradeRightsLogVO.listVO> rightsLog(BbcTradeRightsLogQTO.QTO qto) {
        if (ObjectUtil.isEmpty(qto) || StrUtil.isEmpty(qto.getRightsId())) {
            throw new BusinessException("参数为空!");
        }
        QueryWrapper<TradeRightsLog> query = MybatisPlusUtil.query();
        query.eq("rights_id", qto.getRightsId());
        query.orderByDesc("cdate");
        List<TradeRightsLog> tradeRightsLogList = tradeRightsLogRepository.list(query);
        List<BbcTradeRightsLogVO.listVO> listVOList = new ArrayList<>();
        for (TradeRightsLog tradeRightsLog : tradeRightsLogList) {
            BbcTradeRightsLogVO.listVO listVO = new BbcTradeRightsLogVO.listVO();
            listVO.setState(tradeRightsLog.getState());
            listVO.setContent(tradeRightsLog.getContent());
            listVO.setCdate(tradeRightsLog.getCdate());
            listVOList.add(listVO);
        }
        return listVOList;
    }
}
