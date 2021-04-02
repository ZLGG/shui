package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteBroad;
import com.gs.lshly.biz.support.foundation.repository.ISiteBroadRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteBroadService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBroadDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBroadQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBroadVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

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
public class SiteBroadServiceImpl implements ISiteBroadService {

    @Autowired
    private ISiteBroadRepository repository;

    @Override
    public List<SiteBroadVO.ListVO> list(SiteBroadQTO.QTO qto) {
        QueryWrapper<SiteBroad> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.orderByDesc("cdate");
        List<SiteBroad> siteBroadList = repository.list(wrapper);
        return ListUtil.listCover(SiteBroadVO.ListVO.class,siteBroadList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editSiteBroad(SiteBroadDTO.ETO eto) {
        if(ObjectUtils.isNotEmpty(eto.getItemList())){
            List<SiteBroad> siteBroadList = new ArrayList<>();
            eto.getItemList().forEach(item -> {
                SiteBroad siteBroad = new SiteBroad();
                BeanUtils.copyProperties(item, siteBroad);
                if(TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteBroad.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
                        if(eto.getRemoveIdList().contains(siteBroad.getId())){
                            throw new BusinessException("保存的数据和删除的数据冲突");
                        }
                    }
                }
                siteBroadList.add(siteBroad);
            });
            repository.saveOrUpdateBatch(siteBroadList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
            repository.removeByIds(eto.getRemoveIdList());
        }
    }


}
