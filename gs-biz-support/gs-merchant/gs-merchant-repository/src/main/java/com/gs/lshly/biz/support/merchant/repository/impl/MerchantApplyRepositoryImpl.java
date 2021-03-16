package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantApply;
import com.gs.lshly.biz.support.merchant.mapper.MerchantApplyMapper;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家入驻申请 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-14
 */
@Service
public class MerchantApplyRepositoryImpl extends ServiceImpl<MerchantApplyMapper, MerchantApply> implements IMerchantApplyRepository {

    @Override
    public boolean checkIdExist(String id) {
        MerchantApply entity =  this.getById(id);
        if(ObjectUtils.isNull(entity)){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIdListExist(List<String> idList) {
        List<MerchantApply> dataList = this.listByIds(idList);
        if(ObjectUtils.isEmpty(dataList)){
            return false;
        }
        if(dataList.size() != idList.size()){
            return false;
        }
        return true;
    }
}
