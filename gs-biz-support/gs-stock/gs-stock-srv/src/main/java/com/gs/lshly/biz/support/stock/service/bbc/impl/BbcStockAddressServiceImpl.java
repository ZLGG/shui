package com.gs.lshly.biz.support.stock.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.stock.entity.StockAddress;
import com.gs.lshly.biz.support.stock.entity.StockAddressChild;
import com.gs.lshly.biz.support.stock.enums.StockAddressOwnerTypeEnum;
import com.gs.lshly.biz.support.stock.mapper.StockAddressChildMapper;
import com.gs.lshly.biz.support.stock.mapper.StockAddressMapper;
import com.gs.lshly.biz.support.stock.mapper.StockProvinceAddressMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockAddressView;
import com.gs.lshly.biz.support.stock.repository.IStockAddressChildRepository;
import com.gs.lshly.biz.support.stock.repository.IStockAddressRepository;
import com.gs.lshly.biz.support.stock.service.bbc.IBbcStockAddressService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockAddressDTO;
import com.gs.lshly.common.struct.bbc.stock.qto.BbcStockAddressQTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockAddressVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author zzg
* @since 2020-11-02
*/
@Component
@Slf4j
public class BbcStockAddressServiceImpl implements IBbcStockAddressService {

    @Autowired
    private IStockAddressRepository repository;

    @Autowired
    private StockAddressMapper stockAddressMapper;

    @Autowired
    private IStockAddressChildRepository stockAddressChildRepository;

    @Autowired
    private StockAddressChildMapper stockAddressChildMapper;

    @Autowired
    private StockProvinceAddressMapper stockProvinceAddressMapper;

