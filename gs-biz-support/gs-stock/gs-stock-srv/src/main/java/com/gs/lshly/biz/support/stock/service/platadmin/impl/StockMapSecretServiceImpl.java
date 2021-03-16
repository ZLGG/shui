package com.gs.lshly.biz.support.stock.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockMapSecret;
import com.gs.lshly.biz.support.stock.repository.IStockMapSecretRepository;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockMapSecretService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockMapSecretDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockMapSecretVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-15
*/
@Component
public class StockMapSecretServiceImpl implements IStockMapSecretService {

    @Autowired
    private IStockMapSecretRepository repository;

    @Override
    public PageData<StockMapSecretVO.ListVO> pageData(BaseQTO qto) {
        QueryWrapper<StockMapSecret> wrapper = new QueryWrapper<>();
        IPage<StockMapSecret> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, StockMapSecretVO.ListVO.class, page);
    }

    @Override
    public void addStockMapSecret(StockMapSecretDTO.ETO eto) {
        StockMapSecret stockMapSecret = new StockMapSecret();
        if(ObjectUtils.isEmpty(eto.getId())){
            QueryWrapper<StockMapSecret> wrapper = new QueryWrapper<>();
            int i = repository.count(wrapper.eq("map_secret",eto.getMapSecret()));
            if( i != 0 ){
                throw new BusinessException("该密钥已存在,请勿重复添加");
            }
            BeanUtils.copyProperties(eto, stockMapSecret);
            repository.save(stockMapSecret);
        }
        BeanUtils.copyProperties(eto, stockMapSecret);
        repository.updateById(stockMapSecret);
    }


    @Override
    public void deleteStockMapSecret(StockMapSecretDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editStockMapSecret(StockMapSecretDTO.ETO eto) {
        StockMapSecret stockMapSecret = new StockMapSecret();
        BeanUtils.copyProperties(eto, stockMapSecret);
        repository.updateById(stockMapSecret);
    }

    @Override
    public StockMapSecretVO.DetailVO detailStockMapSecret(StockMapSecretDTO.IdDTO dto) {
        StockMapSecret stockMapSecret = repository.getById(dto.getId());
        StockMapSecretVO.DetailVO detailVo = new StockMapSecretVO.DetailVO();
        if(ObjectUtils.isEmpty(stockMapSecret)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(stockMapSecret, detailVo);
        return detailVo;
    }

}
