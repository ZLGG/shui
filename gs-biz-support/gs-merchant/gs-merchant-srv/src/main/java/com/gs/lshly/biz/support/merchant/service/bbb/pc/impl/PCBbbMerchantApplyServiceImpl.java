package com.gs.lshly.biz.support.merchant.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantApply;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyStateEnum;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyRepository;
import com.gs.lshly.biz.support.merchant.service.bbb.pc.IPCBbbMerchantApplyService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.PCBbbMerchantApplyDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.qto.PCBbbMerchantApplyQTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantApplyVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.Date;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-22
*/
@Component
public class PCBbbMerchantApplyServiceImpl implements IPCBbbMerchantApplyService {

    @Autowired
    private IMerchantApplyRepository repository;

    @Override
    public void addMerchantApply(PCBbbMerchantApplyDTO.ETO eto) {
        MerchantApply merchantApply = new MerchantApply();
        BeanCopyUtils.copyProperties(eto.getShopETO(), merchantApply);
        BeanCopyUtils.copyProperties(eto.getLegalDictETO(), merchantApply);
        repository.save(merchantApply);
    }
}
