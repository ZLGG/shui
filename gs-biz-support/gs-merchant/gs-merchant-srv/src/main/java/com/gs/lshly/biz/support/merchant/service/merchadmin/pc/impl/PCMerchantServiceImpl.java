package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.merchant.entity.Merchant;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchantService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchantDTO.DTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchantDTO.ETO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchantVO.DetailVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.DateUtils;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-06
*/
@Component
public class PCMerchantServiceImpl implements IPCMerchantService {

    @Autowired
    private IMerchantRepository repository;

	@Override
	public DetailVO getMerchant(DTO dto) {
		DetailVO detailVO = new DetailVO();
		String merchantId = dto.getJwtMerchantId();
		Merchant merchant = repository.getById(merchantId);
		if(merchant!=null){
			BeanCopyUtils.copyProperties(merchant, detailVO);
			detailVO.setExpirationTime(DateUtils.fomatDate(merchant.getExpirationTime(), "yyyy-MM-dd"));
		}
		return detailVO;
	}


	@Override
	public void modifyMerchant(ETO eto) {
		DetailVO detailVO = new DetailVO();
		String merchantId = eto.getJwtMerchantId();
		Merchant merchant = repository.getById(merchantId);
		if(merchant==null)
			throw new BusinessException("未找到商户信息");
		BeanCopyUtils.copyProperties(eto, merchant);
		
		repository.saveOrUpdate(merchant);
	}

}
