package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradePayService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradePayDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradePayQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradePayVO;
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
* @since 2020-11-16
*/
@Component
public class PCMerchTradePayServiceImpl implements IPCMerchTradePayService {

    @Autowired
    private ITradePayRepository repository;

    @Override
    public PageData<PCMerchTradePayVO.ListVO> pageData(PCMerchTradePayQTO.QTO qto) {
        QueryWrapper<TradePay> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradePay> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradePayVO.ListVO.class, page);
    }

    @Override
    public void addTradePay(PCMerchTradePayDTO.ETO eto) {
        TradePay tradePay = new TradePay();
        BeanUtils.copyProperties(eto, tradePay);
        repository.save(tradePay);
    }


    @Override
    public void deleteTradePay(PCMerchTradePayDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradePay(PCMerchTradePayDTO.ETO eto) {
        TradePay tradePay = new TradePay();
        BeanUtils.copyProperties(eto, tradePay);
        repository.updateById(tradePay);
    }

    @Override
    public PCMerchTradePayVO.DetailVO detailTradePay(PCMerchTradePayDTO.IdDTO dto) {
        TradePay tradePay = repository.getById(dto.getId());
        PCMerchTradePayVO.DetailVO detailVo = new PCMerchTradePayVO.DetailVO();
        if(ObjectUtils.isEmpty(tradePay)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradePay, detailVo);
        return detailVo;
    }

}
