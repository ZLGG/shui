package com.gs.lshly.biz.support.trade.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradePayService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradePayDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradePayQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradePayVO;
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
public class H5MerchTradePayServiceImpl implements IH5MerchTradePayService {

    @Autowired
    private ITradePayRepository repository;

    @Override
    public PageData<H5MerchTradePayVO.ListVO> pageData(H5MerchTradePayQTO.QTO qto) {
        QueryWrapper<TradePay> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradePay> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, H5MerchTradePayVO.ListVO.class, page);
    }

    @Override
    public void addTradePay(H5MerchTradePayDTO.ETO eto) {
        TradePay tradePay = new TradePay();
        BeanUtils.copyProperties(eto, tradePay);
        repository.save(tradePay);
    }


    @Override
    public void deleteTradePay(H5MerchTradePayDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradePay(H5MerchTradePayDTO.ETO eto) {
        TradePay tradePay = new TradePay();
        BeanUtils.copyProperties(eto, tradePay);
        repository.updateById(tradePay);
    }

    @Override
    public H5MerchTradePayVO.DetailVO detailTradePay(H5MerchTradePayDTO.IdDTO dto) {
        TradePay tradePay = repository.getById(dto.getId());
        H5MerchTradePayVO.DetailVO detailVo = new H5MerchTradePayVO.DetailVO();
        if(ObjectUtils.isEmpty(tradePay)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradePay, detailVo);
        return detailVo;
    }

}
