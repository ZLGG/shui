package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.Pictures;
import com.gs.lshly.biz.support.foundation.repository.IPictureGroupRepository;
import com.gs.lshly.biz.support.foundation.repository.IPicturesRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IPicturesService;
import com.gs.lshly.common.enums.CompareTypeEnum;
import com.gs.lshly.common.enums.PictureMenuStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PicturesQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
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
* @since 2020-10-06
*/
@Component
public class PicturesServiceImpl implements IPicturesService {

    @Autowired
    private IPicturesRepository repository;
    @Autowired
    private IPictureGroupRepository pictureGroupRepository;


    @Override
    public PageData<PicturesVO.ListVO> pageData(PicturesQTO.QTO qto) {
        QueryWrapper<Pictures> boost = MybatisPlusUtil.query();
        if (qto.getMenuCondition() != null && qto.getMenuCondition().equals(PictureMenuStateEnum.平台图片.getCode())){
            boost.isNull("shop_id");
            boost.eq("merchant_id","-1");
            boost.eq("hidden",0);
        }
        if (qto.getMenuCondition() != null && qto.getMenuCondition().equals(PictureMenuStateEnum.店铺图片.getCode())){
            boost.isNotNull("shop_id");
            boost.eq("hidden",0);
        }
        //TODO 商家入驻土图片
        if (qto.getMenuCondition() != null && qto.getMenuCondition().equals(PictureMenuStateEnum.商家入驻图片.getCode()) ){
            boost.eq("hidden",0);
        }

        //TODO 用户图片
        if (qto.getMenuCondition() != null && qto.getMenuCondition().equals(PictureMenuStateEnum.用户图片.getCode()) ){
            boost.eq("hidden",0);
        }

        if (qto.getMenuCondition() != null && qto.getMenuCondition().equals(PictureMenuStateEnum.回收站.getCode())){
            boost.eq("hidden",1);
        }
        if (StringUtils.isNotEmpty(qto.getImageName())){
            boost.like("image_name",qto.getImageName());
        }
        if (StringUtils.isNotEmpty(qto.getImageUrl())){
            boost.like("image_url",qto.getImageUrl());
        }
        if (qto.getUdate() != null && qto.getCompareType().intValue() == CompareTypeEnum.大于.getCode()){
            boost.gt("udate",qto.getUdate());
        }
        if (qto.getUdate() != null && qto.getCompareType().intValue() == CompareTypeEnum.等于.getCode()){
            boost.eq("udate",qto.getUdate());
        }
        if (qto.getUdate() != null && qto.getCompareType().intValue() == CompareTypeEnum.小于.getCode()){
            boost.lt("udate",qto.getUdate());
        }
        IPage<Pictures> page = MybatisPlusUtil.pager(qto);
        repository.page(page,boost);
        return MybatisPlusUtil.toPageData(qto, PicturesVO.ListVO.class, page);
    }

    @Override
    public void addPictures(PicturesDTO.ETO eto) {
        Pictures pictures = new Pictures();
        BeanUtils.copyProperties(eto, pictures);
        repository.save(pictures);
    }

    @Override
    public void moveInRecyclePictures(PicturesDTO.IdListDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        UpdateWrapper<Pictures> wrapper = MybatisPlusUtil.update();
        wrapper.in("id",dto.getIdList());

        Pictures pictures = new Pictures();
        pictures.setHidden(1);

        repository.update(pictures,wrapper);
    }

    @Override
    public void moveToOldPlacePictures(PicturesDTO.IdListDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        UpdateWrapper<Pictures> wrapper = MybatisPlusUtil.update();
        wrapper.in("id",dto.getIdList());

        Pictures pictures = new Pictures();
        pictures.setHidden(0);

        repository.update(pictures,wrapper);
    }

    @Override
    public void moveGroup(List<PicturesDTO.MoveGroupDTO> dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空");
        }
        for (PicturesDTO.MoveGroupDTO moveGroupDTO : dto){
            UpdateWrapper<Pictures> wrapper = new UpdateWrapper<>();
            wrapper.eq("id",moveGroupDTO.getId());
            Pictures pictures = new Pictures();
            pictures.setGroupId(moveGroupDTO.getGroupId());
            repository.update(pictures,wrapper);
        }
    }

    @Override
    public void deletePictures(PicturesDTO.IdListDTO dto) {
        repository.removeByIds(dto.getIdList());
    }

    @Override
    public PageData<PicturesVO.ListVO> pagePicturesBy(PictureGroupQTO.GroupPictureQTO qto) {

        QueryWrapper<Pictures> boost = MybatisPlusUtil.query();
        boost.eq("merchant_id","-1");
        boost.isNull("shop_id");
        if (StringUtils.isNotEmpty(qto.getGroupId())){
            boost.eq("group_id",qto.getGroupId());
        }

        IPage<Pictures> page = MybatisPlusUtil.pager(qto);
        repository.page(page, boost);

       return MybatisPlusUtil.toPageData(qto, PicturesVO.ListVO.class,page);
    }

    @Override
    public List<PicturesVO.ListVO> getPicturesByGroup(PictureGroupQTO.GroupPictureQTO qto) {
        QueryWrapper<Pictures> boost = MybatisPlusUtil.query();
        boost.eq("group_id",qto.getGroupId());
        List<Pictures> pictures = repository.list(boost);
        if (ObjectUtils.isNotEmpty(pictures)){
            List<PicturesVO.ListVO> listVOS = new ArrayList<>();
            for (Pictures pictures1:pictures) {
                PicturesVO.ListVO listVO = new PicturesVO.ListVO();
                BeanUtils.copyProperties(pictures1,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }


}
