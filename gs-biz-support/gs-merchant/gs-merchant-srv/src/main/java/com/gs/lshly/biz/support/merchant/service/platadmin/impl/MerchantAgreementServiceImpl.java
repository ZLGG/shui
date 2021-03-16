package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.entity.MerchantAgreement;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAgreementRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantAgreementService;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAgreementDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-06
*/
@Component
public class MerchantAgreementServiceImpl implements IMerchantAgreementService {

    @Autowired
    private IMerchantAgreementRepository repository;

    @Override
    public void editMerchantAgreement(MerchantAgreementDTO.ETO eto) {
        QueryWrapper<MerchantAgreement> merchantAgreementQueryWrapper = MybatisPlusUtil.query();
        MerchantAgreement merchantAgreement = repository.getOne(merchantAgreementQueryWrapper);
        if(null == merchantAgreement){
            merchantAgreement = new MerchantAgreement();
            repository.save(merchantAgreement);
        }else{
            merchantAgreement.setContent(eto.getContent());
            repository.updateById(merchantAgreement);
        }
    }

    @Override
    public MerchantAgreementVO.DetailVO detailMerchantAgreement(MerchantAgreementDTO.IdDTO dto) {
        QueryWrapper<MerchantAgreement> merchantAgreementQueryWrapper = MybatisPlusUtil.query();
        MerchantAgreement merchantAgreement =  repository.getOne(merchantAgreementQueryWrapper);
        MerchantAgreementVO.DetailVO detailVo = new MerchantAgreementVO.DetailVO();
        if(null != merchantAgreement){
            BeanCopyUtils.copyProperties(merchantAgreement, detailVo);
        }
        return detailVo;
    }

}
