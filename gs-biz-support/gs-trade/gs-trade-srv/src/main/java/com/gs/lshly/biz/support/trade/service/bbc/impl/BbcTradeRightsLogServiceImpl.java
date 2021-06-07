package com.gs.lshly.biz.support.trade.service.bbc.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsLogService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsLogQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsLogVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
