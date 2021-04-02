package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteActive;
import com.gs.lshly.biz.support.foundation.repository.ISiteActiveRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteActiveService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteActiveDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteActiveVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2021-03-30
*/
@Component
public class SiteActiveServiceImpl implements ISiteActiveService {

    @Autowired
    private ISiteActiveRepository repository;


    @Override
    public SiteActiveVO.ListVO getListVO(SiteActiveDTO.QueryDTO dto) {
        QueryWrapper<SiteActive> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",dto.getTerminal());
        wrapper.eq("pc_show", dto.getPcShow());
        SiteActive active = repository.getOne(wrapper);
        if (null != active){
            SiteActiveVO.ListVO listVO = new SiteActiveVO.ListVO();
            BeanUtils.copyProperties(active,listVO);
            return listVO;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSiteActive(SiteActiveDTO.ETO eto) {
        //清除配置
        QueryWrapper<SiteActive> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",eto.getTerminal());
        wrapper.eq("pc_show", eto.getPcShow());
        repository.remove(wrapper);

        //创建配置
        if (null != eto ){
            if (StringUtils.isBlank(eto.getFloorName())){
                throw new BusinessException("请填写名称");
            }
            if (StringUtils.isBlank(eto.getImgUrl())){
                throw new BusinessException("请上传图片！");
            }
            SiteActive active = new SiteActive();
            BeanUtils.copyProperties(eto,active);
            active.setId("");
            repository.save(active);
        }

    }
}
