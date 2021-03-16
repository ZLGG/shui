package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantPermissionDict;
import com.gs.lshly.biz.support.merchant.repository.IMerchantPermissionDictRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantPermissionDictService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantPermissionDictDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantPermissionDictQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-26
*/
@Component
public class PCMerchMerchantPermissionDictServiceImpl implements IPCMerchMerchantPermissionDictService {

    @Autowired
    private IMerchantPermissionDictRepository repository;

    @Override
    public List<PCMerchMerchantPermissionDictVO.ListVO> list(PCMerchMerchantPermissionDictQTO.QTO qto) {

        return repository.selectPermissionDictForGroup();
    }

    @Override
    public void addMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto) {
        MerchantPermissionDict merchantPermissionDict = new MerchantPermissionDict();
        BeanCopyUtils.copyProperties(eto, merchantPermissionDict);
        repository.save(merchantPermissionDict);
    }

    @Override
    public void deleteMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editMerchantPermissionDict(PCMerchMerchantPermissionDictDTO.ETO eto) {
        MerchantPermissionDict merchantPermissionDict = new MerchantPermissionDict();
        BeanCopyUtils.copyProperties(eto, merchantPermissionDict);
        repository.updateById(merchantPermissionDict);
    }


}