    @Override
    public List<BbcStockAddressVO.ListVO> list(BbcStockAddressQTO.QTO qto,Integer addressType) {
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("child.address_type",addressType);
        queryWrapper.eq("ad.owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("ad.owner_id",qto.getJwtUserId());
        queryWrapper.orderByAsc("ad.cdate");
        List<StockAddressView> viewList = stockAddressMapper.mapperList(queryWrapper);
        //没有默认 取最早设置的为默认地址
        if(ObjectUtils.isNotEmpty(viewList)){
            StockAddressView defaultView = null;
            int hasDefault = 0;
            for(int i=0;i<viewList.size();i++){
                if (StringUtils.isBlank(viewList.get(i).getStreet())){
                    viewList.get(i).setStreet("");
                }
                if (StringUtils.isBlank(viewList.get(i).getReals())){
                    viewList.get(i).setReals("");
                }
                StockAddressView view = viewList.get(i);
                if(i==0){
                    defaultView = view;
                }
                if(TrueFalseEnum.是.getCode().equals(view.getIsDefault())){
                    hasDefault = 1;
                }
            }
            if(TrueFalseEnum.否.getCode().equals(hasDefault )){
                defaultView.setIsDefault(TrueFalseEnum.是.getCode());
            }
        }
        return ListUtil.listCover(BbcStockAddressVO.ListVO.class,viewList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BbcStockAddressVO.DetailVO addStockAddress(BbcStockAddressDTO.ETO eto, Integer addressType) {
        StockAddress stockAddress = new StockAddress();
        BeanUtils.copyProperties(eto, stockAddress);
        stockAddress.setOwnerType(StockAddressOwnerTypeEnum.会员.getCode());
        stockAddress.setOwnerId(eto.getJwtUserId());
        repository.save(stockAddress);
        StockAddressChild child = new StockAddressChild();
        child.setAddressId(stockAddress.getId());
        child.setState(TrueFalseEnum.是.getCode());
        // 设置默认地址
        if (1 == eto.getIsDefault() ) {
            // 查询用户是否有默认收货地址
            String addressChildId = stockAddressChildMapper.getAddressIdIsDefault(new QueryWrapper<StockAddressChild>()
                    .eq("addr.owner_id", eto.getJwtUserId())
                    .eq("child.address_type", addressType)
                    .eq("child.state", true)
                    .eq("child.is_default", true)
            );
            if (org.apache.commons.lang3.StringUtils.isNotBlank(addressChildId)) {
                // 取消当前默认地址
                stockAddressMapper.cancelDefault(addressChildId);
            }
        }
        child.setIsDefault(eto.getIsDefault());
        child.setAddressType(addressType);
        stockAddressChildRepository.save(child);

        BbcStockAddressVO.DetailVO detailVO = new BbcStockAddressVO.DetailVO();
        BeanUtils.copyProperties(stockAddress,detailVO);
        detailVO.setIsDefault(eto.getIsDefault());
        return detailVO;

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editorStockAddress(BbcStockAddressDTO.ETO eto,Integer addressType) {

        // 修改地址信息
        QueryWrapper<StockAddress> queryWrapper = MybatisPlusUtil.query();
        StockAddress  stockAddress = new StockAddress();
        BeanUtils.copyProperties(eto,stockAddress);
        stockAddress.setOwnerType( StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("id",eto.getId());
        queryWrapper.eq("owner_id",eto.getJwtUserId());
        repository.update(stockAddress,queryWrapper);

        // 修改是否为默认地址
        if (1 == eto.getIsDefault() ) {
            // 查询用户是否有默认收货地址
            String addressChildId = stockAddressChildMapper.getAddressIdIsDefault(new QueryWrapper<StockAddressChild>()
                    .eq("addr.owner_id", eto.getJwtUserId())
                    .eq("child.address_type", addressType)
                    .eq("child.state", true)
                    .eq("child.is_default", true)
            );
            if (org.apache.commons.lang3.StringUtils.isNotBlank(addressChildId)) {
                // 取消当前默认地址
                stockAddressMapper.cancelDefault(addressChildId);
            }
        }
        UpdateWrapper<StockAddressChild> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_default",eto.getIsDefault());
        updateWrapper.set("address_type",addressType);
        updateWrapper.eq("address_id",eto.getId());
        stockAddressChildRepository.update(updateWrapper);
    }

    @Override
    public void deleteStockAddress(BbcStockAddressDTO.IdDTO dto) {
        QueryWrapper<StockAddress> deleteStockAddressWrapper = MybatisPlusUtil.query();
        deleteStockAddressWrapper.eq("id",dto.getId());
        deleteStockAddressWrapper.eq("owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        deleteStockAddressWrapper.eq("owner_id",dto.getJwtUserId());
        repository.remove(deleteStockAddressWrapper);
    }

    @Override
    public BbcStockAddressVO.DetailVO detailStockAddress(BbcStockAddressDTO.IdDTO dto) {
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("ad.id",dto.getId());
        queryWrapper.eq("ad.owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        StockAddressView view =  stockAddressMapper.mapperOne(queryWrapper);
        if(null == view){
            return null;
        }
        BbcStockAddressVO.DetailVO detailVO = new BbcStockAddressVO.DetailVO();
        BeanUtils.copyProperties(view,detailVO);
        return detailVO;
    }

    @Override
    public BbcStockAddressVO.DetailVO getDefault(BaseDTO dto,Integer addressType) {
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("ad.owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("child.address_type",addressType );
        queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        List<StockAddressView> viewList = stockAddressMapper.mapperList(queryWrapper);
        if(ObjectUtils.isNotEmpty(viewList)){
            BbcStockAddressVO.DetailVO detailVO = null;
            StockAddressView tempDefault = null;
            for(StockAddressView viewItem:viewList){
                if(TrueFalseEnum.是.getCode().equals(viewItem.getIsDefault())){
                    detailVO = new BbcStockAddressVO.DetailVO();
                    BeanUtils.copyProperties(viewItem,detailVO);
                    break;
                }
                tempDefault = viewItem;
            }
            if(null != detailVO){
                return detailVO;
            }
            detailVO = new BbcStockAddressVO.DetailVO();
            BeanUtils.copyProperties(tempDefault,detailVO);
            return detailVO;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setDefault(BbcStockAddressDTO.IdDTO dto,Integer addressType) {
        QueryWrapper<StockAddress> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("owner_id",dto.getJwtUserId());
        StockAddress stockAddress =  repository.getOne(queryWrapper);
        if(null == stockAddress){
            throw new BusinessException("收货地址不存在");
        }
        // 查询用户是否有默认收货地址
        String addressChildId = stockAddressChildMapper.getAddressIdIsDefault(new QueryWrapper<StockAddressChild>()
                .eq("addr.owner_id", dto.getJwtUserId())
                .eq("child.address_type", addressType)
                .eq("child.state", true)
                .eq("child.is_default", true)
        );
        if (org.apache.commons.lang3.StringUtils.isNotBlank(addressChildId)) {
            // 取消当前默认地址
            stockAddressMapper.cancelDefault(addressChildId);
        }
        // 更新当前地址为默认地址
        UpdateWrapper<StockAddressChild> updateWrapper  = MybatisPlusUtil.update();
        updateWrapper.set("is_default",TrueFalseEnum.是.getCode());
        updateWrapper.eq("address_type",addressType);
        updateWrapper.eq("address_id",dto.getId());
        stockAddressChildRepository.update(updateWrapper);

    }
}
