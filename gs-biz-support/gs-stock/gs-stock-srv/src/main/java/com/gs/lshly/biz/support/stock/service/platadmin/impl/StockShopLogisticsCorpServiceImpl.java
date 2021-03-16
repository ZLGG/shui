package com.gs.lshly.biz.support.stock.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockShopLogisticsCorp;
import com.gs.lshly.biz.support.stock.repository.IStockShopLogisticsCorpRepository;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockShopLogisticsCorpService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockShopLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockShopLogisticsCorpQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockShopLogisticsCorpVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author zzg
* @since 2020-10-23
*/
@Component
public class StockShopLogisticsCorpServiceImpl implements IStockShopLogisticsCorpService {

    @Autowired
    private IStockShopLogisticsCorpRepository repository;

    @Override
    public PageData<StockShopLogisticsCorpVO.ListVO> pageData(StockShopLogisticsCorpQTO.QTO qto) {
        QueryWrapper<StockShopLogisticsCorp> wrapper = new QueryWrapper<>();
        IPage<StockShopLogisticsCorp> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, StockShopLogisticsCorpVO.ListVO.class, page);
    }

    @Override
    public void addStockShopLogisticsCorp(StockShopLogisticsCorpDTO.ETO eto) {
        StockShopLogisticsCorp stockShopLogisticsCorp = new StockShopLogisticsCorp();
        BeanUtils.copyProperties(eto, stockShopLogisticsCorp);
        repository.save(stockShopLogisticsCorp);
    }


    @Override
    public void deleteStockShopLogisticsCorp(StockShopLogisticsCorpDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editStockShopLogisticsCorp(StockShopLogisticsCorpDTO.ETO eto) {
        StockShopLogisticsCorp stockShopLogisticsCorp = new StockShopLogisticsCorp();
        BeanUtils.copyProperties(eto, stockShopLogisticsCorp);
        repository.updateById(stockShopLogisticsCorp);
    }

    @Override
    public StockShopLogisticsCorpVO.DetailVO detailStockShopLogisticsCorp(StockShopLogisticsCorpDTO.IdDTO dto) {
        StockShopLogisticsCorp stockShopLogisticsCorp = repository.getById(dto.getId());
        StockShopLogisticsCorpVO.DetailVO detailVo = new StockShopLogisticsCorpVO.DetailVO();
        if(ObjectUtils.isEmpty(stockShopLogisticsCorp)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(stockShopLogisticsCorp, detailVo);
        return detailVo;
    }

}
