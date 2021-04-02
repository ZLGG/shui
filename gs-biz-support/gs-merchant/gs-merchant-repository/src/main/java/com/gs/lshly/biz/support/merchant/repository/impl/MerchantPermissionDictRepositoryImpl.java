package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantPermissionDict;
import com.gs.lshly.biz.support.merchant.mapper.MerchantPermissionDictMapper;
import com.gs.lshly.biz.support.merchant.repository.IMerchantPermissionDictRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家帐号权限字典 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Service
public class MerchantPermissionDictRepositoryImpl extends ServiceImpl<MerchantPermissionDictMapper, MerchantPermissionDict> implements IMerchantPermissionDictRepository {

    @Override
    public List<PCMerchMerchantPermissionDictVO.ListVO> selectPermissionDictForGroup() {
        QueryWrapper<MerchantPermissionDict> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        List<MerchantPermissionDict> dataList= this.list(wrapper);
        if(ObjectUtils.isEmpty(dataList)){
            return new ArrayList<>();
        }
        Map<String, PCMerchMerchantPermissionDictVO.ListVO> nameKeyMap = new HashMap<>();
        for(MerchantPermissionDict item:dataList){
            PCMerchMerchantPermissionDictVO.ListVO listVO = nameKeyMap.get(item.getGroupName());
            if(ObjectUtils.isNull(listVO)){
                listVO = new PCMerchMerchantPermissionDictVO.ListVO();
                listVO.setGroupName(item.getGroupName());
                listVO.setPermissionList(new ArrayList<>());
                nameKeyMap.put(listVO.getGroupName(),listVO);
                PCMerchMerchantPermissionDictVO.ItemVO  itemVO = new PCMerchMerchantPermissionDictVO.ItemVO();
                itemVO.setId(item.getId());
                itemVO.setPermissionName(item.getPermissionName());
                listVO.getPermissionList().add(itemVO);
            }
        }
        return new ArrayList<>(nameKeyMap.values());
    }
}
