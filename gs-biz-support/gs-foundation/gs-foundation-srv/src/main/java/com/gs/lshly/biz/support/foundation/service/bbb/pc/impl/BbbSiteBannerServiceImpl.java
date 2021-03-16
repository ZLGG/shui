package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteBannerService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteBannerService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteBannerVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-02
*/
@Component
public class BbbSiteBannerServiceImpl implements IBbbSiteBannerService {

    @Autowired
    private ISiteBannerRepository repository;

    @Override
    public List<BbbSiteBannerVO.ListVO> list(BbbSiteBannerQTO.QTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(), SubjectEnum.class)){
            return new ArrayList<>();
        }
        QueryWrapper<SiteBanner> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",qto.getSubject());
        wrapper.orderByAsc("idx");
        List<SiteBanner> siteBannerList = repository.list( wrapper);
        return ListUtil.listCover(BbbSiteBannerVO.ListVO.class,siteBannerList);
    }
}
