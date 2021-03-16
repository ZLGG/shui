package com.gs.lshly.biz.support.stock.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsCompanyCode;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsCorp;
import com.gs.lshly.biz.support.stock.repository.IStockLogisticsCompanyCodeRepository;
import com.gs.lshly.biz.support.stock.repository.IStockLogisticsCorpRepository;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockLogisticsCorpService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCorpDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCorpQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCorpVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author zzg
* @since 2020-10-23
*/
@Component
public class StockLogisticsCorpServiceImpl implements IStockLogisticsCorpService {

    @Autowired
    private IStockLogisticsCorpRepository repository;
    @Autowired
    private IStockLogisticsCompanyCodeRepository iStockLogisticsCompanyCodeRepository;
    @Override
    public PageData<StockLogisticsCorpVO.ListVO> pageData(StockLogisticsCorpQTO.QTO qto) {
       QueryWrapper<StockLogisticsCorp> wrapper = new QueryWrapper<>();
        IPage<StockLogisticsCorp> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, StockLogisticsCorpVO.ListVO.class, page);
    }



    @Override
    public void addStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto) {
        StockLogisticsCorp stockLogisticsCorp = new StockLogisticsCorp();
        BeanUtils.copyProperties(eto, stockLogisticsCorp);
        repository.save(stockLogisticsCorp);
    }


    @Override
    public void deleteStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editStockLogisticsCorp(StockLogisticsCorpDTO.ETO eto) {
        StockLogisticsCorp stockLogisticsCorp = new StockLogisticsCorp();
        BeanUtils.copyProperties(eto, stockLogisticsCorp);
        repository.updateById(stockLogisticsCorp);
    }

    @Override
    public StockLogisticsCorpVO.DetailVO detailStockLogisticsCorp(StockLogisticsCorpDTO.IdDTO dto) {
        StockLogisticsCorp stockLogisticsCorp = repository.getById(dto.getId());
        StockLogisticsCorpVO.DetailVO detailVo = new StockLogisticsCorpVO.DetailVO();
        if(ObjectUtils.isEmpty(stockLogisticsCorp)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(stockLogisticsCorp, detailVo);
        return detailVo;
    }

    @Override
    public void initializeLogisticsCompany(StockLogisticsCorpDTO.ETO dto) {

        if(ObjectUtils.isEmpty(dto)){
            throw new BootstrapMethodError("数据错误");
        }
        //初始化数据
        packstockLogisticsCorpDate(iStockLogisticsCompanyCodeRepository.list(MybatisPlusUtil.query()));
    }

    private void packstockLogisticsCorpDate(List<StockLogisticsCompanyCode> logisticsCompanyCodeList){
        StockLogisticsCorp stockLogisticsCorp = new StockLogisticsCorp();
        for (StockLogisticsCompanyCode stockLogisticsCompanyCode:logisticsCompanyCodeList) {
            repository.remove(new QueryWrapper<StockLogisticsCorp>().eq("code", stockLogisticsCompanyCode.getCode()));
            stockLogisticsCorp.setCode(stockLogisticsCompanyCode.getCode());
            stockLogisticsCorp.setName(stockLogisticsCompanyCode.getLogisticsCompanyAme());
            stockLogisticsCorp.setCode(stockLogisticsCompanyCode.getCode());
            stockLogisticsCorp.setWww(stockLogisticsCompanyCode.getWebsite());
            stockLogisticsCorp.setAllname(stockLogisticsCompanyCode.getCohr());
            stockLogisticsCorp.setFlag(false);
            BeanCopyUtils.copyProperties(stockLogisticsCompanyCode, stockLogisticsCorp);
            repository.save(stockLogisticsCorp);
        }
    }



}
