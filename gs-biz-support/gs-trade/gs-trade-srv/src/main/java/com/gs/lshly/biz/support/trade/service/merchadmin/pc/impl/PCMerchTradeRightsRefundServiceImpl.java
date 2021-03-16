package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeRightsRefund;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRefundRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsRefundService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRefundDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRefundQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRefundVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-17
*/
@Component
public class PCMerchTradeRightsRefundServiceImpl implements IPCMerchTradeRightsRefundService {

    @Autowired
    private ITradeRightsRefundRepository repository;

    @Override
    public PageData<PCMerchTradeRightsRefundVO.ListVO> pageData(PCMerchTradeRightsRefundQTO.QTO qto) {
        QueryWrapper<TradeRightsRefund> wrapper = new QueryWrapper<>();
        IPage<TradeRightsRefund> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeRightsRefundVO.ListVO.class, page);
    }

    @Override
    public void addTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto) {
        TradeRightsRefund tradeRightsRefund = new TradeRightsRefund();
        BeanUtils.copyProperties(eto, tradeRightsRefund);
        repository.save(tradeRightsRefund);
    }


    @Override
    public void deleteTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto) {
        TradeRightsRefund tradeRightsRefund = new TradeRightsRefund();
        BeanUtils.copyProperties(eto, tradeRightsRefund);
        repository.updateById(tradeRightsRefund);
    }

    @Override
    public PCMerchTradeRightsRefundVO.DetailVO detailTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto) {
        TradeRightsRefund tradeRightsRefund = repository.getById(dto.getId());
        PCMerchTradeRightsRefundVO.DetailVO detailVo = new PCMerchTradeRightsRefundVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeRightsRefund)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRightsRefund, detailVo);
        return detailVo;
    }

}
