package com.gs.lshly.biz.support.stock.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.StockAddress;
import com.gs.lshly.biz.support.stock.entity.StockAddressChild;
import com.gs.lshly.biz.support.stock.enums.StockAddressOwnerTypeEnum;
import com.gs.lshly.biz.support.stock.mapper.StockAddressChildMapper;
import com.gs.lshly.biz.support.stock.mapper.StockAddressMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockAddressView;
import com.gs.lshly.biz.support.stock.mapper.view.StockDefaultAddressView;
import com.gs.lshly.biz.support.stock.repository.IStockAddressChildRepository;
import com.gs.lshly.biz.support.stock.repository.IStockAddressRepository;
import com.gs.lshly.biz.support.stock.service.bbb.h5.IBbbH5StockAddressService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.qto.BbbH5StockAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockAddressVO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockAddressVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
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
public class BbbH5StockAddressServiceImpl implements IBbbH5StockAddressService {

    @Autowired
    private IStockAddressRepository repository;

    @Autowired
    private StockAddressMapper stockAddressMapper;

    @Autowired
    private IStockAddressChildRepository stockAddressChildRepository;

    @Autowired
    private StockAddressChildMapper stockAddressChildMapper;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BbbH5StockAddressVO.DetailVO saveStockAddress(BbbH5StockAddressDTO.DetailETO eto) {

        if (null == eto){
            throw new BusinessException("参数不能为空！！");
        }
        if (StringUtils.isBlank(eto.getJwtUserId())){
            throw new BusinessException("未登录！！！");
        }
        if (ObjectUtils.isEmpty(eto.getAddressType())){
            throw new BusinessException("请确定地址类型！！");
        }
        //保存地址
        StockAddress stockAddress = new StockAddress();
        BeanUtils.copyProperties(eto,stockAddress);
        //地址归属者
        stockAddress.setOwnerId(eto.getJwtUserId());
        //地址归属者类型
        stockAddress.setOwnerType(StockAddressOwnerTypeEnum.会员.getCode());

        repository.saveOrUpdate(stockAddress);

        StockAddressChild child = new StockAddressChild();
        if (StringUtils.isNotBlank(eto.getId())){
            QueryWrapper<StockAddressChild> wrapper = MybatisPlusUtil.query();
            wrapper.eq("address_type",eto.getAddressType());
            wrapper.eq("address_id",eto.getId());
            StockAddressChild child1 = stockAddressChildRepository.getOne(wrapper);
            if (ObjectUtils.isEmpty(child1)){
                throw new BusinessException("数据异常！！");
            }
            child.setId(child1.getId());
        }

        child.setAddressId(stockAddress.getId());
        child.setAddressType(eto.getAddressType());
        child.setState(TrueFalseEnum.是.getCode());
        //如果将新增的地址设为默认地址先判断该用户是否有设置默认地址，若有则先将原先的默认地址取消掉
        if (ObjectUtils.isNotEmpty(eto.getIsDefault()) && eto.getIsDefault().equals(TrueFalseEnum.是.getCode())){
            StockDefaultAddressView defaultAddressView = hasDefaultAddress(eto.getJwtUserId(),eto.getAddressType());
            if (ObjectUtils.isNotEmpty(defaultAddressView)){
                cancelDefaultAddress(defaultAddressView.getId());
            }
            child.setIsDefault(eto.getIsDefault());
        }else {
            //查询用户是否有新增了收货地址,若没有则将第一个新增地址设为默认地址
            Integer count = stockAddressChildMapper.countDefault(new QueryWrapper<StockAddressChild>()
                    .eq("addr.owner_id", eto.getJwtUserId())
                    .eq("child.address_type", eto.getAddressType())
                    .eq("child.state", TrueFalseEnum.是.getCode()));
            if(count!=null && count>0) {
                child.setIsDefault(TrueFalseEnum.否.getCode());
            }else {
                child.setIsDefault(TrueFalseEnum.是.getCode());
            }
        }
        stockAddressChildRepository.saveOrUpdate(child);

        BbbH5StockAddressVO.DetailVO detailVO = new BbbH5StockAddressVO.DetailVO();
        BeanUtils.copyProperties(stockAddress,detailVO);
        return detailVO;

    }

