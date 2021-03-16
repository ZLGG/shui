package com.gs.lshly.biz.support.stock.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsWatch;
import com.gs.lshly.biz.support.stock.mapper.StockLogisticsWatchMapper;
import com.gs.lshly.biz.support.stock.repository.IStockLogisticsWatchRepository;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockLogisticsWatchService;
import com.gs.lshly.common.enums.StockLogisticesWatchEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsWatchDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsWatchQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsWatchVO;
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
public class StockLogisticsWatchServiceImpl implements IStockLogisticsWatchService {

    @Autowired
    private IStockLogisticsWatchRepository repository;

    @Autowired
    private StockLogisticsWatchMapper  mapper;

    @Override
    public PageData<StockLogisticsWatchVO.ListVO> pageData(StockLogisticsWatchQTO.QTO qto) {
        QueryWrapper<StockLogisticsWatch> wrapper = new QueryWrapper<>();
        IPage<StockLogisticsWatch> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, StockLogisticsWatchVO.ListVO.class, page);
    }

    @Override
    public void addStockLogisticsWatch(StockLogisticsWatchDTO.ETO eto) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        StockLogisticsWatch oneItem = repository.getOne(queryWrapper);
        if(null != oneItem){
            if(!oneItem.getId().equals(eto.getId())){
                throw new BusinessException("不能添加多条数据");
            }
        }
        StockLogisticsWatch stockLogisticsWatch = new StockLogisticsWatch();
        BeanUtils.copyProperties(eto, stockLogisticsWatch);
        repository.saveOrUpdate(stockLogisticsWatch);
    }

    @Override
    public PageData<StockLogisticsWatchVO.ListVO> listWatch(StockLogisticsWatchQTO.QTO qto) {
        QueryWrapper<StockLogisticsWatch> wrapper = new QueryWrapper<>();
        IPage<StockLogisticsWatch> pager = MybatisPlusUtil.pager(qto);
        repository.page(pager,wrapper);
        return MybatisPlusUtil.toPageData(qto, StockLogisticsWatchVO.ListVO.class,pager);



    }


}
