package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteBannerService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 陈奇
 * @since 2020-09-29
 */
@Component
public class SiteBannerServiceImpl implements ISiteBannerService {

    @Autowired
    private ISiteBannerRepository repository;

    @Override
    public List<SiteBannerVO.H5ListVO> h5List(SiteBannerQTO.H5QTO qto) {
        QueryWrapper<SiteBanner> QueryWrapper =  MybatisPlusUtil.query();
        QueryWrapper.eq("pc_show",PcH5Enum.NO.getCode());
        QueryWrapper.eq("terminal",qto.getTerminal());
        QueryWrapper.eq("subject",qto.getSubject());
        QueryWrapper.orderByAsc("idx");
        List<SiteBanner> siteBannerList = repository.list(QueryWrapper);
        return ListUtil.listCover(SiteBannerVO.H5ListVO.class,siteBannerList);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void h5Editor(SiteBannerDTO.H5DTO dto) {
        if(ObjectUtils.isNotEmpty(dto.getBannerList())){
            List<SiteBanner> siteBannerList = new ArrayList<>();
            dto.getBannerList().forEach(item -> {
                SiteBanner siteBanner = new SiteBanner();
                BeanUtils.copyProperties(item, siteBanner);
                if(null == siteBanner.getIdx()){
                    item.setIdx(0);
                }
                siteBanner.setPcShow(PcH5Enum.NO.getCode());
                if(TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteBanner.setId(null);
                }else{
                   if(ObjectUtils.isNotEmpty(dto.getRemoveIdList())){
                       if(dto.getRemoveIdList().contains(siteBanner.getId())){
                           throw new BusinessException("保存的数据和删除的数据冲突");
                       }
                   }
                }
                siteBannerList.add(siteBanner);
            });
            repository.saveOrUpdateBatch(siteBannerList);
        }
        if(ObjectUtils.isNotEmpty(dto.getRemoveIdList())){
            repository.removeByIds(dto.getRemoveIdList());
        }
    }

    @Override
    public List<SiteBannerVO.PCListVO> pcList(SiteBannerQTO.PCQTO qto) {
        QueryWrapper<SiteBanner> QueryWrapper =  MybatisPlusUtil.query();
        QueryWrapper.eq("pc_show",PcH5Enum.YES.getCode());
        QueryWrapper.eq("terminal",qto.getTerminal());
        QueryWrapper.eq("subject",qto.getSubject());
        QueryWrapper.orderByAsc("idx");
        List<SiteBanner> siteBannerList = repository.list(QueryWrapper);
        return ListUtil.listCover(SiteBannerVO.PCListVO.class,siteBannerList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcEditor(SiteBannerDTO.PCDTO dto) {
        if (ObjectUtils.isNotEmpty(dto.getBannerList())) {
            List<SiteBanner> siteBannerList = new ArrayList<>();
            dto.getBannerList().forEach(item -> {
                SiteBanner siteBanner = new SiteBanner();
                BeanUtils.copyProperties(item, siteBanner);
                if (null == siteBanner.getIdx()) {
                    item.setIdx(0);
                }
                siteBanner.setPcShow(PcH5Enum.YES.getCode());
                if (TrueFalseEnum.是.getCode().equals(item.getIsNew())) {
                    siteBanner.setId(null);
                    siteBanner.setIsClassify(TrueFalseEnum.否.getCode());
                } else {
                    if (ObjectUtils.isNotEmpty(dto.getRemoveIdList())) {
                        if (dto.getRemoveIdList().contains(siteBanner.getId())) {
                            throw new BusinessException("保存的数据和删除的数据冲突");
                        }
                    }
                }
                siteBannerList.add(siteBanner);
            });
            repository.saveOrUpdateBatch(siteBannerList);
        }
        if (ObjectUtils.isNotEmpty(dto.getRemoveIdList())) {
            repository.removeByIds(dto.getRemoveIdList());
        }
    }
}
