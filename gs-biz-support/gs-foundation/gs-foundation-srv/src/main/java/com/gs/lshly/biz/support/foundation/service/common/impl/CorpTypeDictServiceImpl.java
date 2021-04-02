package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.CorpTypeCert;
import com.gs.lshly.biz.support.foundation.entity.CorpTypeDict;
import com.gs.lshly.biz.support.foundation.entity.LegalDict;
import com.gs.lshly.biz.support.foundation.enums.CorpTypeDictQueryTypeEnum;
import com.gs.lshly.biz.support.foundation.mapper.CorpTypeCertMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.CorpTypeCertView;
import com.gs.lshly.biz.support.foundation.repository.ICorpTypeCertRepository;
import com.gs.lshly.biz.support.foundation.repository.ICorpTypeDictRepository;
import com.gs.lshly.biz.support.foundation.repository.ILegalDictRepository;
import com.gs.lshly.biz.support.foundation.service.common.ICorpTypeDictService;
import com.gs.lshly.common.enums.BusinessTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CorpTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpTypeDictQTO;
import com.gs.lshly.common.struct.common.CorpTypeDictVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-09
*/
@Component
public class CorpTypeDictServiceImpl implements ICorpTypeDictService {

    @Autowired
    private ICorpTypeDictRepository repository;

    @Autowired
    private ICorpTypeCertRepository corpTypeCertRepository;

    @Autowired
    private CorpTypeCertMapper corpTypeCertMapper;

    @Autowired
    private ILegalDictRepository legalDictRepository;


    @Override
    public List<CorpTypeDictVO.SimpleList> simpleList(CorpTypeDictQTO.SimpleQTO qto) {
        QueryWrapper<CorpTypeDict> queryWrapper =  MybatisPlusUtil.query();
        if(null == qto.getBusinessType()){
            throw new BusinessException("企业类型归属买家,卖家类型不能为空");
        }
        if(!BusinessTypeEnum.全部.getCode().equals(qto.getBusinessType())){
            queryWrapper.eq("type_group",qto.getBusinessType());
        }
        queryWrapper.orderByDesc("cdate");
        List<CorpTypeDict> corpTypeDictList = repository.list(queryWrapper);
        return ListUtil.listCover(CorpTypeDictVO.SimpleList.class,corpTypeDictList);
    }

