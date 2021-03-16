package com.gs.lshly.biz.support.stock.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockLogisticsCompanyCode;
import com.gs.lshly.biz.support.stock.mapper.StockLogisticsCompanyCodeMapper;
import com.gs.lshly.biz.support.stock.repository.IStockLogisticsCompanyCodeRepository;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockLogisticsCompanyCodeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockLogisticsCompanyCodeDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockLogisticsCompanyCodeQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCompanyCodeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author zzg
* @since 2020-10-30
*/
@Component
public class StockLogisticsCompanyCodeServiceImpl implements IStockLogisticsCompanyCodeService {

    @Autowired
    private IStockLogisticsCompanyCodeRepository repository;

    @Autowired
    private StockLogisticsCompanyCodeMapper mapper;


    @Override
    public PageData<StockLogisticsCompanyCodeVO.ListVO> pageData(StockLogisticsCompanyCodeQTO.QTO qto) {
        QueryWrapper<StockLogisticsCompanyCode> wrapper = new QueryWrapper<>();
        IPage<StockLogisticsCompanyCode> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, StockLogisticsCompanyCodeVO.ListVO.class, page);
    }

    @Override
    public void addStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto) {
        StockLogisticsCompanyCode stockLogisticsCompanyCode = new StockLogisticsCompanyCode();
        BeanUtils.copyProperties(eto, stockLogisticsCompanyCode);
        repository.save(stockLogisticsCompanyCode);
    }


    @Override
    public void deleteStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.ETO eto) {
        StockLogisticsCompanyCode stockLogisticsCompanyCode = new StockLogisticsCompanyCode();
        BeanUtils.copyProperties(eto, stockLogisticsCompanyCode);
        repository.updateById(stockLogisticsCompanyCode);
    }

    @Override
    public StockLogisticsCompanyCodeVO.DetailVO detailStockLogisticsCompanyCode(StockLogisticsCompanyCodeDTO.IdDTO dto) {
        StockLogisticsCompanyCode stockLogisticsCompanyCode = repository.getById(dto.getId());
        StockLogisticsCompanyCodeVO.DetailVO detailVo = new StockLogisticsCompanyCodeVO.DetailVO();
        if(ObjectUtils.isEmpty(stockLogisticsCompanyCode)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(stockLogisticsCompanyCode, detailVo);
        return detailVo;
    }

    @Override
    public StockLogisticsCompanyCodeVO.ListCodeCodeVO provideLogisticsCode() {
        List<StockLogisticsCompanyCode> listCodes = mapper.provideLogisticsCode();

        StockLogisticsCompanyCodeVO.ListCodeCodeVO listCodeCodeVO = new StockLogisticsCompanyCodeVO.ListCodeCodeVO();//大

        StockLogisticsCompanyCodeVO.ListVO listVO = new StockLogisticsCompanyCodeVO.ListVO();//小

        listCodeCodeVO.setAllCode(new ArrayList<>());
        for (StockLogisticsCompanyCode  item : listCodes ) {
            StockLogisticsCompanyCodeVO.ListVO listVO1 = new StockLogisticsCompanyCodeVO.ListVO();
            listVO1.setId(item.getId());
            listVO1.setCode(item.getCode());
            listVO1.setLogisticsCompanyAme(item.getLogisticsCompanyAme());
            List<StockLogisticsCompanyCodeVO.ListVO> allCode = listCodeCodeVO.getAllCode();
            allCode.add(listVO1);
        }
        List<String> objects = new ArrayList<>();
        for (StockLogisticsCompanyCodeVO.ListVO item: listCodeCodeVO.getAllCode()) {
            item.getId();
            objects.add(item.getId());
        }
        System.out.println(objects);
        return listCodeCodeVO;
    }

}
