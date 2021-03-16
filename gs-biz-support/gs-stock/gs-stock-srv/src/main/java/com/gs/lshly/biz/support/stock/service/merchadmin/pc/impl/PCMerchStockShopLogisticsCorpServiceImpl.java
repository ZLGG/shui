package com.gs.lshly.biz.support.stock.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsCorp;
import com.gs.lshly.biz.support.stock.entity.StockShopLogisticsCorp;
import com.gs.lshly.biz.support.stock.mapper.StockShopLogisticsCorpMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockShopLogisticsCorpView;
import com.gs.lshly.biz.support.stock.repository.IStockLogisticsCorpRepository;
import com.gs.lshly.biz.support.stock.repository.IStockShopLogisticsCorpRepository;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchStockShopLogisticsCorpService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockShopLogisticsCorpDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockShopLogisticsCorpQTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockShopLogisticsCorpVO;
import com.gs.lshly.common.utils.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author R
* @since 2020-10-24
*/
@Component
public class PCMerchStockShopLogisticsCorpServiceImpl implements IPCMerchStockShopLogisticsCorpService {

    @Autowired
    private IStockShopLogisticsCorpRepository repository;

    @Autowired
    private IStockLogisticsCorpRepository stockLogisticsCorpRepository;


    @Autowired
    private StockShopLogisticsCorpMapper stockShopLogisticsCorpMapper;


    @Override
    public List<PCMerchStockShopLogisticsCorpVO.ListVO> pageData(PCMerchStockShopLogisticsCorpQTO.QTO qto) {
        List<StockLogisticsCorp> corpList =  stockLogisticsCorpRepository.list();
        List<PCMerchStockShopLogisticsCorpVO.ListVO> voList = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(corpList)){
            QueryWrapper<StockShopLogisticsCorp> shopLogisticsCorpQuery = new QueryWrapper<>();
            shopLogisticsCorpQuery.eq("shop_id",qto.getJwtShopId());
            List<StockShopLogisticsCorp> stockShopLogisticsCorpList = repository.list(shopLogisticsCorpQuery);
            List<String> idList = ListUtil.getIdList(StockShopLogisticsCorp.class,stockShopLogisticsCorpList,"logisticsCorpId");
            for(StockLogisticsCorp stockLogisticsCorp:corpList){
                PCMerchStockShopLogisticsCorpVO.ListVO listVO = new PCMerchStockShopLogisticsCorpVO.ListVO();
                BeanUtils.copyProperties(stockLogisticsCorp,listVO);
                listVO.setState(TrueFalseEnum.否.getCode());
                if(ObjectUtils.isNotEmpty(idList)){
                    for(StockShopLogisticsCorp stockShopLogisticsCorp:stockShopLogisticsCorpList){
                        if(stockShopLogisticsCorp.getLogisticsCorpId().equals(stockLogisticsCorp.getId())){
                            if(TrueFalseEnum.是.getCode().equals( stockShopLogisticsCorp.getState())){
                                listVO.setState(TrueFalseEnum.是.getCode());
                            }
                        }
                    }
                }
                voList.add(listVO);
            }
        }
        return voList;
    }

    @Override
    public List<PCMerchStockShopLogisticsCorpVO.ListVO> enableList(BaseDTO dto) {
        QueryWrapper<StockShopLogisticsCorpView> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sc.shop_id",dto.getJwtShopId());
        queryWrapper.eq("sc.state",TrueFalseEnum.是.getCode());
        List<StockShopLogisticsCorpView> viewList = stockShopLogisticsCorpMapper.mapperListShopLogisticsCorp(queryWrapper);
        return  ListUtil.listCover(PCMerchStockShopLogisticsCorpVO.ListVO.class,viewList);
    }

    @Override
    public void enable(PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO) {
        QueryWrapper<StockShopLogisticsCorp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("logistics_corp_id",idDTO.getId());
        queryWrapper.eq("shop_id",idDTO.getJwtShopId());
        List<StockShopLogisticsCorp> shopLogisticsCorpList = repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(shopLogisticsCorpList)){
            StockShopLogisticsCorp stockShopLogisticsCorp = new StockShopLogisticsCorp();
            stockShopLogisticsCorp.setLogisticsCorpId(idDTO.getId());
            stockShopLogisticsCorp.setState(TrueFalseEnum.是.getCode());
            stockShopLogisticsCorp.setShopId(idDTO.getJwtShopId());
            stockShopLogisticsCorp.setMerchantId(idDTO.getJwtMerchantId());
            repository.save(stockShopLogisticsCorp);
        }else{
            StockShopLogisticsCorp stockShopLogisticsCorp = shopLogisticsCorpList.get(0);
            stockShopLogisticsCorp.setState(TrueFalseEnum.是.getCode());
            repository.updateById(stockShopLogisticsCorp);
        }
    }
    @Override
    public void disable(PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO) {
        QueryWrapper<StockShopLogisticsCorp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("logistics_corp_id",idDTO.getId());
        queryWrapper.eq("shop_id",idDTO.getJwtShopId());
        List<StockShopLogisticsCorp> shopLogisticsCorpList = repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(shopLogisticsCorpList)){
            StockShopLogisticsCorp stockShopLogisticsCorp = new StockShopLogisticsCorp();
            stockShopLogisticsCorp.setLogisticsCorpId(idDTO.getId());
            stockShopLogisticsCorp.setState(TrueFalseEnum.否.getCode());
            stockShopLogisticsCorp.setShopId(idDTO.getJwtShopId());
            stockShopLogisticsCorp.setMerchantId(idDTO.getJwtMerchantId());
            repository.save(stockShopLogisticsCorp);
        }else{
            StockShopLogisticsCorp stockShopLogisticsCorp = shopLogisticsCorpList.get(0);
            stockShopLogisticsCorp.setState(TrueFalseEnum.否.getCode());
            repository.updateById(stockShopLogisticsCorp);
        }
    }
}
