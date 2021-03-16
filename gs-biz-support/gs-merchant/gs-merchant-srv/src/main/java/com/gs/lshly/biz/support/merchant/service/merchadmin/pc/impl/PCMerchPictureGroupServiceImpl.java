package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.PictureGroup;
import com.gs.lshly.biz.support.merchant.entity.Pictures;
import com.gs.lshly.biz.support.merchant.repository.IPictureGroupRepository;
import com.gs.lshly.biz.support.merchant.repository.IPicturesRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchPictureGroupService;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchPicturesService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPictureGroupDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPictureGroupQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPictureGroupVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-22
*/
@Component
public class PCMerchPictureGroupServiceImpl implements IPCMerchPictureGroupService {

    @Autowired
    private IPictureGroupRepository repository;
    @Autowired
    private IPicturesRepository picturesRepository;


    @Override
    public List<PCMerchPictureGroupVO.ListVO> listGroup(PCMerchPictureGroupQTO.BelongIdQTO qto) {
        if (qto == null){
            throw new BusinessException("参数不能空！");
        }
        QueryWrapper<PictureGroup> boost = MybatisPlusUtil.query();
        if (!qto.getBelongId().equals("-1")){
            boost.eq("belong_id",qto.getJwtShopId());
        }
        List<PictureGroup> pictureGroups = repository.list(boost);
        if (ObjectUtils.isNotEmpty(pictureGroups)){
            List<PCMerchPictureGroupVO.ListVO> listVOS = new ArrayList<>();
            for (PictureGroup group:pictureGroups) {
                PCMerchPictureGroupVO.ListVO listVO = new PCMerchPictureGroupVO.ListVO();
                BeanUtils.copyProperties(group,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }

    @Override
    public void addPictureGroup(PCMerchPictureGroupDTO.ETO eto) {
        PictureGroup pictureGroup = new PictureGroup();
        BeanUtils.copyProperties(eto, pictureGroup);
        if (!eto.getBelongId().equals("-1")){
            pictureGroup.setBelongId(eto.getJwtShopId());
        }
        repository.save(pictureGroup);
    }


    @Override
    public void deletePictureGroup(PCMerchPictureGroupDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<Pictures> wrapper = MybatisPlusUtil.query();
        wrapper.eq("group_id",dto.getId());
        int count = picturesRepository.count(wrapper);
        if (count > 0){
            throw new BusinessException("该文件夹下面有图片不可以直接删除！");
        }
        repository.removeById(dto.getId());
    }

    @Override
    public void editPictureGroup(PCMerchPictureGroupDTO.ETO eto) {
        PictureGroup pictureGroup = new PictureGroup();
        BeanUtils.copyProperties(eto,pictureGroup);
        if (!eto.getBelongId().equals("-1")){
            pictureGroup.setBelongId(eto.getJwtShopId());
        }
        repository.updateById(pictureGroup);
    }


}
