package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteBannerService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBannerQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-02
*/
@Component
public class BbbH5SiteBannerServiceImpl implements IBbbH5SiteBannerService {

    @Autowired
    private ISiteBannerRepository repository;

    @Override
    public List<BbbH5SiteBannerVO.ListVO> list(BbbH5SiteBannerQTO.QTO qto) {
        QueryWrapper<SiteBanner> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",qto.getSubject());
        List<SiteBanner> siteBannerList = repository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteBannerVO.ListVO.class,siteBannerList);
    }


}
