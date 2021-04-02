package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantPermissionDict;
import com.gs.lshly.biz.support.merchant.repository.IMerchantPermissionDictRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantPermissionDictService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantPermissionDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantPermissionDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantPermissionDictVO;
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
* @since 2020-10-08
*/
@Component
public class MerchantPermissionDictServiceImpl implements IMerchantPermissionDictService {

    @Autowired
    private IMerchantPermissionDictRepository repository;

    @Override
    public PageData<MerchantPermissionDictVO.ListVO> pageData(MerchantPermissionDictQTO.QTO qto) {
        QueryWrapper<MerchantPermissionDict> wq = new QueryWrapper<>();
        wq.orderByDesc("cdate");
        IPage<MerchantPermissionDict> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, MerchantPermissionDictVO.ListVO.class, page);
    }

    @Override
    public void addMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto) {
        MerchantPermissionDict merchantPermissionDict = new MerchantPermissionDict();
        BeanCopyUtils.copyProperties(eto, merchantPermissionDict);
        repository.save(merchantPermissionDict);
    }


    @Override
    public void deleteMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto) {
        MerchantPermissionDict merchantPermissionDict = new MerchantPermissionDict();
        BeanCopyUtils.copyProperties(eto, merchantPermissionDict);
        repository.updateById(merchantPermissionDict);
    }

    @Override
    public MerchantPermissionDictVO.DetailVO detailMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto) {
        MerchantPermissionDict merchantPermissionDict = repository.getById(dto.getId());
        MerchantPermissionDictVO.DetailVO detailVo = new MerchantPermissionDictVO.DetailVO();
        if(ObjectUtils.isEmpty(merchantPermissionDict)){
            throw new BusinessException("没有数据");
        }
        BeanCopyUtils.copyProperties(merchantPermissionDict, detailVo);
        return detailVo;
    }

}