    @Override
    public void deleteAddress(BbbH5StockAddressDTO.IdDTO dto) {
        if (null == dto){
            throw new BusinessException("参数不能为空！！");
        }
        if (StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("未登录！！！");
        }
        if (ObjectUtils.isEmpty(dto.getId())){
            throw new BusinessException("地址ID不能为空！！！");
        }

        QueryWrapper<StockAddressChild> query = MybatisPlusUtil.query();
        query.eq("address_id",dto.getId());
        stockAddressChildRepository.remove(query);

        QueryWrapper<StockAddress> wrapper1 = MybatisPlusUtil.query();
        wrapper1.eq("id",dto.getId());
        repository.remove(wrapper1);
    }

    @Override
    public BbbH5StockAddressVO.DetailVO detailStockAddress(BbbH5StockAddressDTO.IdAndTypeDTO dto) {
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(dto.getId())){
            queryWrapper.eq("ad.id",dto.getId());
        }
            queryWrapper.eq("ad.owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        if (ObjectUtils.isNotEmpty(dto.getJwtUserId())){
            queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        }
        if (ObjectUtils.isNotEmpty(dto.getAddressType())){
            queryWrapper.eq("child.address_type",dto.getAddressType());
        }
        StockAddressView view =  stockAddressMapper.mapperOne(queryWrapper);
        if(null == view){
            throw  new BusinessException("查询数据异常！！");
        }
        BbbH5StockAddressVO.DetailVO detailVO = new BbbH5StockAddressVO.DetailVO();
        BeanUtils.copyProperties(view,detailVO);
        return detailVO;
    }

    @Override
    public PageData<BbbH5StockAddressVO.ListVO> pageAddressListVO(BbbH5StockAddressQTO.QTO qto) {
        if (ObjectUtils.isEmpty(qto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        if (ObjectUtils.isEmpty(qto.getAddressType())){
            throw new BusinessException("请确定查找的地址类型！！");
        }
        QueryWrapper<StockAddressView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gsa.owner_id",qto.getJwtUserId());
        wrapper.eq("gsac.state",TrueFalseEnum.是.getCode());
        wrapper.eq("gsac.address_type",qto.getAddressType());
        IPage<StockAddressView> page = MybatisPlusUtil.pager(qto);
        IPage<StockAddressView> addressViewIPage = stockAddressMapper.pageStockAddressView(wrapper,page);
        if (ObjectUtils.isEmpty(addressViewIPage) || ObjectUtils.isEmpty(addressViewIPage.getRecords())){
            return new PageData<>();
        }
        List<BbbH5StockAddressVO.ListVO> listVOS = ListUtil.listCover(BbbH5StockAddressVO.ListVO.class,addressViewIPage.getRecords());
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),addressViewIPage.getTotal());
    }

    @Override
    public BbbH5StockAddressVO.DetailVO innerGetDefault(BaseDTO dto, Integer addressType) {
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("ad.owner_type", StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("child.address_type",addressType );
        queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        List<StockAddressView> viewList = stockAddressMapper.mapperList(queryWrapper);
        if(ObjectUtils.isNotEmpty(viewList)){
            BbbH5StockAddressVO.DetailVO detailVO = null;
            StockAddressView tempDefault = null;
            for(StockAddressView viewItem:viewList){
                if(TrueFalseEnum.是.getCode().equals(viewItem.getIsDefault())){
                    detailVO = new BbbH5StockAddressVO.DetailVO();
                    BeanUtils.copyProperties(viewItem,detailVO);
                    break;
                }
                tempDefault = viewItem;
            }
            if(null != detailVO){
                return detailVO;
            }
            detailVO = new BbbH5StockAddressVO.DetailVO();
            BeanUtils.copyProperties(tempDefault,detailVO);
            return detailVO;
        }
        return null;
    }

    private StockDefaultAddressView hasDefaultAddress(String ownerId,Integer addressType){
        QueryWrapper<StockDefaultAddressView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("sa.owner_id",ownerId);
        wrapper.eq("sac.address_type",addressType);
        wrapper.eq("sac.is_default",TrueFalseEnum.是.getCode());
        StockDefaultAddressView defaultAddressView = stockAddressMapper.getStockDefaultAddressView(wrapper);
        return defaultAddressView;
    }

    private void cancelDefaultAddress(String addressChildId){
        StockAddressChild stockAddressChild = new StockDefaultAddressView();
        stockAddressChild.setIsDefault(TrueFalseEnum.否.getCode());
        stockAddressChild.setId(addressChildId);
        stockAddressChildRepository.updateById(stockAddressChild);
    }

}
