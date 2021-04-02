package com.gs.lshly.biz.support.stock.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.StockAddress;
import com.gs.lshly.biz.support.stock.entity.StockAddressChild;
import com.gs.lshly.biz.support.stock.enums.StockAddressOwnerTypeEnum;
import com.gs.lshly.biz.support.stock.mapper.StockAddressMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockAddressView;
import com.gs.lshly.biz.support.stock.mapper.view.StockDefaultAddressView;
import com.gs.lshly.biz.support.stock.repository.IStockAddressChildRepository;
import com.gs.lshly.biz.support.stock.repository.IStockAddressRepository;
import com.gs.lshly.biz.support.stock.service.bbb.pc.IBbbPcStockAddressService;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockTemplateService;
import com.gs.lshly.common.enums.StockAddressTypeEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.qto.BbbStockAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockAddressVO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockAddressDTO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author zzg
* @since 2020-11-02
*/
@Component
public class BbbPcStockAddressServiceImpl implements IBbbPcStockAddressService {
    @Autowired
    private StockAddressMapper stockAddressMapper;
    @Autowired
    private ICommonStockTemplateService commonStockTemplateService;
    @Autowired
    private IStockAddressRepository repository;
    @Autowired
    private IStockAddressChildRepository addressChildRepository;


    @Override
    public String queryStockAddress(String id) {
        return commonStockTemplateService.queryStockAddress(id);
    }

