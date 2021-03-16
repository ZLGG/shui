package com.gs.lshly.biz.support.foundation.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataFeedback;
import com.gs.lshly.biz.support.foundation.repository.IDataFeedbackRepository;
import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCMerchFeedbackService;
import com.gs.lshly.common.enums.OperatorTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchFeedbackDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchFeedbackQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchFeedbackVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-24
*/
@Component
public class PCMerchFeedbackServiceImpl implements IPCMerchFeedbackService {

    @Autowired
    private IDataFeedbackRepository repository;

    @Override
    public PageData<PCMerchFeedbackVO.ListVO> pageData(PCMerchFeedbackQTO.QTO qto) {
        if(StringUtils.isBlank(qto.getJwtShopId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<DataFeedback> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("fb_operator_id",qto.getJwtShopId());
        wrapper.eq("fb_operator_type",OperatorTypeEnum.商家.getCode());
        IPage<DataFeedback> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchFeedbackVO.ListVO.class, page);
    }

    @Override
    public void addDataFeedback(PCMerchFeedbackDTO.ETO eto) {
        if(StringUtils.isBlank(eto.getJwtShopId())){
            throw new BusinessException("没有登录");
        }
        DataFeedback dataFeedback = new DataFeedback();
        BeanUtils.copyProperties(eto, dataFeedback);
        dataFeedback.setFbOperatorType(OperatorTypeEnum.商家.getCode());
        dataFeedback.setFbOperatorTime(LocalDateTime.now());
        dataFeedback.setFbOperatorId(eto.getJwtShopId());
        repository.save(dataFeedback);
    }

}
