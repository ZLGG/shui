package com.gs.lshly.biz.support.stock.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockRegion;
import com.gs.lshly.biz.support.stock.repository.IStockRegionRepository;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockRegionService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockRegionDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockRegionQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockRegionVO;
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
* @since 2020-10-24
*/
@Component
public class StockRegionServiceImpl implements IStockRegionService {

    @Autowired
    private IStockRegionRepository repository;

    @Override
    public PageData<StockRegionVO.ListVO> pageData(StockRegionQTO.QTO qto) {
        QueryWrapper<StockRegion> wrapper = new QueryWrapper<>();
        IPage<StockRegion> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, StockRegionVO.ListVO.class, page);
    }

    @Override
    public void addStockRegion(StockRegionDTO.ETO eto) {
        StockRegion stockRegion = new StockRegion();
        BeanUtils.copyProperties(eto, stockRegion);
        repository.save(stockRegion);
    }


    @Override
    public void deleteStockRegion(StockRegionDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editStockRegion(StockRegionDTO.ETO eto) {
        StockRegion stockRegion = new StockRegion();
        BeanUtils.copyProperties(eto, stockRegion);
        repository.updateById(stockRegion);
    }

    @Override
    public StockRegionVO.DetailVO detailStockRegion(StockRegionDTO.IdDTO dto) {
        StockRegion stockRegion = repository.getById(dto.getId());
        StockRegionVO.DetailVO detailVo = new StockRegionVO.DetailVO();
        if(ObjectUtils.isEmpty(stockRegion)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(stockRegion, detailVo);
        return detailVo;
    }

}
