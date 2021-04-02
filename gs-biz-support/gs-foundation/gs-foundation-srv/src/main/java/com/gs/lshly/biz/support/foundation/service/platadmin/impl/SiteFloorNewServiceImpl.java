package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorNew;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorNewRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorNewService;
import com.gs.lshly.common.enums.SitePCShowEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorNewDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorNewQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorNewVO;
import com.gs.lshly.common.utils.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2021-03-10
*/
@Component
public class SiteFloorNewServiceImpl implements ISiteFloorNewService {

    @Autowired
    private ISiteFloorNewRepository repository;

    @Override
    public List<SiteFloorNewVO.ListVO> listSiteFloorNewVO(SiteFloorNewDTO.ShowDTO dto) {
        List<SiteFloorNewVO.ListVO> listVOS = new ArrayList<>();
        QueryWrapper<SiteFloorNew> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", dto.getPcShow());
        wrapper.eq("terminal",dto.getTerminal());
        wrapper.orderByDesc("cdate");
        List<SiteFloorNew> list = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(list)){
            listVOS = ListUtil.listCover(SiteFloorNewVO.ListVO.class,list);
        }
        return listVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSiteFloorNew(List<SiteFloorNewDTO.ETO> etoList) {
        //先清除配置数据
        QueryWrapper<SiteFloorNew> wrapper = MybatisPlusUtil.query();
        repository.remove(wrapper);

        if (ObjectUtils.isNotEmpty(etoList)){
            //创建配置数据
            List<SiteFloorNew> siteFloorNews = new ArrayList<>();
            for (SiteFloorNewDTO.ETO eto : etoList){
                if (StringUtils.isBlank(eto.getFloorName())){
                    throw new BusinessException("楼层名称为空！");
                }
                SiteFloorNew siteFloorNew = new SiteFloorNew();
                BeanUtils.copyProperties(eto,siteFloorNew);
                siteFloorNew.setId("");
                siteFloorNews.add(siteFloorNew);
            }
            repository.saveBatch(siteFloorNews);
        }

    }
}
