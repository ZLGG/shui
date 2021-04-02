package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SysSm;
import com.gs.lshly.biz.support.foundation.entity.SysSmTemplate;
import com.gs.lshly.biz.support.foundation.repository.ISysSmRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysSmTemplateRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISysSmService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmTemplateDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmTemplateVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2021-03-20
*/
@Component
public class SysSmServiceImpl implements ISysSmService {

    @Autowired
    private ISysSmRepository repository;
    @Autowired
    private ISysSmTemplateRepository sysSmTemplateRepository;

    @Override
    public List<SysSmVO.ListVO> listSysSM(BaseDTO dto) {
        QueryWrapper<SysSm> wrapper = MybatisPlusUtil.query();
        List<SysSmVO.ListVO> listVOS = new ArrayList<>();
        List<SysSm> sysSms = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(sysSms)){
            listVOS = ListUtil.listCover(SysSmVO.ListVO.class,sysSms);
        }
        return listVOS;
    }

    @Override
    public void saveSysSm(SysSmDTO.ETO eto) {
        if (null == eto || StringUtils.isBlank(eto.getKey()) || StringUtils.isBlank(eto.getSecret()) || StringUtils.isBlank(eto.getSignName())){
            throw new BusinessException("传参错误，异常！");
        }
        SysSm sysSm = new SysSm();
        BeanUtils.copyProperties(eto, sysSm);
        repository.saveOrUpdate(sysSm);

    }


    @Override
    public void deleteSysSm(SysSmDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("传参错误，异常！");
        }
        repository.removeById(dto.getId());
    }

    @Override
    public void saveSysSmTemplate(SysSmTemplateDTO.ETO eto) {
        if (null == eto || StringUtils.isBlank(eto.getSmId()) || StringUtils.isBlank(eto.getTemplateCode()) ){
            throw new BusinessException("传参错误，异常！");
        }
        SysSmTemplate sysSmTemplate = new SysSmTemplate();
        BeanUtils.copyProperties(eto, sysSmTemplate);
        sysSmTemplateRepository.saveOrUpdate(sysSmTemplate);

    }

    @Override
    public void deleteSysSmTemplate(SysSmTemplateDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("传参错误，异常！");
        }
        sysSmTemplateRepository.removeById(dto.getId());
    }

    @Override
    public PageData<SysSmTemplateVO.DetailVO> templatePage(BaseQTO qto) {
        QueryWrapper<SysSmTemplate> wrapper = MybatisPlusUtil.query();
        IPage<SysSmTemplate> page = MybatisPlusUtil.pager(qto);
        IPage<SysSmTemplate> templateIPage = sysSmTemplateRepository.page(page,wrapper);
        if (ObjectUtils.isEmpty(templateIPage) || ObjectUtils.isEmpty(templateIPage.getRecords())){
            return new PageData<>();
        }
        List<SysSmTemplateVO.DetailVO> detailVOS = templateIPage.getRecords().parallelStream().map(e ->{
            SysSmTemplateVO.DetailVO detailVO = new SysSmTemplateVO.DetailVO();
            BeanUtils.copyProperties(e,detailVO);
            SysSm sysSm = repository.getById(e.getSmId());
            if (null != sysSm){
                detailVO.setSignName(sysSm.getSignName());
            }
            return detailVO;
        }).collect(Collectors.toList());
        return new PageData<>(detailVOS,qto.getPageNum(),qto.getPageSize(),templateIPage.getTotal());
    }

}
