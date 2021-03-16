package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantApplyCategory;
import com.gs.lshly.biz.support.merchant.mapper.MerchantApplyCategoryMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantApplyCategoryView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyCategoryRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家店铺商品类目申请 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
@Service
public class MerchantApplyCategoryRepositoryImpl extends ServiceImpl<MerchantApplyCategoryMapper, MerchantApplyCategory> implements IMerchantApplyCategoryRepository {

    @Override
    public boolean checkIdExist(String id) {
        MerchantApplyCategory entity =  this.getById(id);
        if(ObjectUtils.isNull(entity)){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIdListExist(List<String> idList) {
        List<MerchantApplyCategory> dataList = this.listByIds(idList);
        if(ObjectUtils.isEmpty(dataList)){
            return false;
        }
        if(dataList.size() != idList.size()){
            return false;
        }
        return true;
    }
}
