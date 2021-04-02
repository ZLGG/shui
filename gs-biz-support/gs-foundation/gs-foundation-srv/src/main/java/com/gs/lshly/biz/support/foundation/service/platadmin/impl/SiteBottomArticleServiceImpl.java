package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SiteBottomArticle;
import com.gs.lshly.biz.support.foundation.repository.ISiteBottomArticleRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteBottomArticleService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBottomArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBottomArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBottomArticleVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-08
*/
@Component
public class SiteBottomArticleServiceImpl implements ISiteBottomArticleService {

    @Autowired
    private ISiteBottomArticleRepository repository;

    @Override
    public List<SiteBottomArticleVO.PCListVO> list(SiteBottomArticleQTO.PCQTO qto) {
        QueryWrapper<SiteBottomArticle> wq =  MybatisPlusUtil.query();
        wq.eq("terminal",qto.getTerminal());
        wq.eq("pc_show",qto.getPcShow());
        wq.eq("subject",qto.getSubject());
        wq.orderByDesc("cdate");
        List<SiteBottomArticle> bottomList = repository.list(wq);
        return ListUtil.listCover(SiteBottomArticleVO.PCListVO.class,bottomList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void bottomArticleEditor(SiteBottomArticleDTO.PCUDTO dto) {

        QueryWrapper<SiteBottomArticle> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("terminal",dto.getTerminal());
        queryWrapper.eq("pc_show",dto.getPcShow());
        queryWrapper.eq("subject",dto.getSubject());
        repository.remove(queryWrapper);
        if(ObjectUtils.isNotEmpty(dto.getBottomList())){
            List<SiteBottomArticle> batchList  = new ArrayList<>();
            for(SiteBottomArticleDTO.PCBottomItem bottomItem:dto.getBottomList()){
                SiteBottomArticle bottomArticle = new SiteBottomArticle();
                BeanUtils.copyProperties(bottomItem,bottomArticle);
                bottomArticle.setTerminal(dto.getTerminal());
                bottomArticle.setPcShow(dto.getPcShow());
                bottomArticle.setSubject(dto.getSubject());
                batchList.add(bottomArticle);
            }
            if(ObjectUtils.isNotEmpty(batchList)){
                repository.saveBatch(batchList);
            }
        }
    }
}
