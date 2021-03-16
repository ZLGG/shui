package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.SiteNavigation;
import com.gs.lshly.biz.support.foundation.repository.ISiteNavigationRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteNavigationService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNavigationQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNavigationVO;
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
public class SiteNavigationServiceImpl implements ISiteNavigationService {

    @Autowired
    private ISiteNavigationRepository repository;

    @Override
    public List<SiteNavigationVO.H5ListVO> h5List(SiteNavigationQTO.H5QTO qto) {
        QueryWrapper<SiteNavigation> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("subject",SubjectEnum.默认.getCode());
        queryWrapper.eq("type",qto.getType());
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.eq("pc_show", PcH5Enum.NO.getCode());
        queryWrapper.orderByAsc("idx");
        List<SiteNavigation> siteNavigationList = repository.list(queryWrapper);
        return ListUtil.listCover(SiteNavigationVO.H5ListVO.class,siteNavigationList);
    }


    @Override
    public List<SiteNavigationVO.PCListVO> pcList(SiteNavigationQTO.PCQTO qto) {
        QueryWrapper<SiteNavigation> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        queryWrapper.eq("subject",SubjectEnum.默认.getCode());
        queryWrapper.eq("type",qto.getType());
        List<SiteNavigation> siteNavigationList = repository.list(queryWrapper);
        return ListUtil.listCover(SiteNavigationVO.PCListVO.class,siteNavigationList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void h5Editor(SiteNavigationDTO.H5DTO eto) {
        if(ObjectUtils.isNotEmpty(eto.getNavigationList())){
            List<SiteNavigation> siteNavigationList =new ArrayList<>();
            eto.getNavigationList().forEach(item ->{
                if(null == item.getIdx()){
                    item.setIdx(0);
                }
                SiteNavigation siteNavigation = new SiteNavigation();
                BeanUtils.copyProperties(item, siteNavigation);
                siteNavigation.setPcShow(PcH5Enum.NO.getCode());
                siteNavigation.setSubject(SubjectEnum.默认.getCode());
                if (TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteNavigation.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
                        for(String id:eto.getRemoveIdList()){
                            if(StringUtils.isNotBlank(id)){
                                if(id.equals(item.getId())){
                                    throw new BusinessException("保存的数据和删除的数据冲突");
                                }
                            }
                        }
                    }
                }
                siteNavigationList.add(siteNavigation);
            });
            repository.saveOrUpdateBatch(siteNavigationList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
            repository.removeByIds(eto.getRemoveIdList());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcEditor(SiteNavigationDTO.PCDTO eto) {
        if(ObjectUtils.isNotEmpty(eto.getNavigationList())){
            List<SiteNavigation> siteNavigationList =new ArrayList<>();
            eto.getNavigationList().forEach(item ->{
                if(null == item.getIdx()){
                    item.setIdx(0);
                }
                SiteNavigation siteNavigation = new SiteNavigation();
                BeanUtils.copyProperties(item, siteNavigation);
                siteNavigation.setPcShow(PcH5Enum.YES.getCode());
                siteNavigation.setSubject(SubjectEnum.默认.getCode());
                if (TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteNavigation.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
                        for(String id:eto.getRemoveIdList()){
                            if(StringUtils.isNotBlank(id)){
                                if(id.equals(item.getId())){
                                    throw new BusinessException("保存的数据和删除的数据冲突");
                                }
                            }
                        }
                    }
                }
                siteNavigationList.add(siteNavigation);
            });
            repository.saveOrUpdateBatch(siteNavigationList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
            repository.removeByIds(eto.getRemoveIdList());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcEditor2(SiteNavigationDTO.PC2DTO dto) {
        QueryWrapper<SiteNavigation> removeWrapper = MybatisPlusUtil.query();
        removeWrapper.eq("terminal",dto.getTerminal());
        removeWrapper.eq("pc_show",PcH5Enum.YES.getCode());
        removeWrapper.eq("subject",SubjectEnum.默认.getCode());
        removeWrapper.eq("type",dto.getType());
        repository.remove(removeWrapper);
        if (ObjectUtils.isNotEmpty(dto.getNavigationList())) {
            List<SiteNavigation> siteNavigationList = new ArrayList<>();
            for(SiteNavigationDTO.PC2Item item:dto.getNavigationList()){
                if (null == item.getIdx()) {
                    item.setIdx(0);
                }
                SiteNavigation siteNavigation = new SiteNavigation();
                BeanUtils.copyProperties(item, siteNavigation);
                siteNavigation.setId(null);
                siteNavigation.setTerminal(dto.getTerminal());
                siteNavigation.setPcShow(PcH5Enum.YES.getCode());
                siteNavigation.setSubject(SubjectEnum.默认.getCode());
                siteNavigation.setType(dto.getType());
                siteNavigationList.add(siteNavigation);
            }
            repository.saveBatch(siteNavigationList);
        }
    }


}
