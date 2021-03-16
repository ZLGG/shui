package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteAdvert;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteAdvertService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteAdvertService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
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
* @author hyy
* @since 2020-11-03
*/
@Component
public class BbbSiteAdvertServiceImpl implements IBbbSiteAdvertService {

    @Autowired
    private ISiteAdvertRepository repository;

    @Override
    public PageData<BbbSiteAdvertVO.SubjectListVO> pageList(BbbSiteAdvertQTO.QTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(), SubjectEnum.class)){
            return MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(), (long) 0);
        }
        QueryWrapper<SiteAdvert> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal",TerminalEnum.BBB.getCode());
        wrapper.eq("pc_show",PcH5Enum.YES.getCode());
        wrapper.eq("is_category", TrueFalseEnum.否.getCode());
        wrapper.eq("subject",qto.getSubject());
        IPage<SiteAdvert> pager = MybatisPlusUtil.pager(qto);
        repository.page(pager,wrapper);
        return MybatisPlusUtil.toPageData(qto,BbbSiteAdvertVO.SubjectListVO.class,pager);
    }


}
