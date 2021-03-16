package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantAgreement;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAgreementRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchantAgreementService;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantAgreementService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchantAgreementVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAgreementDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-06
*/
@Component
public class PCMerchantAgreementServiceImpl implements IPCMerchantAgreementService {

    @Autowired
    private IMerchantAgreementRepository repository;


    @Override
    public PCMerchantAgreementVO.DetailVO detailMerchantAgreement(BaseDTO dto) {
        PCMerchantAgreementVO.DetailVO detailVo = new PCMerchantAgreementVO.DetailVO();
        List< MerchantAgreement> merchantAgreementList =  repository.list();
        if(merchantAgreementList.size() > 1){
          throw new BusinessException("入驻协义数据重复");
        }
        MerchantAgreement merchantAgreement = merchantAgreementList.get(0);
        BeanCopyUtils.copyProperties(merchantAgreement, detailVo);
        return detailVo;
    }

}
