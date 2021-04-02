package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.PictureGroup;
import com.gs.lshly.biz.support.foundation.repository.IPictureGroupRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IPictureGroupService;
import com.gs.lshly.biz.support.foundation.service.platadmin.IPicturesService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PictureGroupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PictureGroupVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
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
* @author Starry
* @since 2020-10-06
*/
@Component
public class PictureGroupServiceImpl implements IPictureGroupService {

    @Autowired
    private IPictureGroupRepository repository;
    @Autowired
    private IPicturesService picturesService;

    @Override
    public List<PictureGroupVO.ListVO> pageData(PictureGroupQTO.QTO qto) {
        QueryWrapper<PictureGroup> wq = MybatisPlusUtil.query();
        wq.eq("belong_id",-1);
        wq.orderByDesc("cdate");
        List<PictureGroup> list = repository.list(wq);
        return qto.toListData(PictureGroupVO.ListVO.class, list);
    }

    @Override
    public void addPictureGroup(PictureGroupDTO.ETO eto) {
        PictureGroup pictureGroup = new PictureGroup();
        BeanUtils.copyProperties(eto, pictureGroup);
        repository.save(pictureGroup);
    }


    @Override
    public void deletePictureGroup(PictureGroupDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        PictureGroupQTO.GroupPictureQTO groupIdQTO = new PictureGroupQTO.GroupPictureQTO();
        groupIdQTO.setGroupId(dto.getId());
        List<PicturesVO.ListVO> listVOS = picturesService.getPicturesByGroup(groupIdQTO);
        if (ObjectUtils.isNotEmpty(listVOS)){
            throw new BusinessException("该分组下面有图片不可以直接删除！");
        }
        repository.removeById(dto.getId());
    }


    @Override
    public void editPictureGroup(PictureGroupDTO.ETO eto) {
        PictureGroup pictureGroup = new PictureGroup();
        BeanUtils.copyProperties(eto, pictureGroup);
        repository.updateById(pictureGroup);
    }

    @Override
    public PictureGroupVO.DetailVO detailPictureGroup(PictureGroupDTO.IdDTO dto) {
        PictureGroup pictureGroup = repository.getById(dto.getId());
        PictureGroupVO.DetailVO detailVo = new PictureGroupVO.DetailVO();
        if(ObjectUtils.isEmpty(pictureGroup)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(pictureGroup, detailVo);
        return detailVo;
    }

}
