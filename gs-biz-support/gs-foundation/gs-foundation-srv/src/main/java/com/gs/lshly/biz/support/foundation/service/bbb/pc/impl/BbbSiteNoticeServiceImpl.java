package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteNotice;
import com.gs.lshly.biz.support.foundation.repository.ISiteNoticeRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteNoticeService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO.IDQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO.DetailVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
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
public class BbbSiteNoticeServiceImpl implements IBbbSiteNoticeService {

    @Autowired
    private ISiteNoticeRepository repository;

    @Override
    public List<BbbSiteNoticeVO.ListVO> list(BbbSiteNoticeQTO.QTO qto) {
        QueryWrapper<SiteNotice> wrapper =  MybatisPlusUtil.query();
        wrapper.orderByDesc("id");
        List<SiteNotice> list = repository.list(wrapper);
        List<BbbSiteNoticeVO.ListVO> retList = new ArrayList<BbbSiteNoticeVO.ListVO>();
        retList = BeanUtils.copyList(BbbSiteNoticeVO.ListVO.class, list);
        return retList;
    }

	@Override
	public DetailVO detail(IDQTO qto) {
		SiteNotice notice = repository.getById(qto.getId());
		DetailVO detailVO = new DetailVO();
		if(notice!=null) 
			BeanCopyUtils.copyProperties(notice, detailVO);
		return detailVO;
	}


}
