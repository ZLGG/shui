package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteVideo;
import com.gs.lshly.biz.support.foundation.repository.ISiteVideoRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteVideoService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteVideoDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-04
*/
@Component
public class BbcSiteVideoServiceImpl implements IBbcSiteVideoService {

    @Autowired
    private ISiteVideoRepository repository;

    @Override
    public List<BbcSiteVideoVO.ListVO> list(BbcSiteVideoQTO.QTO qto) {
        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject",qto.getSubject());
        List<SiteVideo> siteVideoList = repository.list( wrapper);
        return ListUtil.listCover(BbcSiteVideoVO.ListVO.class,siteVideoList);
    }

    @Override
    public BbcSiteVideoVO.ListVO video(BbcSiteVideoQTO.QTO qto) {
        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject",qto.getSubject());
        List<SiteVideo> siteVideoList = repository.list( wrapper);
        if(ObjectUtils.isEmpty(siteVideoList)){
            return null;
        }
        BbcSiteVideoVO.ListVO videoVO = new BbcSiteVideoVO.ListVO();
        BeanUtils.copyProperties(siteVideoList.get(0),videoVO);
        return videoVO;
    }
}
