package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.Pictures;
import com.gs.lshly.biz.support.merchant.repository.IPicturesRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchPicturesService;
import com.gs.lshly.common.enums.OrderByTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.middleware.mybatisplus.MerchantPermitUtil;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPicturesDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class PCMerchPicturesServiceImpl implements IPCMerchPicturesService {

    @Autowired
    private IPicturesRepository repository;

    @Override
    public PageData<PCMerchPicturesVO.ListVO> pageData(PCMerchPicturesQTO.QTO qto) {
        QueryWrapper<Pictures> boost = MybatisPlusUtil.query();
        MerchantPermitUtil.permitShop(boost, qto);
        if (StringUtils.isNotEmpty(qto.getGroupId())){
            boost.eq("group_id",qto.getGroupId());
        }
        if (StringUtils.isNotEmpty(qto.getImageName())){
            boost.like("original_image_name",qto.getImageName());
        }
        if (StringUtils.isNotEmpty(qto.getOrderByProperty()) && qto.getOrderByProperty().equals("name")){
            if (ObjectUtils.isNotEmpty(qto.getOrderWays()) && qto.getOrderByProperty().equals(OrderByTypeEnum.升序.getCode())){
                boost.orderByAsc("original_image_name");
            }else {
                boost.orderByDesc("original_image_name");
            }

        }
        if (StringUtils.isNotEmpty(qto.getOrderByProperty()) && qto.getOrderByProperty().equals("time")){
            if (ObjectUtils.isNotEmpty(qto.getOrderWays()) && qto.getOrderByProperty().equals(OrderByTypeEnum.升序.getCode())){
                boost.orderByAsc("cdate");
            }else {
                boost.orderByDesc("cdate");
            }
        }
        IPage<Pictures> page = MybatisPlusUtil.pager(qto);
        repository.page(page, boost);
        return MybatisPlusUtil.toPageData(qto, PCMerchPicturesVO.ListVO.class, page);
    }

    @Override
    public void addPictures(PCMerchPicturesDTO.ETO eto) {
        Pictures pictures = new Pictures();
        BeanUtils.copyProperties(eto, pictures);
        pictures.setShopId(eto.getJwtShopId());
        if (!eto.getMerchantId().equals("-1")){
            pictures.setMerchantId(eto.getJwtMerchantId());
        }
        repository.save(pictures);
    }


    @Override
    public void deletePictures(PCMerchPicturesDTO.IdListDTO dto) {
        repository.removeByIds(dto.getIdList());
    }

    @Override
    public PCMerchPicturesVO.DetailVO detailPictures(PCMerchPicturesDTO.IdDTO dto) {
        Pictures pictures = repository.getById(dto.getId());
        PCMerchPicturesVO.DetailVO detailVo = new PCMerchPicturesVO.DetailVO();
        if(ObjectUtils.isEmpty(pictures)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(pictures, detailVo);
        return detailVo;
    }

    @Override
    public void moveInGroup(List<PCMerchPicturesDTO.MoveGroupETO> etos) {
        if (ObjectUtils.isEmpty(etos)){
            throw new BusinessException("参数不能为空");
        }
        for (PCMerchPicturesDTO.MoveGroupETO eto : etos){
            QueryWrapper<Pictures> boost = MybatisPlusUtil.query();
            boost.eq("id",eto.getId());

            Pictures pictures = new Pictures();
            pictures.setGroupId(eto.getGroupId());

            repository.update(pictures,boost);
        }
    }

    @Override
    public List<PCMerchPicturesVO.ListVO> getPicturesByGroup(PCMerchPicturesQTO.GroupIdQTO qto) {
        QueryWrapper<Pictures> boost = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getGroupId()) || !qto.getGroupId().equals("-1")){
            boost.eq("group_id",qto.getGroupId());
        }
        List<Pictures> pictures = repository.list(boost);
        if (ObjectUtils.isNotEmpty(pictures)){
            List<PCMerchPicturesVO.ListVO> listVOS = new ArrayList<>();
            for (Pictures pictures1:pictures) {
                PCMerchPicturesVO.ListVO listVO = new PCMerchPicturesVO.ListVO();
                BeanUtils.copyProperties(pictures1,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return new ArrayList<>();
    }

}
