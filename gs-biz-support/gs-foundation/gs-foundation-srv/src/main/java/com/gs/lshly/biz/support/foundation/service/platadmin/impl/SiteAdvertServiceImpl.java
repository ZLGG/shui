package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteAdvert;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteAdvertService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertVO;
import com.gs.lshly.common.utils.EnumUtil;
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
public class SiteAdvertServiceImpl implements ISiteAdvertService {

    @Autowired
    private ISiteAdvertRepository repository;


    @Override
    public List<SiteAdvertVO.H5CategoryListVO> h5CategoryList(SiteAdvertQTO.H5CategoryQTO qto) {
        QueryWrapper<SiteAdvert> wrapper= MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("is_category",TrueFalseEnum.是.getCode());
        wrapper.orderByAsc("idx");
        List<SiteAdvert> siteAdvertList = repository.list(wrapper);
        if(ObjectUtils.isEmpty(siteAdvertList)){
            return null;
        }
        List<SiteAdvertVO.H5CategoryListVO> categoryVoList = new ArrayList<>();
        for(SiteAdvert siteAdvert:siteAdvertList){
            boolean check = true;
            for(SiteAdvertVO.H5CategoryListVO categoryListVO:categoryVoList){
                if(categoryListVO.getCategoryId().equals(siteAdvert.getCategoryId())){
                    check = false;
                    break;
                }
            }
            if(check == true){
                SiteAdvertVO.H5CategoryListVO categoryListVO = new SiteAdvertVO.H5CategoryListVO();
                categoryListVO.setCategoryId(siteAdvert.getCategoryId());
                categoryVoList.add(categoryListVO);
            }
        }

        for(SiteAdvertVO.H5CategoryListVO categoryListVO:categoryVoList){
            for(SiteAdvert siteAdvert:siteAdvertList){
                if(categoryListVO.getCategoryId().equals(siteAdvert.getCategoryId())){
                    SiteAdvertVO.H5AdvertItemVO advertItemVO = new SiteAdvertVO.H5AdvertItemVO();
                    BeanUtils.copyProperties(siteAdvert,advertItemVO);
                    categoryListVO.getAdvertList().add(advertItemVO);
                }
            }
        }
        return categoryVoList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void h5CategoryEditor(SiteAdvertDTO.H5CategoryETO eto) {
        QueryWrapper<SiteAdvert> removeQueryWrapper = MybatisPlusUtil.query();
        removeQueryWrapper.eq("terminal",eto.getTerminal());
        removeQueryWrapper.eq("pc_show", PcH5Enum.NO.getCode());
        removeQueryWrapper.eq("is_category",TrueFalseEnum.是.getCode());
        repository.remove(removeQueryWrapper);
        List<SiteAdvert> siteAdvertBatchList = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(eto.getCategoryList())){
            for(SiteAdvertDTO.H5CategoryItem categoryItem:eto.getCategoryList()){
                if(ObjectUtils.isNotEmpty(categoryItem.getAdvertList())){
                    for(SiteAdvertDTO.H5CategoryAdvertItem advertItem:categoryItem.getAdvertList()){
                        SiteAdvert siteAdvert = new SiteAdvert();
                        siteAdvert.setImageUrl(advertItem.getImageUrl());
                        siteAdvert.setJumpUrl(advertItem.getJumpUrl());
                        siteAdvert.setText(advertItem.getText());
                        siteAdvert.setIdx(advertItem.getIdx());
                        siteAdvert.setTerminal(eto.getTerminal());
                        siteAdvert.setIsCategory(TrueFalseEnum.是.getCode());
                        siteAdvert.setCategoryId(categoryItem.getCategoryId());
                        siteAdvert.setPcShow(PcH5Enum.NO.getCode());
                        siteAdvertBatchList.add(siteAdvert);
                    }
                }
            }
            repository.saveBatch(siteAdvertBatchList);
        }
    }

