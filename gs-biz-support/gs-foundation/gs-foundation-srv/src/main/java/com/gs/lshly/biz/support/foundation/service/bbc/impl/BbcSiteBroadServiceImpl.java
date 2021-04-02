package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteBroad;
import com.gs.lshly.biz.support.foundation.repository.ISiteBroadRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteBroadService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBroadDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBroadQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBroadVO;
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
* @since 2020-11-03
*/
@Component
public class BbcSiteBroadServiceImpl implements IBbcSiteBroadService {

    @Autowired
    private ISiteBroadRepository repository;

    @Override
    public List<BbcSiteBroadVO.ListVO> list(BbcSiteBroadQTO.QTO qto) {
        QueryWrapper<SiteBroad> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.orderByDesc("cdate");
        List<SiteBroad> siteBroadList = repository.list( wrapper);
        return ListUtil.listCover(BbcSiteBroadVO.ListVO.class,siteBroadList);
    }

    @Override
    public BbcSiteBroadVO.DetailsVO details(BbcSiteBroadDTO.IdDTO dto) {
        SiteBroad siteBroad =  repository.getById(dto.getId());
        if(null == siteBroad){
            throw new BusinessException("公告文章不存在");
        }
        BbcSiteBroadVO.DetailsVO detailsVO = new BbcSiteBroadVO.DetailsVO();
        BeanUtils.copyProperties(siteBroad,detailsVO);
        return detailsVO;
    }


}
