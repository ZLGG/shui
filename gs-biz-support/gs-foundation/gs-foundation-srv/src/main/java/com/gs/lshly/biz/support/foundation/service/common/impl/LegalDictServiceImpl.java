package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.LegalCert;
import com.gs.lshly.biz.support.foundation.entity.LegalDict;
import com.gs.lshly.biz.support.foundation.enums.LegalDictCorpQueryTypeEnum;
import com.gs.lshly.biz.support.foundation.enums.LegalDictNalQueryTypeEnum;
import com.gs.lshly.biz.support.foundation.mapper.CorpTypeCertMapper;
import com.gs.lshly.biz.support.foundation.mapper.LegalCertMapper;
import com.gs.lshly.biz.support.foundation.mapper.LegalDictMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.CorpTypeCertView;
import com.gs.lshly.biz.support.foundation.mapper.view.LegalCertView;
import com.gs.lshly.biz.support.foundation.mapper.view.LegalCortpTypeView;
import com.gs.lshly.biz.support.foundation.repository.ILegalCertRepository;
import com.gs.lshly.biz.support.foundation.repository.ILegalDictRepository;
import com.gs.lshly.biz.support.foundation.service.common.ILegalDictService;
import com.gs.lshly.common.enums.BusinessTypeEnum;
import com.gs.lshly.common.enums.LegalTypeEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictQTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
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
* @author xxfc
* @since 2020-10-06
*/
@Component
public class LegalDictServiceImpl implements ILegalDictService {

    @Autowired
    private ILegalDictRepository repository;

    @Autowired
    private LegalDictMapper legalDictMapper;

    @Autowired
    private CorpTypeCertMapper corpTypeCertMapper;

    @Autowired
    private LegalCertMapper legalCertMapper;

    @Autowired
    private ILegalCertRepository legalCertRepository;

