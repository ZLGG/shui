package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.trade.mapper.TradeGoodsMapper;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeGoodsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oy
 * @since 2020-11-16
 */
@Component
public class TradeGoodsServiceImpl implements ITradeGoodsService {

    @Autowired
    private ITradeGoodsRepository repository;
    @Autowired
    private TradeGoodsMapper  tradeGoodsMapper;

    @Override
    public PageData<TradeGoodsVO.ListVO> pageData(TradeGoodsQTO.QTO qto) {
        QueryWrapper<TradeGoods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradeGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, TradeGoodsVO.ListVO.class, page);
    }

    @Override
    public void addTradeGoods(TradeGoodsDTO.ETO eto) {
        TradeGoods tradeGoods = new TradeGoods();
        BeanUtils.copyProperties(eto, tradeGoods);
        repository.save(tradeGoods);
    }


    @Override
    public void deleteTradeGoods(TradeGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editTradeGoods(TradeGoodsDTO.ETO eto) {
        TradeGoods tradeGoods = new TradeGoods();
        BeanUtils.copyProperties(eto, tradeGoods);
        repository.updateById(tradeGoods);
    }

    @Override
    public TradeGoodsVO.DetailVO detailTradeGoods(TradeGoodsDTO.IdDTO dto) {
        TradeGoods tradeGoods = repository.getById(dto.getId());
        TradeGoodsVO.DetailVO detailVo = new TradeGoodsVO.DetailVO();
        if (ObjectUtils.isEmpty(tradeGoods)) {
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeGoods, detailVo);
        return detailVo;
    }

    @Override
    public List<TradeGoodsVO.ListVO> findListByGoodsId(GoodsInfoDTO.IdListDTO dto) {
        List<TradeGoods> tradeGoodsList = tradeGoodsMapper.selectList(Wrappers.<TradeGoods>lambdaQuery().in(TradeGoods::getGoodsId, dto.getIdList()));
        List<TradeGoodsVO.ListVO> listVOList = new ArrayList<>();
        for (TradeGoods tradeGoods : tradeGoodsList) {
            TradeGoodsVO.ListVO listVO = new TradeGoodsVO.DetailVO();
            BeanUtils.copyProperties(tradeGoods,listVO);
            listVOList.add(listVO);
        }
        return listVOList;
    }
}
