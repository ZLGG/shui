package com.gs.lshly.biz.support.stock.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockAddress;
import com.gs.lshly.biz.support.stock.enums.StockAddressOwnerTypeEnum;
import com.gs.lshly.biz.support.stock.mapper.StockAddressMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockAddressView;
import com.gs.lshly.biz.support.stock.repository.IStockAddressRepository;
import com.gs.lshly.biz.support.stock.service.merchadmin.h5.IH5MerchStockAddressService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.dto.H5MerchStockAddressDTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.qto.H5MerchStockAddressQTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.vo.H5MerchStockAddressVO;
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
* @since 2021-01-21
*/
@Component
public class H5MerchStockAddressServiceImpl implements IH5MerchStockAddressService {

    @Autowired
    private StockAddressMapper stockAddressMapper;
    @Autowired
    private IStockAddressRepository repository;

    @Override
    public PageData<H5MerchStockAddressVO.ListVO> pageData(H5MerchStockAddressQTO.QTO qto) {
        QueryWrapper<StockAddress> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<StockAddress> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, H5MerchStockAddressVO.ListVO.class, page);
    }

    @Override
    public void addStockAddress(H5MerchStockAddressDTO.ETO eto) {
        StockAddress stockAddress = new StockAddress();
        BeanUtils.copyProperties(eto, stockAddress);
        repository.save(stockAddress);
    }


    @Override
    public void deleteStockAddress(H5MerchStockAddressDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editStockAddress(H5MerchStockAddressDTO.ETO eto) {
        StockAddress stockAddress = new StockAddress();
        BeanUtils.copyProperties(eto, stockAddress);
        repository.updateById(stockAddress);
    }

    @Override
    public H5MerchStockAddressVO.ListVO detailStockAddress(BaseDTO dto) {
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("ad.owner_type", StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        StockAddressView view =  stockAddressMapper.mapperOne(queryWrapper);
        if(null == view){
            throw  new BusinessException("查询数据异常！！");
        }
        H5MerchStockAddressVO.ListVO detailVO = new H5MerchStockAddressVO.ListVO();
        BeanUtils.copyProperties(view,detailVO);
        return detailVO;
    }

}