    @Override
    public PageData<LegalDictVO.ListVO> pageData(LegalDictQTO.QTO qto) {
        QueryWrapper<LegalCortpTypeView> queryWrapper = MybatisPlusUtil.query();
        if(null != qto.getBusinessType() && !BusinessTypeEnum.全部.getCode().equals(qto.getBusinessType())){
            queryWrapper.eq("lg.business_type",qto.getBusinessType());
        }
        if(StringUtils.isNotBlank(qto.getQueryValue())){
            if(LegalDictCorpQueryTypeEnum.公司名称.getCode().equals(qto.getQueryType())){
                queryWrapper.like("lg.corp_name",qto.getQueryValue());
            }else  if(LegalDictCorpQueryTypeEnum.法人代表.getCode().equals(qto.getQueryType())){
                queryWrapper.like("lg.person_name",qto.getQueryValue());
            }else  if(LegalDictCorpQueryTypeEnum.公司联系人手机号.getCode().equals(qto.getQueryType())){
                queryWrapper.like("lg.corp_phone",qto.getQueryValue());
            }
        }
        queryWrapper.eq("lg.legal_type",LegalTypeEnum.企业.getCode());
        IPage<LegalCortpTypeView> page = MybatisPlusUtil.pager(qto);
        IPage<LegalCortpTypeView> legalCortpTypeViewIPage = legalDictMapper.pageLegalCortpType(page, queryWrapper);
        List<LegalDictVO.ListVO> voList = new ArrayList<>();
        if(ObjectUtils.isEmpty(legalCortpTypeViewIPage) || ObjectUtils.isNotEmpty(page.getRecords())){
            List<String> idList = new ArrayList<>();
            for(LegalCortpTypeView viteItem:legalCortpTypeViewIPage.getRecords()){
                LegalDictVO.ListVO listVO = new LegalDictVO.ListVO();
                BeanUtils.copyProperties(viteItem,listVO);
                idList.add(viteItem.getId());
                voList.add(listVO);
            }
        }
        return new PageData<>(voList,qto.getPageNum(),qto.getPageSize(),legalCortpTypeViewIPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String addLegalDict(LegalDictDTO.ETO eto) {
        LegalDict legalDict = new LegalDict();
        BeanUtils.copyProperties(eto, legalDict);
        legalDict.setLegalType(LegalTypeEnum.企业.getCode());
        legalDict.setUnifiedSocialCreditCode(StringUtils.isBlank(eto.getCorpLicenseNum())?"":eto.getCorpLicenseNum());
        repository.save(legalDict);
        //保存数据
        if (ObjectUtils.isNotEmpty(eto.getCertList())){
            List<LegalCert> userLegalCertList = new ArrayList<>();
            for(LegalDictDTO.CertDTO certDTO:eto.getCertList()){
                LegalCert userLegalCert = new LegalCert();
                userLegalCert.setCertUrl(certDTO.getCertUrl());
                userLegalCert.setCertId(certDTO.getId());
                userLegalCert.setLegalId(legalDict.getId());
                userLegalCertList.add(userLegalCert);
            }
            legalCertRepository.saveBatch(userLegalCertList);
        }
        return legalDict.getId();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editLegalDict(LegalDictDTO.ETO eto) {
        LegalDict legalDict = new LegalDict();
        BeanUtils.copyProperties(eto, legalDict);
        repository.updateById(legalDict);
        ///删除关系
        QueryWrapper<LegalCert> queryWrapperDelete = MybatisPlusUtil.query();
        queryWrapperDelete.eq("legal_id",eto.getId());
        legalCertMapper.delete(queryWrapperDelete);
        //保存数据
        List<LegalCert> userLegalCertList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(eto.getCertList())){
            for(LegalDictDTO.CertDTO certDTO:eto.getCertList()){
                LegalCert userLegalCert = new LegalCert();
                userLegalCert.setCertUrl(StringUtils.isBlank(certDTO.getCertUrl())?"":certDTO.getCertUrl());
                userLegalCert.setCertId(StringUtils.isBlank(certDTO.getId())?"":certDTO.getId());
                userLegalCert.setLegalId(StringUtils.isBlank(eto.getId())?"":eto.getId());
                userLegalCertList.add(userLegalCert);
            }
            legalCertRepository.saveBatch(userLegalCertList);
        }
    }

    @Override
    public LegalDictVO.DetailVO detailLegalDict(LegalDictDTO.IdDTO dto) {
        QueryWrapper<LegalCortpTypeView> queryWrapperLegalCorpType = MybatisPlusUtil.query();
        queryWrapperLegalCorpType.eq("lg.id",dto.getId());
        LegalCortpTypeView userLegalCortpTypeView = legalDictMapper.selectLegalCortpType(queryWrapperLegalCorpType);
        LegalDictVO.DetailVO detailVo = new LegalDictVO.DetailVO();
        if(null == userLegalCortpTypeView){
            throw new BusinessException("无效的ID");
        }
        LegalDictVO.CompanyVO companyVO  = new LegalDictVO.CompanyVO();
        LegalDictVO.BankVO bankVO  = new LegalDictVO.BankVO();
        BeanUtils.copyProperties(userLegalCortpTypeView, companyVO);
        BeanUtils.copyProperties(userLegalCortpTypeView, bankVO);
        detailVo.setCompanyVO(companyVO);
        detailVo.setCertListVO(new ArrayList<>());
        //已有的证照信息
        QueryWrapper<LegalCertView> queryWrapperLegalCert = MybatisPlusUtil.query();
        queryWrapperLegalCert.eq("lc.legal_id",dto.getId());
        List<LegalCertView> viewList =  legalCertMapper.listrLegalCert(queryWrapperLegalCert);
        if(ObjectUtils.isNotEmpty(viewList)){
            for(LegalCertView viewItem:viewList){
                LegalDictVO.CertVO cert = new  LegalDictVO.CertVO();
                cert.setId(StringUtils.isBlank(viewItem.getCertId())?"":viewItem.getCertId());
                cert.setCertName(StringUtils.isBlank(viewItem.getCertName())?"":viewItem.getCertName());
                cert.setCertUrl(StringUtils.isBlank(viewItem.getCertUrl())?"":viewItem.getCertUrl());
                detailVo.getCertListVO().add(cert);
            }
        }
        return detailVo;
    }

    @Override
    public LegalDictVO.DetailVO detailLegalDict(LegalDictDTO.LicenseNumDTO dto) {
        if(StringUtils.isBlank(dto.getCorpLicenseNum())){
            throw new BusinessException("营业执照注册号不能为空");
        }
        QueryWrapper<LegalDict> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.eq("corp_license_num",dto.getCorpLicenseNum());
        LegalDict legalDict = repository.getOne(queryWrapper);
        if(null == legalDict){
            return null;
        }
        LegalDictVO.DetailVO detailVO = new LegalDictVO.DetailVO();
        detailVO.setCertListVO(new ArrayList<>());
        detailVO.setNeedCertListVO(new ArrayList<>());
        //企业信息
        LegalDictVO.CompanyVO companyVO = new LegalDictVO.CompanyVO();
        BeanUtils.copyProperties(legalDict,companyVO);
        detailVO.setCompanyVO(companyVO);
        //银行信息
        LegalDictVO.BankVO bankVO = new LegalDictVO.BankVO();
        BeanUtils.copyProperties(legalDict,bankVO);
        detailVO.setBankVO(bankVO);


        if(StringUtils.isNotBlank(legalDict.getCorpTypeId())){
            QueryWrapper<CorpTypeCertView> corpTypeCertViewWrapper =  MybatisPlusUtil.query();
            corpTypeCertViewWrapper.eq("d.id",legalDict.getCorpTypeId());
            //需要证照信息
            List<CorpTypeCertView> needViewList =  corpTypeCertMapper.mapperListCorpTypeCert(corpTypeCertViewWrapper);
            if(ObjectUtils.isNotEmpty(needViewList)){
                for(CorpTypeCertView viewItem:needViewList){
                    LegalDictVO.NeedCertVO cert = new  LegalDictVO.NeedCertVO();
                    cert.setId(viewItem.getCertId());
                    cert.setCertName(viewItem.getCertName());
                    detailVO.getNeedCertListVO().add(cert);
                }
            }
            //已有证照信息
            QueryWrapper<LegalCertView> queryWrapperLegalCert = MybatisPlusUtil.query();
            queryWrapperLegalCert.eq("lc.legal_id",legalDict.getId());
            List<LegalCertView> viewList =  legalCertMapper.listrLegalCert(queryWrapperLegalCert);
            if(ObjectUtils.isNotEmpty(viewList)){
                for(LegalCertView viewItem:viewList){
                    LegalDictVO.CertVO cert = new  LegalDictVO.CertVO();
                    cert.setId(viewItem.getCertId());
                    cert.setCertName(viewItem.getCertName());
                    cert.setCertUrl(viewItem.getCertUrl());
                    detailVO.getCertListVO().add(cert);
                }
            }
        }
        return detailVO;
    }


    @Override
    public PageData<LegalDictVO.NalListVO> nalList(LegalDictQTO.NalQTO qto) {
        QueryWrapper<LegalDict> queryWrapper = MybatisPlusUtil.query();
        if(null != qto.getBusinessType() && !BusinessTypeEnum.全部.getCode().equals(qto.getBusinessType())){
            queryWrapper.eq("business_type",qto.getBusinessType());
        }
        if(StringUtils.isNotBlank(qto.getQueryValue())){
            if(LegalDictNalQueryTypeEnum.姓名.getCode().equals(qto.getQueryType())){
                queryWrapper.like("person_name",qto.getQueryValue());
            }else  if(LegalDictNalQueryTypeEnum.手机号.equals(qto.getQueryType())){
                queryWrapper.like("corp_phone",qto.getQueryValue());
            }
        }
        queryWrapper.eq("legal_type", LegalTypeEnum.个人.getCode());
        IPage<LegalDict> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, LegalDictVO.NalListVO.class,page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nalEditLegalDict(LegalDictDTO.NalETO eto) {
        if (null == eto ){
            throw new BusinessException("参数为空异常！！");
        }
        LegalDict legalDict = new LegalDict();
        BeanUtils.copyProperties(eto, legalDict);
        repository.updateById(legalDict);
    }

    @Override
    public LegalDictVO.NalDetailVO nalDetailLegalDict(LegalDictDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数为空！！");
        }
        LegalDict legalDict = repository.getById(dto.getId());
        if(ObjectUtils.isEmpty(legalDict)){
            throw new BusinessException("无效的ID");
        }
        LegalDictVO.NalDetailVO nalDetailVO = new LegalDictVO.NalDetailVO();
        BeanUtils.copyProperties(legalDict,nalDetailVO);
        return nalDetailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchLegalDict(LegalDictDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要删除的数据！！");
        }
        repository.removeByIds(dto.getIdList());

    }

    @Override
    public LegalDictVO.CheckCertVO checkUploadCert(String corpTypeId,List<String> certIdList) {
        QueryWrapper<CorpTypeCertView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("d.id",corpTypeId);
        List<CorpTypeCertView> certViewList =  corpTypeCertMapper.mapperListCorpTypeCert(queryWrapper);
        LegalDictVO.CheckCertVO checkCertVO = new LegalDictVO.CheckCertVO();
        if(ObjectUtils.isNotEmpty(certViewList)){
            for(CorpTypeCertView certView:certViewList){
                if(TrueFalseEnum.是.getCode().equals(certView.getIsNeed())){
                    boolean bool = false;
                    for(String uploadId:certIdList){
                        if(uploadId.equals(certView.getCertId())){
                            bool = true;
                            break;
                        }
                    }
                    if(bool==false){
                        LegalDictVO.CertVO certVO = new LegalDictVO.CertVO();
                        BeanUtils.copyProperties(certView,certVO);
                        checkCertVO.getCertList().add(certVO);
                    }
                }
            }
        }
        return checkCertVO;
    }

    @Override
    public ExportDataDTO export(LegalDictDTO.IdListDTO dto) throws Exception{
        if (ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要导出的数据！！");
        }
        List<LegalDict> legalDictList =  repository.listByIds(dto.getIdList());
        List<LegalDictVO.ListVO> listVo = ListUtil.listCover(LegalDictVO.ListVO.class,legalDictList);
        return ExcelUtil.treatmentBean(listVo, LegalDictVO.ListVO.class);
    }

    @Override
    public void innerUpdateLegalDict(String legalId, Integer bussinessType) {
        LegalDict legalDict = new LegalDict();
        legalDict.setId(legalId);
        legalDict.setBusinessType(bussinessType);
        repository.updateById(legalDict);
    }

    @Override
    public List<LegalDictVO.DetailVO> innerListLegalDict(List<String> legalIdList) {
        if(ObjectUtils.isEmpty(legalIdList)){
            return new ArrayList<>();
        }
        List<LegalDict> legalDictList  =  repository.listByIds(legalIdList);
        if(ObjectUtils.isEmpty(legalDictList)){
            return new ArrayList<>();
        }
        List<LegalDictVO.DetailVO> voList = new ArrayList<>();
        for(LegalDict legalDict:legalDictList){
            LegalDictVO.DetailVO detailVO = new LegalDictVO.DetailVO();
            detailVO.setId(legalDict.getId());
            //企业信息
            LegalDictVO.CompanyVO companyVO = new LegalDictVO.CompanyVO();
            BeanUtils.copyProperties(legalDict,companyVO);
            detailVO.setCompanyVO(companyVO);
            voList.add(detailVO);
        }
        return voList;
    }

    @Override
    public String innerQueryAddLegalDict(LegalDictDTO.InnerETO eto) {
        //先检查企业字典库里是否有这个企业的信息（用哪个字段来校验?）
        //保存企业字典
        LegalDict legalDict = new LegalDict();
        BeanUtils.copyProperties(eto, legalDict);
        legalDict.setLegalType(LegalTypeEnum.企业.getCode());
        legalDict.setCorpLicenseNum(StringUtils.isBlank(eto.getUnifiedSocialCreditCode())?"":eto.getUnifiedSocialCreditCode());
        repository.saveOrUpdate(legalDict);
        //保存企业证照数据
        List<LegalCert> userLegalCertList = new ArrayList<>();
        for(LegalDictDTO.CertDTO certDTO:eto.getCertList()){
            LegalCert userLegalCert = new LegalCert();
            userLegalCert.setCertId(certDTO.getId());
            userLegalCert.setCertUrl(certDTO.getCertUrl());
            userLegalCert.setLegalId(legalDict.getId());
            userLegalCertList.add(userLegalCert);
        }
        legalCertRepository.saveBatch(userLegalCertList);
        return legalDict.getId();
    }

    @Override
    public LegalDictVO.InnerDetailVO innerdetailLegalDict(String legalDictId) {
        QueryWrapper<LegalCortpTypeView> queryWrapperLegalCorpType = MybatisPlusUtil.query();
        queryWrapperLegalCorpType.eq("lg.id",legalDictId);
        LegalCortpTypeView userLegalCortpTypeView = legalDictMapper.selectLegalCortpType(queryWrapperLegalCorpType);
        LegalDictVO.InnerDetailVO detailVo = new LegalDictVO.InnerDetailVO();
        if(null == userLegalCortpTypeView){
            throw new BusinessException("无效的ID");
        }
        LegalDictVO.CompanyVO companyVO = new LegalDictVO.CompanyVO();
        LegalDictVO.BankVO bankVO  = new LegalDictVO.BankVO();
        BeanUtils.copyProperties(userLegalCortpTypeView, companyVO);
        BeanUtils.copyProperties(userLegalCortpTypeView, bankVO);
        detailVo.setCompanyVO(companyVO);
        detailVo.setBankVO(bankVO);
        detailVo.setCdate(userLegalCortpTypeView.getCdate());
        //已有的证照信息
        QueryWrapper<LegalCertView> queryWrapperLegalCert = MybatisPlusUtil.query();
        queryWrapperLegalCert.eq("lc.legal_id",legalDictId);
        List<LegalCertView> viewList =  legalCertMapper.listrLegalCert(queryWrapperLegalCert);
        if(ObjectUtils.isNotEmpty(viewList)){
            for(LegalCertView viewItem:viewList){
                LegalDictVO.CertVO cert = new  LegalDictVO.CertVO();
                cert.setId(viewItem.getCertId());
                cert.setCertName(viewItem.getCertName());
                cert.setCertUrl(viewItem.getCertUrl());
                detailVo.getCertListVO().add(cert);
            }
        }
        return detailVo;
    }

    @Override
    public LegalDictVO.ListVO innerViewlLegalDict(String legalDictId) {
        LegalDict byId = repository.getById(legalDictId);
        if (ObjectUtils.isEmpty(byId)){
            return null;
        }
        LegalDictVO.ListVO listVO = new LegalDictVO.ListVO();
        BeanUtils.copyProperties(byId,listVO);
        return listVO;
    }

}