    @Override
    public BbbStockAddressVO.DetailVO getDefault(BaseDTO dto, Integer addressType) {
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("ad.owner_type", StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("child.address_type",addressType );
        queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        queryWrapper.orderByDesc("ad.cdate");
        List<StockAddressView> viewList = stockAddressMapper.mapperList(queryWrapper);
        if(ObjectUtils.isNotEmpty(viewList)){
            BbbStockAddressVO.DetailVO detailVO = null;
            StockAddressView tempDefault = null;
            for(StockAddressView viewItem:viewList){
                if(TrueFalseEnum.是.getCode().equals(viewItem.getIsDefault())){
                    detailVO = new BbbStockAddressVO.DetailVO();
                    BeanUtils.copyProperties(viewItem,detailVO);
                    break;
                }
                tempDefault = viewItem;
            }
            if(null != detailVO){
                return detailVO;
            }
            detailVO = new BbbStockAddressVO.DetailVO();
            BeanUtils.copyProperties(tempDefault,detailVO);
            return detailVO;
        }
        return null;
    }

    @Override
    public BbbStockAddressVO.ListVO detailStockAddress(BbbStockAddressDTO.EditDTO dto) {
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("ad.id",dto.getAddressId());
        queryWrapper.eq("ad.owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        queryWrapper.eq("child.address_type",dto.getAddressType());
        StockAddressView view =  stockAddressMapper.mapperOne(queryWrapper);
        if(null == view){
           throw  new BusinessException("查询数据异常！！");
        }
        BbbStockAddressVO.DetailVO detailVO = new BbbStockAddressVO.DetailVO();
        BeanUtils.copyProperties(view,detailVO);
        return detailVO;
    }

    @Override
    public void saveStockAddress(BbbStockAddressDTO.DetailETO eto) {
        if (ObjectUtils.isEmpty(eto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        //添加默认收获地址
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
            StockAddressChild child1 = addressChildRepository.getOne(wrapper);
            if (ObjectUtils.isEmpty(child1)){
                throw new BusinessException("数据异常！！");
            }
            child.setId(child1.getId());
        }

        child.setAddressId(stockAddress.getId());
        child.setAddressType(eto.getAddressType());
        child.setState(TrueFalseEnum.是.getCode());
        child.setIsDefault(ObjectUtils.isEmpty(eto.getIsDefault())?TrueFalseEnum.否.getCode():eto.getIsDefault());
        //如果将新增的地址设为默认地址先判断该用户是否有设置默认地址，若有则先将原先的默认地址取消掉
        if (ObjectUtils.isNotEmpty(eto.getIsDefault()) && eto.getIsDefault().equals(TrueFalseEnum.是.getCode())){
            StockDefaultAddressView defaultAddressView = hasDefaultAddress(eto.getJwtUserId(),eto.getAddressType());
            if (ObjectUtils.isNotEmpty(defaultAddressView)){
                cancelDefaultAddress(defaultAddressView.getId());
            }
        }
        addressChildRepository.saveOrUpdate(child);
    }

    @Override
    public PageData<BbbStockAddressVO.ListVO> pageAddressListVO(BbbStockAddressQTO.QTO qto) {
        if (ObjectUtils.isEmpty(qto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<StockAddressView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gsa.owner_id",qto.getJwtUserId());
        wrapper.eq("gsac.state",TrueFalseEnum.是.getCode());
        wrapper.eq("gsac.address_type", StockAddressTypeEnum.收货.getCode());
        IPage<StockAddressView> page = MybatisPlusUtil.pager(qto);
        IPage<StockAddressView> addressViewIPage = stockAddressMapper.pageStockAddressView(wrapper,page);
        if (ObjectUtils.isEmpty(addressViewIPage) || ObjectUtils.isEmpty(addressViewIPage.getRecords())){
            return new PageData<>();
        }
        List<BbbStockAddressVO.ListVO> listVOS = ListUtil.listCover(BbbStockAddressVO.ListVO.class,addressViewIPage.getRecords());
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),addressViewIPage.getTotal());
    }

    @Override
    public void setDefaultStockAddress(BbbStockAddressDTO.SetDefaultDTO dto) {
        if (ObjectUtils.isEmpty(dto) || StringUtils.isBlank(dto.getAddressId())){
            throw new BusinessException("参数为空，异常！！");
        }
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        //先判断该用户是否有设置默认地址，若有则先将原先的默认地址取消掉
        StockDefaultAddressView defaultAddressView = hasDefaultAddress(dto.getJwtUserId(),dto.getAddressType());
        if (ObjectUtils.isNotEmpty(defaultAddressView)){
            cancelDefaultAddress(defaultAddressView.getId());
        }

        //将此时的地址改为默认地址
        QueryWrapper<StockAddressChild> wrapper = MybatisPlusUtil.query();
        wrapper.eq("address_id",dto.getAddressId());
        wrapper.eq("address_type",dto.getAddressType());
        wrapper.eq("state",TrueFalseEnum.是.getCode());
        StockAddressChild child = new StockAddressChild();
        child.setIsDefault(TrueFalseEnum.是.getCode());
        addressChildRepository.update(child,wrapper);
    }

    @Override
    public void deleteStockAddress(BbbStockAddressDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto) || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数异常，id为空！！");
        }
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<StockAddressChild> wrapper = MybatisPlusUtil.query();
        wrapper.eq("address_id",dto.getId());
        addressChildRepository.remove(wrapper);

        QueryWrapper<StockAddress> wrapper1 = MybatisPlusUtil.query();
        wrapper1.eq("id",dto.getId());
        repository.remove(wrapper1);

    }

    @Override
    public List<BbbStockAddressVO.ListVO> listStockAddressVO(BbbStockAddressDTO.AddressTypeDTO dto) {
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<StockAddressView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("ad.owner_id",dto.getJwtUserId());
        wrapper.eq("ad.owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        wrapper.eq("child.state",TrueFalseEnum.是.getCode());
        wrapper.eq("child.address_type", dto.getAddressType());
        List<StockAddressView> stockAddresses = stockAddressMapper.mapperList(wrapper);
        if (ObjectUtils.isEmpty(stockAddresses)){
            return new ArrayList<>();
        }
        List<BbbStockAddressVO.ListVO> listVOS = ListUtil.listCover(BbbStockAddressVO.ListVO.class,stockAddresses);
        return listVOS;
    }

    @Override
    public BbbStockAddressVO.ListVO innerDetailVO(BbbStockAddressDTO.IdDTO dto) {
        QueryWrapper<StockAddressView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("ad.id",dto.getId());
        queryWrapper.eq("ad.owner_type",StockAddressOwnerTypeEnum.会员.getCode());
        queryWrapper.eq("ad.owner_id",dto.getJwtUserId());
        StockAddressView view =  stockAddressMapper.mapperOne(queryWrapper);
        if(null == view){
            throw  new BusinessException("查询数据异常！！");
        }
        BbbStockAddressVO.DetailVO detailVO = new BbbStockAddressVO.DetailVO();
        BeanUtils.copyProperties(view,detailVO);
        return detailVO;
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
        addressChildRepository.updateById(stockAddressChild);
    }

}
