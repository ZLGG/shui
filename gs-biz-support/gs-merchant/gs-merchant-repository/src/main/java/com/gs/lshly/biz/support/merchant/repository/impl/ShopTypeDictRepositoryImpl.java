package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.ShopTypeDict;
import com.gs.lshly.biz.support.merchant.mapper.ShopTypeDictMapper;
import com.gs.lshly.biz.support.merchant.repository.IShopTypeDictRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺类型 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-14
 */
@Service
public class ShopTypeDictRepositoryImpl extends ServiceImpl<ShopTypeDictMapper, ShopTypeDict> implements IShopTypeDictRepository {

    @Override
    public boolean checkIdExist(String id) {
        ShopTypeDict entity =  this.getById(id);
        if(ObjectUtils.isNull(entity)){
            return false;
        }
        return true;
    }
}
