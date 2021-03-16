package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeDeliveryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDeliveryDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
public class TradeDeliveryServiceImpl implements ITradeDeliveryService {

    @Autowired
    private ITradeDeliveryRepository repository;
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;
    @DubboReference
    private IShopRpc iShopRpc;

    @Override
    public PageData<TradeDeliveryVO.ListVO> pageData(TradeDeliveryQTO.QTO qto) {
        QueryWrapper<TradeDeliveryQTO.QTO> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("1","1"));
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.and(i -> i.like("t.`trade_code`",qto.getTradeCode()));
        }
        if(ObjectUtils.isNotEmpty(qto.getLogisticsNumber())){
            wrapper.and(i -> i.like("td.`logistics_number`",qto.getLogisticsNumber()));
        }
        if(ObjectUtils.isNotEmpty(qto.getOperatorName())){
            wrapper.and(i -> i.like("td.`operator_name`",qto.getOperatorName()));
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeId())){
            wrapper.and(i -> i.like("td.`trade_id`",qto.getTradeId()));
        }
        IPage<TradeDeliveryVO.ListVO> page = MybatisPlusUtil.pager(qto);
        repository.selectListPageForPlatform(page, wrapper);
        List<String> shopId = new ArrayList<>();
        for (TradeDeliveryVO.ListVO listVO:page.getRecords()){
            if (StringUtils.isNotBlank(listVO.getShopId())){
                shopId.add(listVO.getShopId());
            }
        }
        List<TradeDeliveryVO.ListVO> records = page.getRecords();
        if (ObjectUtils.isNotEmpty(shopId)){
            ArrayList<String> shopIds = new ArrayList<>(new TreeSet<String>(shopId));
            setShopName(shopIds,records);
        }

        return new PageData<>(records,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }

    private void setShopName(List<String> shopId, List<TradeDeliveryVO.ListVO> records) {
        List<ShopVO.InnerSimpleVO> innerSimpleVOS = iShopRpc.innerlistShopIdName(new BaseDTO(), shopId);
        if (ObjectUtils.isNotEmpty(innerSimpleVOS)){
            for (ShopVO.InnerSimpleVO simpleVO:innerSimpleVOS){
                for (int i = 0; i < records.size(); i++) {
                    if (StringUtils.isNotBlank(records.get(i).getShopId())){
                        if (records.get(i).getShopId().equals(simpleVO.getId())){
                            records.get(i).setShopName(simpleVO.getShopName());

                        }
                    }

                }
            }
        }
    }

    @Override
    public void addTradeDelivery(TradeDeliveryDTO.ETO eto) {
        TradeDelivery tradeDelivery = new TradeDelivery();
        BeanUtils.copyProperties(eto, tradeDelivery);
        repository.save(tradeDelivery);
    }


    @Override
    public void deleteTradeDelivery(TradeDeliveryDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeDelivery(TradeDeliveryDTO.ETO eto) {
        TradeDelivery tradeDelivery = new TradeDelivery();
        BeanUtils.copyProperties(eto, tradeDelivery);
        repository.updateById(tradeDelivery);
    }

    @Override
    public TradeDeliveryVO.DetailVO detailTradeDelivery(TradeDeliveryDTO.IdDTO dto) {
        TradeDelivery tradeDelivery = repository.getById(dto.getId());
        TradeDeliveryVO.DetailVO detailVo = new TradeDeliveryVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeDelivery)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeDelivery, detailVo);
        if (StringUtils.isNotBlank(detailVo.getTradeId())){
            Trade trade = iTradeRepository.getById(detailVo.getTradeId());
            if (ObjectUtils.isNotEmpty(trade)){
                detailVo.setDeliveryAmount(trade.getDeliveryAmount()).
                        setDeliveryType(trade.getDeliveryType()).
                        setRecvFullAddres(trade.getRecvFullAddres()).
                        setRecvPersonName(trade.getRecvPersonName()).
                        setRecvPhone(trade.getRecvPhone());
            }
            QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_id",detailVo.getTradeId()));
            List<TradeGoods> list = iTradeGoodsRepository.list(query);
            if (ObjectUtils.isNotEmpty(list)) {
                List<TradeDeliveryVO.TradeGoodsVO> goodsVOS= list.parallelStream().map(i -> {
                    TradeDeliveryVO.TradeGoodsVO tradeGoodsVO = new TradeDeliveryVO.TradeGoodsVO();
                    tradeGoodsVO.setGoodsNo(i.getGoodsNo()).
                            setGoodsName(i.getGoodsName()).
                            setPayAmount(i.getPayAmount()).
                            setSalePrice(i.getSalePrice());
                    return tradeGoodsVO;
                }).collect(Collectors.toList());
                detailVo.setGoodsVOS(goodsVOS);
            }
        }
        return detailVo;
    }

}
