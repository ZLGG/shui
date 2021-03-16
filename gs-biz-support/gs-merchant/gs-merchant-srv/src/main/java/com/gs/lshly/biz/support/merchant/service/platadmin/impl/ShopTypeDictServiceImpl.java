package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopTypeDict;
import com.gs.lshly.biz.support.merchant.repository.IShopTypeDictRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IShopTypeDictService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopTypeDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopTypeDictVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-14
*/
@Component
public class ShopTypeDictServiceImpl implements IShopTypeDictService {

    @Autowired
    private IShopTypeDictRepository repository;

    @Override
    public PageData<ShopTypeDictVO.ListVO> pageData(ShopTypeDictQTO.QTO qto) {
        QueryWrapper<ShopTypeDict> wrapper = new QueryWrapper<>();
        IPage<ShopTypeDict> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, ShopTypeDictVO.ListVO.class, page);
    }

    @Override
    public void editShopTypeDict(ShopTypeDictDTO.ETO eto) {
        QueryWrapper<ShopTypeDict> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",eto.getId());
        ShopTypeDict shopTypeDict =  repository.getOne(queryWrapper);
        if(null == shopTypeDict){
           throw new BusinessException("店铺类型不存在");
        }
        BeanCopyUtils.copyProperties(eto, shopTypeDict);
        repository.updateById(shopTypeDict);
    }

    @Override
    public ShopTypeDictVO.DetailVO detailsShopTypeDict(ShopTypeDictDTO.ShopTypeDTO dto) {
        QueryWrapper<ShopTypeDict> shopTypeDictQueryWrapper = MybatisPlusUtil.query();
        shopTypeDictQueryWrapper.eq("type_code",dto.getShopType());
        ShopTypeDict shopTypeDict = repository.getOne(shopTypeDictQueryWrapper);
        if(null == shopTypeDict){
            return null;
        }
        ShopTypeDictVO.DetailVO detailVO = new ShopTypeDictVO.DetailVO();
        BeanUtils.copyProperties(shopTypeDict,detailVO);
        return detailVO;
    }


}
