package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteBannerService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
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
public class BbcSiteBannerServiceImpl implements IBbcSiteBannerService {

    @Autowired
    private ISiteBannerRepository repository;

    @Override
    public List<BbcSiteBannerVO.ListVO> list(BbcSiteBannerQTO.QTO qto) {
        QueryWrapper<SiteBanner> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject",qto.getSubject());
        List<SiteBanner> siteBannerList=repository.list( wrapper);
        return ListUtil.listCover(BbcSiteBannerVO.ListVO.class,siteBannerList);
    }


}
