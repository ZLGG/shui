package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.Site;
import com.gs.lshly.biz.support.foundation.repository.ISiteRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SitePCShowEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-11
*/
@Component
public class SiteServiceImpl implements ISiteService {

    @Autowired
    private ISiteRepository repository;

    @Override
    public PageData<SiteVO.ListVO> pageData(SiteQTO.QTO qto) {
        QueryWrapper<Site> wrapper =  MybatisPlusUtil.query();
        wrapper.orderByDesc("cdate");
        IPage<Site> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, SiteVO.ListVO.class, page);
    }


    @Override
    public void editSite(SiteDTO.ETO eto) {
        QueryWrapper queryWrapper =  MybatisPlusUtil.query();
        Site oneItem = repository.getOne(queryWrapper);
        if(null != oneItem){
            if(!oneItem.getId().equals(eto.getId())){
                throw new BusinessException("不能添加多条数据");
            }
        }
        Site site = new Site();
        BeanUtils.copyProperties(eto, site);
        site.setPcShow(SitePCShowEnum.显示.getCode());
        repository.saveOrUpdate(site);
    }

    @Override
    public Boolean merchantCanUsePhoneLogin() {
        QueryWrapper queryWrapper =  MybatisPlusUtil.query();
        Site oneItem = repository.getOne(queryWrapper);
        return oneItem != null && oneItem.getState() != null && oneItem.getState().equals(10);
    }

}
