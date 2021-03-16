package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataFeedback;
import com.gs.lshly.biz.support.foundation.repository.IDataFeedbackRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataFeedbackService;
import com.gs.lshly.common.enums.OperatorTypeEnum;
import com.gs.lshly.common.exception.BusinessException;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.user.dto.BbcDataFeedbackDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcDataFeedbackQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcDataFeedbackVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.time.LocalDateTime;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-24
*/
@Component
public class BbcDataFeedbackServiceImpl implements IBbcDataFeedbackService {

    @Autowired
    private IDataFeedbackRepository repository;

    @Override
    public PageData<BbcDataFeedbackVO.ListVO> pageData(BbcDataFeedbackQTO.QTO qto) {
        if(StringUtils.isBlank(qto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<DataFeedback> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("fb_operator_id",qto.getJwtUserId());
        wrapper.eq("fb_operator_type",OperatorTypeEnum.会员.getCode());
        IPage<DataFeedback> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcDataFeedbackVO.ListVO.class, page);
    }

    @Override
    public void addDataFeedback(BbcDataFeedbackDTO.ETO eto) {
        if(StringUtils.isBlank(eto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        DataFeedback dataFeedback = new DataFeedback();
        BeanUtils.copyProperties(eto, dataFeedback);
        dataFeedback.setFbOperatorType(OperatorTypeEnum.会员.getCode());
        dataFeedback.setFbOperatorTime(LocalDateTime.now());
        dataFeedback.setFbOperatorId(eto.getJwtUserId());
        repository.save(dataFeedback);
    }

}