    @Override
    public List<SiteAdvertVO.H5SubjectListVO> h5SubjectList(SiteAdvertQTO.H5SubjectQTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteAdvert> wrapper= MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("advert_type", AdvertTypeEnum.单张广告.getCode());
        wrapper.eq("is_category",TrueFalseEnum.否.getCode());
        List<SiteAdvert> siteAdvertList = repository.list(wrapper);
        return ListUtil.listCover(SiteAdvertVO.H5SubjectListVO.class,siteAdvertList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void h5SubjectEditor(SiteAdvertDTO.H5SubjectETO eto) {
        if(ObjectUtils.isNotEmpty(eto.getAdvertList())){
            List<SiteAdvert> siteAdvertList = new ArrayList<>();
            eto.getAdvertList().forEach(item -> {
                SiteAdvert siteAdvert = new SiteAdvert();
                BeanUtils.copyProperties(item, siteAdvert);
                siteAdvert.setAdvertType(AdvertTypeEnum.单张广告.getCode());
                siteAdvert.setPcShow(PcH5Enum.NO.getCode());
                siteAdvert.setIsCategory(TrueFalseEnum.否.getCode());
                siteAdvert.setSubject(item.getSubject());
                if(TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteAdvert.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
                        if(eto.getRemoveIdList().contains(siteAdvert.getId())){
                            throw new BusinessException("保存的数据和删除的数据冲突");
                        }
                    }
                }
                siteAdvertList.add(siteAdvert);
            });
            repository.saveOrUpdateBatch(siteAdvertList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
            repository.removeByIds(eto.getRemoveIdList());
        }
    }

    @Override
    public List<SiteAdvertVO.PCGroupListVO> pcAdvertGroupList(SiteAdvertQTO.PCSubjectQTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteAdvert> wrapper= MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("advert_type", AdvertTypeEnum.组合广告.getCode());
        wrapper.eq("is_category",TrueFalseEnum.否.getCode());
        List<SiteAdvert> siteAdvertList = repository.list(wrapper);
        return ListUtil.listCover(SiteAdvertVO.PCGroupListVO.class,siteAdvertList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcAdvertGroupEditor(SiteAdvertDTO.PCGroupETO eto) {
        if(!EnumUtil.checkEnumCode(eto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteAdvert> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("advert_type",AdvertTypeEnum.组合广告.getCode());
        queryWrapper.eq("pc_show",PcH5Enum.YES.getCode());
        queryWrapper.eq("is_category",TrueFalseEnum.否.getCode());
        queryWrapper.eq("subject",eto.getSubject());
        queryWrapper.eq("terminal",eto.getTerminal());
        repository.remove(queryWrapper);
        if (ObjectUtils.isNotEmpty(eto.getAdvertList())) {
            List<SiteAdvert> siteAdvertList = new ArrayList<>();
            for(SiteAdvertDTO.PCGroupItem groupItem:eto.getAdvertList()){
                SiteAdvert siteAdvert = new SiteAdvert();
                BeanUtils.copyProperties(groupItem, siteAdvert);
                siteAdvert.setTerminal(eto.getTerminal());
                siteAdvert.setPcShow(PcH5Enum.YES.getCode());
                siteAdvert.setSubject(eto.getSubject());
                siteAdvert.setAdvertType(AdvertTypeEnum.组合广告.getCode());
                siteAdvert.setIsCategory(TrueFalseEnum.否.getCode());
                siteAdvertList.add(siteAdvert);
            }
            repository.saveBatch(siteAdvertList);
        }
    }

    @Override
    public SiteAdvertVO.PCBillBoardListVO pcBillBoardList(SiteAdvertQTO.PCBillBoardQTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteAdvert> wrapper= MybatisPlusUtil.query();
        wrapper.eq("terminal", qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("advert_type", AdvertTypeEnum.单张广告.getCode());
        wrapper.eq("is_category",TrueFalseEnum.否.getCode());
        List<SiteAdvert> siteAdvertList = repository.list(wrapper);
        if(ObjectUtils.isEmpty(siteAdvertList)){
            return null;
        }
        SiteAdvertVO.PCBillBoardListVO billBoardListVO = new SiteAdvertVO.PCBillBoardListVO();
        BeanUtils.copyProperties(siteAdvertList.get(0),billBoardListVO);
        return billBoardListVO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcBillBoardEditor(SiteAdvertDTO.PCBillBoardETO eto) {
        if(!EnumUtil.checkEnumCode(eto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteAdvert> removeWrapper= MybatisPlusUtil.query();
        removeWrapper.eq("terminal", eto.getTerminal());
        removeWrapper.eq("subject",eto.getSubject());
        removeWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        removeWrapper.eq("advert_type", AdvertTypeEnum.单张广告.getCode());
        removeWrapper.eq("is_category",TrueFalseEnum.否.getCode());
        repository.remove(removeWrapper);
        SiteAdvert siteAdvert = new SiteAdvert();
        BeanUtils.copyProperties(eto,siteAdvert);
        siteAdvert.setPcShow(PcH5Enum.YES.getCode());
        siteAdvert.setAdvertType(AdvertTypeEnum.单张广告.getCode());
        siteAdvert.setIsCategory(TrueFalseEnum.否.getCode());
        repository.save(siteAdvert);
    }

    @Override
    public List<SiteAdvertVO.PCBillBoardListVO> pcBillBoardMoreList(SiteAdvertQTO.PCBillBoardQTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteAdvert> wrapper= MybatisPlusUtil.query();
        wrapper.eq("terminal", qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("advert_type", AdvertTypeEnum.单张广告.getCode());
        wrapper.eq("is_category",TrueFalseEnum.否.getCode());
        List<SiteAdvert> siteAdvertList = repository.list(wrapper);
        if(ObjectUtils.isEmpty(siteAdvertList)){
            return null;
        }
        return ListUtil.listCover(SiteAdvertVO.PCBillBoardListVO.class,siteAdvertList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcBillBoardMoreEditor(SiteAdvertDTO.PCBillBoardMoreETO eto) {
        if(!EnumUtil.checkEnumCode(eto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteAdvert> removeWrapper= MybatisPlusUtil.query();
        removeWrapper.eq("terminal", eto.getTerminal());
        removeWrapper.eq("subject",eto.getSubject());
        removeWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        removeWrapper.eq("advert_type", AdvertTypeEnum.单张广告.getCode());
        removeWrapper.eq("is_category",TrueFalseEnum.否.getCode());
        repository.remove(removeWrapper);
        if(ObjectUtils.isNotEmpty(eto.getBillBoardList())){
            List<SiteAdvert> batchList = new ArrayList<>();
            for(SiteAdvertDTO.PCBillBoardItem billBoardItem:eto.getBillBoardList()){
                SiteAdvert siteAdvert = new SiteAdvert();
                BeanUtils.copyProperties(billBoardItem,siteAdvert);
                siteAdvert.setTerminal(eto.getTerminal());
                siteAdvert.setSubject(eto.getSubject());
                siteAdvert.setPcShow(PcH5Enum.YES.getCode());
                siteAdvert.setAdvertType(AdvertTypeEnum.单张广告.getCode());
                siteAdvert.setIsCategory(TrueFalseEnum.否.getCode());
                batchList.add(siteAdvert);
            }
            repository.saveBatch(batchList);
        }

    }
}
