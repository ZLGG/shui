package com.gs.lshly.biz.support.trade.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeGoodsVO;
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
public class H5MerchTradeGoodsServiceImpl implements IH5MerchTradeGoodsService {

    @Autowired
    private ITradeGoodsRepository repository;

    @Override
    public PageData<H5MerchTradeGoodsVO.ListVO> pageData(H5MerchTradeGoodsQTO.QTO qto) {
        QueryWrapper<TradeGoods> wrapper = new QueryWrapper<>();
        IPage<TradeGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, H5MerchTradeGoodsVO.ListVO.class, page);
    }

    @Override
    public void addTradeGoods(H5MerchTradeGoodsDTO.ETO eto) {
        TradeGoods tradeGoods = new TradeGoods();
        BeanUtils.copyProperties(eto, tradeGoods);
        repository.save(tradeGoods);
    }


    @Override
    public void deleteTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeGoods(H5MerchTradeGoodsDTO.ETO eto) {
        TradeGoods tradeGoods = new TradeGoods();
        BeanUtils.copyProperties(eto, tradeGoods);
        repository.updateById(tradeGoods);
    }

    @Override
    public H5MerchTradeGoodsVO.DetailVO detailTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto) {
        TradeGoods tradeGoods = repository.getById(dto.getId());
        H5MerchTradeGoodsVO.DetailVO detailVo = new H5MerchTradeGoodsVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeGoods)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeGoods, detailVo);
        return detailVo;
    }

}
