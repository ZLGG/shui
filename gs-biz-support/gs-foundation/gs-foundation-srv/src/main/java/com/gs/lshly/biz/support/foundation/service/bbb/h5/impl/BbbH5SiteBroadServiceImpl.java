package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteBroad;
import com.gs.lshly.biz.support.foundation.repository.ISiteBroadRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteBroadService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5SiteBroadDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBroadQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBroadVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-03
*/
@Component
public class BbbH5SiteBroadServiceImpl implements IBbbH5SiteBroadService {

    @Autowired
    private ISiteBroadRepository repository;

    @Override
    public List<BbbH5SiteBroadVO.ListVO> list(BbbH5SiteBroadQTO.QTO qto) {
        QueryWrapper<SiteBroad> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.orderByDesc("cdate");
        List<SiteBroad> siteBroadList = repository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteBroadVO.ListVO.class,siteBroadList);
    }

    @Override
    public BbbH5SiteBroadVO.DetailsVO details(BbbH5SiteBroadDTO.IdDTO dto) {
        SiteBroad siteBroad =  repository.getById(dto.getId());
        if(null == siteBroad){
            throw new BusinessException("公告文章不存在");
        }
        BbbH5SiteBroadVO.DetailsVO detailsVO = new BbbH5SiteBroadVO.DetailsVO();
        BeanUtils.copyProperties(siteBroad,detailsVO);
        return detailsVO;
    }


}