    @Override
    public PageData<CorpTypeDictVO.ListVO> pageData(CorpTypeDictQTO.QTO qto) {
        QueryWrapper<CorpTypeDict> queryWrapper = MybatisPlusUtil.query();
        if(null != qto.getBusinessType() && !BusinessTypeEnum.全部.getCode().equals(qto.getBusinessType())){
            queryWrapper.eq("type_group",qto.getBusinessType());
        }
        if(StringUtils.isNotBlank(qto.getQueryValue())){
            if(CorpTypeDictQueryTypeEnum.企业类型名称.getCode().equals(qto.getQueryType())){
                queryWrapper.like("type_name",qto.getQueryValue());
            }
        }
        queryWrapper.orderByAsc("idx","id");
        IPage<CorpTypeDict> page = MybatisPlusUtil.pager(qto);
        IPage<CorpTypeDict> corpTypeDictIPage = repository.page(page, queryWrapper);
        if(ObjectUtils.isEmpty(corpTypeDictIPage) || ObjectUtils.isEmpty(page.getRecords())){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),page.getTotal());
        }
        List<String> idList = new ArrayList<>();
        Map<String,CorpTypeDictVO.ListVO> handerMap = new HashMap<>();
        for(CorpTypeDict corpTypeDict:corpTypeDictIPage.getRecords()){
            CorpTypeDictVO.ListVO listVO = new CorpTypeDictVO.ListVO();
            BeanUtils.copyProperties(corpTypeDict,listVO);
            listVO.setCertList(new ArrayList<>());
            handerMap.put(corpTypeDict.getId(),listVO);
            idList.add(corpTypeDict.getId());
        }
        QueryWrapper<CorpTypeCertView> queryWrapperCorpTypeCertView = MybatisPlusUtil.query();
        queryWrapper.in("d.id",idList);
        List<CorpTypeCertView> viewList = corpTypeCertMapper.mapperListCorpTypeCert(queryWrapperCorpTypeCertView);
        for(CorpTypeCertView viewItem:viewList){
            CorpTypeDictVO.ListVO listVO = handerMap.get(viewItem.getId());
            if(null != listVO){
                CorpTypeDictVO.CertItem certItem = new CorpTypeDictVO.CertItem();
                certItem.setId(viewItem.getCertId());
                certItem.setCertName(viewItem.getCertName());
                certItem.setIsNeed(viewItem.getIsNeed());
                listVO.getCertList().add(certItem);
            }
        }
        List<CorpTypeDictVO.ListVO> listVOS = new ArrayList<>(handerMap.values());
        listVOS.sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);

        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),corpTypeDictIPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addCorpTypeDict(CorpTypeDictDTO.ETO eto) {
        //数据校验
        checkData(eto);

        CorpTypeDict corpTypeDict = new CorpTypeDict();
        BeanUtils.copyProperties(eto, corpTypeDict);
        repository.save(corpTypeDict);

        List<CorpTypeCert> corpTypeCertList = new ArrayList<>();
        for(String certId:eto.getCertIdList()){
            CorpTypeCert corpTypeCert = new CorpTypeCert();
            corpTypeCert.setCorpTypeId(corpTypeDict.getId());
            corpTypeCert.setCertId(certId);
            corpTypeCertList.add(corpTypeCert);
        }
        if(ObjectUtils.isNotEmpty(corpTypeCertList)){
            corpTypeCertRepository.saveBatch(corpTypeCertList);
        }
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBatchCorpTypeDict(CorpTypeDictDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("ID数组不能为空");
        }
        QueryWrapper<LegalDict> wrapperCount = MybatisPlusUtil.query();
        wrapperCount.in("corp_type_id",dto.getIdList());
        int count = legalDictRepository.count(wrapperCount);
        if(count > 0){
            throw new BusinessException("该企业类型与法人单位数据有关联");
        }
        QueryWrapper<CorpTypeCert> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("corp_type_id",dto.getIdList());
        corpTypeCertMapper.delete(queryWrapper);

        repository.removeByIds(dto.getIdList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editCorpTypeDict(CorpTypeDictDTO.ETO eto) {
        //数据校验
        checkData(eto);

        CorpTypeDict corpTypeDict = new CorpTypeDict();
        BeanUtils.copyProperties(eto, corpTypeDict);
        repository.updateById(corpTypeDict);

        QueryWrapper<CorpTypeCert> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("corp_type_id",eto.getId());
        corpTypeCertRepository.remove(queryWrapper);

        List<CorpTypeCert> corpTypeCertList = new ArrayList<>();
        for(String certId:eto.getCertIdList()){
            CorpTypeCert corpTypeCert = new CorpTypeCert();
            corpTypeCert.setCorpTypeId(eto.getId());
            corpTypeCert.setCertId(certId);
            corpTypeCertList.add(corpTypeCert);
        }
        if(ObjectUtils.isNotEmpty(corpTypeCertList)){
            corpTypeCertRepository.saveBatch(corpTypeCertList);
        }
    }

    @Override
    public CorpTypeDictVO.DetailVO detailCorpTypeDict(CorpTypeDictDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数错误！！");
        }
        CorpTypeDict corpTypeDict = repository.getById(dto.getId());
        CorpTypeDictVO.DetailVO detailVo = new CorpTypeDictVO.DetailVO();
        if(ObjectUtils.isEmpty(corpTypeDict)){
            throw new BusinessException("数据查询异常");
        }
        BeanUtils.copyProperties(corpTypeDict, detailVo);
        QueryWrapper<CorpTypeCertView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("d.id",dto.getId());
        List<CorpTypeCertView> viewList = corpTypeCertMapper.mapperListCorpTypeCert(queryWrapper);
        if (ObjectUtils.isNotEmpty(viewList)){
            List<CorpTypeDictVO.CertItem> certItems = viewList.parallelStream().map(e ->{
                CorpTypeDictVO.CertItem certItem = new CorpTypeDictVO.CertItem();
                BeanUtils.copyProperties(e,certItem);
                certItem.setId(e.getCertId());
                return certItem;
            }).collect(Collectors.toList());
            detailVo.setCertList(certItems);
        }
        return detailVo;
    }

    private void checkData(CorpTypeDictDTO.ETO dto){
        if (null == dto){
            throw new BusinessException("参数为空异常！！");
        }
        if (StringUtils.isBlank(dto.getTypeName())){
            throw new BusinessException("企业类型名称不能为空！！");
        }
        if (filterSameName(dto) >0 ){
            throw new BusinessException("企业类型名称"+dto.getTypeName()+"已存在！！！");
        }
        if (ObjectUtils.isEmpty(dto.getCertIdList())){
            throw new BusinessException("请选择要关联的证照");
        }
    }

    private int filterSameName(CorpTypeDictDTO.ETO dto){
        QueryWrapper<CorpTypeDict> wrapper = MybatisPlusUtil.query();
        wrapper.eq("type_name",dto.getTypeName());
        int count = repository.count(wrapper);

        if (org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getId())){
            QueryWrapper<CorpTypeDict> boost = MybatisPlusUtil.query();
            boost.eq("id",dto.getId());
            CorpTypeDict corpTypeDict = repository.getOne(boost);
            if (corpTypeDict.getTypeName().equals(dto.getTypeName())){
                return 0;
            }else {
                return count;
            }
        }
        return count;
    }

}
