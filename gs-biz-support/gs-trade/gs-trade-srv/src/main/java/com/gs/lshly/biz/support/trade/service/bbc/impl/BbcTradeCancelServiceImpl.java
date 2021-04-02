package com.gs.lshly.biz.support.trade.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeCancelService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCancelQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCancelVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-20
*/
@Component
public class BbcTradeCancelServiceImpl implements IBbcTradeCancelService {

    @Autowired
    private ITradeCancelRepository repository;

    @Override
    public PageData<BbcTradeCancelVO.ListVO> pageData(BbcTradeCancelQTO.QTO qto) {
        QueryWrapper<TradeCancel> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradeCancel> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcTradeCancelVO.ListVO.class, page);
    }

    @Override
    public void addTradeCancel(BbcTradeCancelDTO.ETO eto) {
        TradeCancel tradeCancel = new TradeCancel();
        BeanUtils.copyProperties(eto, tradeCancel);
        repository.save(tradeCancel);
    }


    @Override
    public void deleteTradeCancel(BbcTradeCancelDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeCancel(BbcTradeCancelDTO.ETO eto) {
        TradeCancel tradeCancel = new TradeCancel();
        BeanUtils.copyProperties(eto, tradeCancel);
        repository.updateById(tradeCancel);
    }

    @Override
    public BbcTradeCancelVO.DetailVO detailTradeCancel(BbcTradeCancelDTO.IdDTO dto) {
        TradeCancel tradeCancel = repository.getById(dto.getId());
        BbcTradeCancelVO.DetailVO detailVo = new BbcTradeCancelVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeCancel)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeCancel, detailVo);
        return detailVo;
    }

}
