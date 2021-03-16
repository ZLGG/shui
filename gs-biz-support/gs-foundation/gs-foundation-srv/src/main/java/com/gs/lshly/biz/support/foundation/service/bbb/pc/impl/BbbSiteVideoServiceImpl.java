package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteVideo;
import com.gs.lshly.biz.support.foundation.repository.ISiteVideoRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteVideoService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteVideoService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-04
*/
@Component
public class BbbSiteVideoServiceImpl implements IBbbSiteVideoService {

    @Autowired
    private ISiteVideoRepository repository;

    @Override
    public List<BbbSiteVideoVO.ListVO> list(BbbSiteVideoQTO.QTO qto) {
        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",qto.getSubject());
        List<SiteVideo> siteVideoList = repository.list( wrapper);
        return ListUtil.listCover(BbbSiteVideoVO.ListVO.class,siteVideoList);
    }
}
