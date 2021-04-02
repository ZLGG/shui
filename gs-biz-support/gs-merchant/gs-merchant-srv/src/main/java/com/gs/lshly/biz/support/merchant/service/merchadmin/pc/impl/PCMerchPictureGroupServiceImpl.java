package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.PictureGroup;
import com.gs.lshly.biz.support.merchant.entity.Pictures;
import com.gs.lshly.biz.support.merchant.repository.IPictureGroupRepository;
import com.gs.lshly.biz.support.merchant.repository.IPicturesRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchPictureGroupService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPictureGroupDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPictureGroupVO;
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
    public List<PCMerchPictureGroupVO.ListVO> listGroup(PCMerchPictureGroupDTO.ParentIdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getParentId())){
            throw new BusinessException("参数异常！");
        }
        List<PCMerchPictureGroupVO.ListVO> listVOS = new ArrayList<>();
        QueryWrapper<PictureGroup> wrapper = MybatisPlusUtil.query();
        if (dto.getParentId().equals("-1")){
            wrapper.eq("parent_id",dto.getParentId());

        }
        if (!dto.getParentId().equals("-1")){
            wrapper.eq("parent_id",dto.getParentId()).or().eq("id",dto.getParentId());
        }
        wrapper.eq("belong_id",dto.getJwtShopId());
        wrapper.orderByDesc("cdate","id");
        List<PictureGroup> pictureGroups = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(pictureGroups)){
            for (PictureGroup group:pictureGroups) {
                PCMerchPictureGroupVO.ListVO listVO = new PCMerchPictureGroupVO.ListVO();
                BeanUtils.copyProperties(group,listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

    @Override
    public void addPictureGroup(PCMerchPictureGroupDTO.SaveETO eto, BaseDTO baseDTO) {
        if (null == eto || ObjectUtils.isEmpty(eto.getGroupValues())){
            throw new BusinessException("参数为空，异常！！");
        }
        if (ObjectUtils.isEmpty(eto.getParentId())){
            throw new BusinessException("父文件夹ID必传！");
        }
        List<PictureGroup> pictureGroups = new ArrayList<>();
        for (PCMerchPictureGroupDTO.ETO dto : eto.getGroupValues()){
            PictureGroup pictureGroup = new PictureGroup();
            BeanUtils.copyProperties(dto, pictureGroup);
            pictureGroup.setBelongId(baseDTO.getJwtShopId());
            pictureGroup.setParentId(eto.getParentId());
            if (ObjectUtils.isEmpty(eto.getParentLevel())){
                pictureGroup.setLevel(1);
            }else {
                int level = eto.getParentLevel() +1;
                pictureGroup.setLevel(level);
            }
            pictureGroups.add(pictureGroup);
        }
        repository.saveOrUpdateBatch(pictureGroups);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePictureGroup(PCMerchPictureGroupDTO.IdListDTO dto) {
        if (dto == null || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要删除的文件夹！");
        }
        for (String id : dto.getIdList()){
            QueryWrapper<Pictures> wrapper = MybatisPlusUtil.query();
            wrapper.in("group_id",id);
            int count = picturesRepository.count(wrapper);
            if (count > 0){
                throw new BusinessException("该文件夹下面有图片不可以直接删除！");
            }
            QueryWrapper<PictureGroup> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.eq("parent_id",id);
            int count2 = repository.count(queryWrapper);
            if (count2 > 0){
                throw new BusinessException("该文件夹下有子文件不可以直接删除！");
            }
        }
        QueryWrapper<PictureGroup> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());
        repository.remove(wrapper);
    }

    @Override
    public void editPictureGroup(PCMerchPictureGroupDTO.ETO eto) {
        PictureGroup pictureGroup = new PictureGroup();
        BeanUtils.copyProperties(eto,pictureGroup);
        repository.updateById(pictureGroup);
    }


}
