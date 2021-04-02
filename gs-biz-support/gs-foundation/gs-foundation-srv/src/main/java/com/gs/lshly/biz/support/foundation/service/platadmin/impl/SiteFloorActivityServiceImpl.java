package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorActivity;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorActivityRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorActivityService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorActivityDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorActivityQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorActivityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2021-03-20
*/
@Component
public class SiteFloorActivityServiceImpl implements ISiteFloorActivityService {

    @Autowired
    private ISiteFloorActivityRepository repository;



    @Override
    public SiteFloorActivityVO.ListVO getAdvertActivityList() {
        List<SiteFloorActivity> list = repository.list();
        if (ObjectUtils.isNotEmpty(list)){
            SiteFloorActivityVO.ListVO listVO = new SiteFloorActivityVO.ListVO();
            BeanUtils.copyProperties(list.get(0),listVO);
            return listVO;
        }
        return null;
    }

    @Override
    public void advertActivityEditor(SiteFloorActivityDTO.ETO eto) {
        System.out.println(eto+"11111111111111111111");
        List<SiteFloorActivity> one = repository.list();
        if (ObjectUtils.isEmpty(one)){
            //添加
            SiteFloorActivity siteFloorActivity = new SiteFloorActivity();
            siteFloorActivity.setFloorName(eto.getFloorName()).setImg(eto.getImg()).setLink(eto.getLink());
            repository.save(siteFloorActivity);
        }else {
            //修改
            one.get(0).setFloorName(eto.getFloorName()).setImg(eto.getImg()).setLink(eto.getLink());
            repository.updateById(one.get(0));
        }
    }

}
