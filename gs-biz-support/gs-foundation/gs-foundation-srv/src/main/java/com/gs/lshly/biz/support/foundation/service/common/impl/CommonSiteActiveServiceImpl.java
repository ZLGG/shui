package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteActive;
import com.gs.lshly.biz.support.foundation.repository.ISiteActiveRepository;
import com.gs.lshly.biz.support.foundation.service.common.ICommonSiteActiveService;
import com.gs.lshly.common.struct.common.dto.CommonSiteActiveDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteActiveVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Starry
 * @Date 16:12 2021/3/30
 */
@Component
public class CommonSiteActiveServiceImpl implements ICommonSiteActiveService {

    @Autowired
    private ISiteActiveRepository siteActiveRepository;


    @Override
    public CommonSiteActiveVO.ListVO getCommonSiteActiveVO(CommonSiteActiveDTO.QueryDTO dto) {
        QueryWrapper<SiteActive> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",dto.getTerminal());
        wrapper.eq("pc_show", dto.getPcShow());
        SiteActive active = siteActiveRepository.getOne(wrapper);
        if (null != active){
            CommonSiteActiveVO.ListVO listVO = new CommonSiteActiveVO.ListVO();
            BeanUtils.copyProperties(active,listVO);
            return listVO;
        }
        return null;
    }
}
