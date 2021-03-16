package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteNotice;
import com.gs.lshly.biz.support.foundation.entity.SiteTopic;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteTopicService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  公告通知
* </p>
* @author yingjun
* @since 2020-11-03
*/
@Component
public class BbbSiteTopicServiceImpl implements IBbbSiteTopicService {

    @Autowired
    private ISiteTopicRepository repository;

    @Override
    public List<BbbSiteTopicVO.ListVO> list() {
        QueryWrapper<SiteTopic> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
        wrapper.orderByDesc("id");
        List<SiteTopic> list = repository.list(wrapper);
        List<BbbSiteTopicVO.ListVO> retList = new ArrayList<BbbSiteTopicVO.ListVO>();
        retList = BeanUtils.copyList(BbbSiteTopicVO.ListVO.class, list);
        return retList;
    }


}
