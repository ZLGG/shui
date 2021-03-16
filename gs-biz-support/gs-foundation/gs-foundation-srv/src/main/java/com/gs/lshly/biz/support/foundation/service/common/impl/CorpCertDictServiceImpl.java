package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.CorpCertDict;
import com.gs.lshly.biz.support.foundation.entity.CorpTypeCert;
import com.gs.lshly.biz.support.foundation.entity.CorpTypeDict;
import com.gs.lshly.biz.support.foundation.entity.LegalCert;
import com.gs.lshly.biz.support.foundation.enums.CorpCertDictQueryTypeEnum;
import com.gs.lshly.biz.support.foundation.mapper.CorpTypeCertMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.CorpTypeCertView;
import com.gs.lshly.biz.support.foundation.repository.ICorpCertDictRepository;
import com.gs.lshly.biz.support.foundation.repository.ICorpTypeCertRepository;
import com.gs.lshly.biz.support.foundation.repository.ILegalCertRepository;
import com.gs.lshly.biz.support.foundation.service.common.ICorpCertDictService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CorpCertDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpCertDictQTO;
import com.gs.lshly.common.struct.common.CorpCertDictVO;
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
* @author xxfc
* @since 2020-10-09
*/
@Component
public class CorpCertDictServiceImpl implements ICorpCertDictService {

    @Autowired
    private ICorpCertDictRepository repository;

    @Autowired
    private CorpTypeCertMapper corpTypeCertMapper;

    @Autowired
    private ICorpTypeCertRepository corpTypeCertRepository;

    @Autowired
    private ILegalCertRepository userLegalCertRepository;

    @Autowired
    private ICorpCertDictRepository corpCertDictRepository;



    @Override
    public PageData<CorpCertDictVO.ListVO> pageData(CorpCertDictQTO.QTO qto) {
        QueryWrapper<CorpCertDict> queryWrapper = MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getQueryValue())){
            if(CorpCertDictQueryTypeEnum.证照名称.getCode().equals(qto.getQueryType())){
                queryWrapper.like("cert_name",qto.getQueryValue());
            }
        }
        queryWrapper.orderByAsc("idx","id");
        IPage<CorpCertDict> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, CorpCertDictVO.ListVO.class, page);
    }

    @Override
    public void addCorpCertDict(CorpCertDictDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数为空异常！！");
        }
        if (StringUtils.isBlank(eto.getCertName())){
            throw new BusinessException("请填写证照名称！！");
        }
        CorpCertDict corpCertDict = new CorpCertDict();
        BeanUtils.copyProperties(eto, corpCertDict);
        repository.save(corpCertDict);
    }


    @Override
    public void deleteBatchCorpCertDict(CorpCertDictDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("ID数组不能为空");
        }
        QueryWrapper<CorpTypeCert> corpCertDictWrapperCount = MybatisPlusUtil.query();
        corpCertDictWrapperCount.in("cert_id",dto.getIdList());
        int corpCertDictCount = corpTypeCertRepository.count(corpCertDictWrapperCount);
        if(corpCertDictCount > 0 ){
            throw new BusinessException("证照已与企业类型关联,不能删除");
        }
        QueryWrapper<LegalCert> userLegalCertWrapperCount = MybatisPlusUtil.query();
        userLegalCertWrapperCount.in("cert_id",dto.getIdList());
        int userLegalCertCount = userLegalCertRepository.count(userLegalCertWrapperCount);
        if(userLegalCertCount > 0 ){
            throw new BusinessException("证照已与法人单位关联,不能删除");
        }
        repository.removeByIds(dto.getIdList());
    }

    @Override
    public void editCorpCertDict(CorpCertDictDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数为空异常！！");
        }
        CorpCertDict corpCertDict = new CorpCertDict();
        BeanUtils.copyProperties(eto, corpCertDict);
        repository.updateById(corpCertDict);
    }

    @Override
    public CorpCertDictVO.DetailVO detailCorpCertDict(CorpCertDictDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto) || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数错误！！");
        }
        CorpCertDict corpCertDict = repository.getById(dto.getId());
        if(ObjectUtils.isEmpty(corpCertDict)){
            throw new BusinessException("数据查询错误");
        }
        CorpCertDictVO.DetailVO detailVo = new CorpCertDictVO.DetailVO();
        BeanUtils.copyProperties(corpCertDict, detailVo);
        return detailVo;
    }

    @Override
    public List<CorpCertDictVO.SimpleVO> ListCorpTypeCert(CorpCertDictQTO.CorpTypeIdQTO qto) {
        QueryWrapper<CorpTypeCertView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("tc.corp_type_id",qto.getCorpTypeId());
        wrapper.orderByAsc("d.idx");
        List<CorpTypeCertView> viewList = corpTypeCertMapper.mapperListCorpTypeCert(wrapper);
        return ListUtil.listCover(CorpCertDictVO.SimpleVO.class,viewList);
    }

}
