package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeCancelService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCancelDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCancelQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCancelVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-20
*/
@Component
public class BbbH5TradeCancelServiceImpl implements IBbbH5TradeCancelService {

    @Autowired
    private ITradeCancelRepository repository;

    @Override
    public PageData<BbbH5TradeCancelVO.ListVO> pageData(BbbH5TradeCancelQTO.QTO qto) {
        QueryWrapper<TradeCancel> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradeCancel> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbbH5TradeCancelVO.ListVO.class, page);
    }

    @Override
    public void addTradeCancel(BbbH5TradeCancelDTO.ETO eto) {
        TradeCancel tradeCancel = new TradeCancel();
        BeanUtils.copyProperties(eto, tradeCancel);
        repository.save(tradeCancel);
    }


    @Override
    public void deleteTradeCancel(BbbH5TradeCancelDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeCancel(BbbH5TradeCancelDTO.ETO eto) {
        TradeCancel tradeCancel = new TradeCancel();
        BeanUtils.copyProperties(eto, tradeCancel);
        repository.updateById(tradeCancel);
    }

    @Override
    public BbbH5TradeCancelVO.DetailVO detailTradeCancel(BbbH5TradeCancelDTO.IdDTO dto) {
        TradeCancel tradeCancel = repository.getById(dto.getId());
        BbbH5TradeCancelVO.DetailVO detailVo = new BbbH5TradeCancelVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeCancel)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeCancel, detailVo);
        return detailVo;
    }

}
